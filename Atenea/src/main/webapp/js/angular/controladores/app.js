var app = angular.module('app', ['textAngular', 'ngSanitize']);

app.controller('menuController', function ($scope, $http, $window) {
    $http.post('/Atenea/Servlet/ProcedimientoServlet').then(function (response) {
        $scope.entradasMenu = response.data;
    });
    
    $scope.redirigir = function(url){
        $window.location.href = url;
    };
});

app.controller('controladorEnunciadoPregunta', function($scope){
   $scope.htmlContent = '<p>Escribe aquí, <strong>el enunciado de la pregunta</strong>!</p>';
});



