fichador.config(['$routeProvider', function ($routeProvider) {
        //LOGIN
        $routeProvider.when('/login', { templateUrl: 'js/app/login.html', controller: 'loginController' });

        //OTROS
        $routeProvider.when('/', { templateUrl: 'js/app/login.html', controller: 'loginController' });
        $routeProvider.when('/home', { templateUrl: 'js/app/common/home.html', controller: 'homeController' });

        $routeProvider.otherwise({ redirectTo: '/' });
}]);
