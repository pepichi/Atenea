/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
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


