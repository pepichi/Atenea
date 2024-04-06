var app = angular.module('app', []);

app.controller('menuController', function ($scope, $http, $window) {
    $http.post('/Atenea/Servlet/ProcedimientoServlet').then(function (response) {
        $scope.entradasMenu = response.data;
    });
    
    $scope.redirigir = function(url){
        $window.location.href = url;
    };
});



