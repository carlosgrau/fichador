'use strict'

moduleCommon.controller('homeController', ['$scope', '$location', 'toolService', 'sessionService', '$http',
    function ($scope, $location, toolService, sessionService, $http) {
        var host = 'http://localhost:8081/';
        $http({
            method: 'GET',
            url: host + 'json?ob=usuario&op=check'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message;
            if (response.status === 200) {
                sessionService.isSessionActive
                sessionService.setTrabajador(response.data.message.trabajador)
                console.log("holaaa--"+$scope.ajaxDataUsuarios.trabajador);
                $http({
                    method: 'GET',
                    url: host + 'json?ob=historico&op=getpage&trabajador='+ sessionService.getTrabajador()
                }).then(function (response) {
                    if (response.status === 200) {
                        sessionService.isSessionActive
                        sessionService.setTrabajador(response.data.message.trabajador)
                    } else if (response.status === 401 || response.status === 500) {
                        sessionService.setSessionInactive
                    }
                })
                
            } else if (response.status === 401 || response.status === 500) {
                sessionService.setSessionInactive
            }
        })
        
        $scope.ruta = $location.path();

        $scope.isActive = toolService.isActive;

    }
]);