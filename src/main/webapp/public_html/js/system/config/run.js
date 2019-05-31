fichador.run(['$rootScope', 'sessionService', '$location', '$http',
    function ($rootScope, oSessionService, $location, $http) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            var nextUrl = next.$$route.originalPath;
            var host ='http://localhost:8081/';
            $http({
                method: 'GET',
                url: host+'json?ob=usuario&op=check'
            }).then(function (response) {
                if (response.data.status === 200) {
                    oSessionService.setSessionActive();
                   
                } else {
                    oSessionService.setSessionInactive;
                    if (nextUrl !== '/' && nextUrl !== '/home') {
                        $location.path("/");
                    }
                }
            }, function (response) {
                oSessionService.setSessionInactive;
                if (nextUrl !== '/' && nextUrl !== '/home') {
                    $location.path("/");
                }

            });
        });
    }]);
    