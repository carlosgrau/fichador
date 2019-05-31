'use strict';
moduleService.service('sessionService', ['$location', function ($location) {
        var isSessionActive = false;
        var trabajador = '';
        var observerCallbacks = [];
        return {
            isSessionActive: function () {

                return isSessionActive;
            },
            setSessionActive: function (name) {
                isSessionActive = true;
            },
            setSessionInactive: function (name) {
                isSessionActive = false;
            },
            getTrabajador: function () {
                return trabajador;
            },
            setTrabajador: function (id) {
                trabajador = id;
            },
            registerObserverCallback: function (callback) {
                observerCallbacks.push(callback);
            }

        };

    }]);