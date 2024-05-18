/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
const listaCategoriasApp = angular.module('listaCategoriasApp', []);


listaCategoriasApp.controller('listaCategoriaController', function ($scope,  $http) {
    $scope.categoriasNoSeleccionadas = [];
    $scope.categoriasSeleccionadas = [];

    $http({
        method: 'POST',
        url: '/Atenea/Servlet/ListadoCategoriaServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.categoriasNoSeleccionadas = response.data;
    });

    $scope.moverADerecha = function (index) {
        var item = $scope.categoriasNoSeleccionadas.splice(index, 1)[0];
        $scope.categoriasSeleccionadas.push(item);
    };

    $scope.moverAIzquierda = function (index) {
        var item = $scope.categoriasSeleccionadas.splice(index, 1)[0];
        $scope.categoriasNoSeleccionadas.push(item);
    };
});

