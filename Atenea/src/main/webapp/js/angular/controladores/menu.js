/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */

var app = angular.module('menuApp', []);

app.controller('menuController', function ($scope, $http, $window) {
    $http.post('/Atenea/Servlet/ProcedimientoServlet').then(function (response) {
        $scope.entradasMenu = response.data;
    });

    $scope.redirigir = function (url) {
        $window.location.href = url;
    };
});


