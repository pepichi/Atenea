/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
const CORRECTO = 'CORRECTO';
var app = angular.module('pdfApp', ['ngDialog', 'ngDialogHelper']);

app.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

app.controller('subidaFicheroController', function ($scope, $http, ngDialog) {
    $scope.categoriasNoSeleccionadas = [];
    $scope.categoriasSeleccionadas = [];
    $scope.subirFichero = function () {
        const datos = {
            patron: $scope.patron,
            categorias: $scope.categoriasSeleccionadas
        };
        var file = $scope.fichero;
        var fd = new FormData();
        fd.append('file', file);
        fd.append('datos', new Blob([JSON.stringify(datos)], {type: "application/json"}));
        $http.post('/Atenea/Servlet/ImportardorPreguntasPDFServlet', fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {
            if (response.data === CORRECTO) {
                mostrarVentanaConfirmacion(ngDialog, 'Se ha importado el fichero correctamente');
            } else {
                mostrarVentanaAviso(ngDialog, 'Ha ocurrido un error realizando la importación: ' + response.data);
            }
        });
    };
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

