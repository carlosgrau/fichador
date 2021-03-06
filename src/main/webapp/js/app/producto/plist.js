'use strict';

moduleProducto.controller('productoPlistController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {
        $scope.ejercicio = sessionService.getEmpresa();

        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLProducto = "";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLProducto = $routeParams.order;
        }

        if (!$routeParams.rpp) {
            $scope.rpp = '10';
        } else {
            $scope.rpp = $routeParams.rpp;
        }

        if (!$routeParams.page) {
            $scope.page = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.page = $routeParams.page;
            } else {
                $scope.page = 1;
            }
        }
        if (sessionService) {

            $scope.ocultar = true;
        }

        function pagination2() {
            $scope.list2 = [];
            $scope.neighborhood = 1;
            for (var i = 1; i <= $scope.totalPages; i++) {
                if (i === $scope.page) {
                    $scope.list2.push(i);
                } else if (i <= $scope.page && i >= ($scope.page - $scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i >= $scope.page && i <= ($scope.page - -$scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i === ($scope.page - $scope.neighborhood) - 1) {
                    if ($scope.page >= 4) {
                        $scope.list2.push("...");
                    }
                } else if (i === ($scope.page - -$scope.neighborhood) + 1) {
                    if ($scope.page <= $scope.totalPages - 3) {
                        $scope.list2.push("...");
                    }
                }
            }
        }
        ;
        $scope.resetOrder = function () {
            $location.url(`producto/plist/` + $scope.rpp + `/` + $scope.page);
        };


        $scope.ordena = function (order, align) {
            if ($scope.orderURLServidor === "") {
                $scope.orderURLServidor = "&order=" + order + "," + align;
                $scope.orderURLProducto = order + "," + align;
            } else {
                $scope.orderURLServidor = $scope.orderURLServidor + "-" + order + "," + align;
                $scope.orderURLProducto = $scope.orderURLProducto + "-" + order + "," + align;
            }
            $location.url(`producto/plist/` + $scope.rpp + `/` + $scope.page + `/` + $scope.orderURLProducto);
        };

        //getcount
        $http({
            method: 'GET',
            url: '/json?ob=producto&op=getcount&ejercicio=' + sessionService.getEmpresa()
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuariosNumber = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataUsuariosNumber / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            pagination2();
        }, function (response) {
            $scope.ajaxDataUsuariosNumber = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: '/json?ob=producto&op=getpage&ejercicio=' + sessionService.getEmpresa() + '&rpp=' + $scope.rpp + '&page=' + $scope.page + $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProducto = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
        });

        $scope.update = function () {
            $location.url(`producto/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLProducto);
        };

        $scope.isActive = toolService.isActive;



    }



]);