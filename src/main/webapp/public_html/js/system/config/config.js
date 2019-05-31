'use strict'

fichador.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode(true);
    }]);
fichador.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }]);