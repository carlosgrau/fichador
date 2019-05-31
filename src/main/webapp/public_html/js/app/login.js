'use strict';

moduleLogin.controller('loginController', ['$scope', '$http', 'sessionService', '$location',
    function ($scope, $http, sessionService, $location) {
        $scope.mensajeError = false;
        $scope.mensaje = false;
        var host ='http://localhost:8081/';
        $scope.validar = function () {
            $scope.ob = "usuario";
            $http({
                method: 'GET',
                url: host+'json?ob=' + $scope.ob + '&op=login&user=' + $scope.login + '&pass=' + forge_sha256($scope.password)
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataUsuarios = response.data.message;
                if (response.status === 200) {
                    sessionService.setTrabajador(response.data.message.trabajador)
                    if (response.data.status === 401) {
                        $scope.mensaje = false;
                        $scope.mensajeError = true;
                    } else {
                        $scope.mensajeError = false;
                        $scope.mensaje = true;
                    }
                }
                $location.url('/home');
            }, function (response) {
                $scope.mensajeError = true;
                $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                $scope.status = response.status;
            });

        }
    }]);