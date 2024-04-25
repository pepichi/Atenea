var app = angular.module('app', ['textAngular', 'ngSanitize']);

app.controller('menuController', function ($scope, $http, $window) {
    $http.post('/Atenea/Servlet/ProcedimientoServlet').then(function (response) {
        $scope.entradasMenu = response.data;
    });

    $scope.redirigir = function (url) {
        $window.location.href = url;
    };
});

app.controller('controladorEnunciadoPregunta', function ($scope, $http, $httpParamSerializerJQLike) {
    $scope.enunciado = '<p>Escribe aquí, <strong>el enunciado de la pregunta</strong>!</p>';
    $scope.numeroRespuestas = 1;
    $scope.respuestas = [{respuesta: '',esCorrecta: false, feedback: ''}];
    $scope.comentario = '';
    $scope.fuente = '';
    $scope.categoriasNoSeleccionadas = [];
    $scope.categoriasSeleccionadas = [];

    $scope.moverADerecha = function (index) {
        var item = $scope.categoriasNoSeleccionadas.splice(index, 1)[0];
        $scope.categoriasSeleccionadas.push(item);
    };

    $scope.moverAIzquierda = function (index) {
        var item = $scope.categoriasSeleccionadas.splice(index, 1)[0];
        $scope.categoriasNoSeleccionadas.push(item);
    };

    $scope.incrementarNumeroRespuestas = function () {
        $scope.numeroRespuestas++;
        $scope.respuestas.push({respuesta: '',esCorrecta: false, feedback: ''});
    };

    $scope.decrementarNumeroRespuestas = function () {
        if ($scope.numeroRespuestas === 1) {
            alert('El número mínimo de respuestas es 1. No se puede borrar');
        } else {
            const valorRespuesta = $scope.respuestas[$scope.numeroRespuestas - 1].respuesta;
            if (valorRespuesta === null || valorRespuesta === undefined || valorRespuesta.trim() === "") {
                $scope.numeroRespuestas--;
                $scope.respuestas.pop();
            } else {
                alert('No se puede borrar una respuesta que tiene contenido');
            }
        }
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

    $scope.validar = function () {
        let valido = true;
        let algunaCorrecta = false;
        $scope.respuestas.forEach(function (elemento) {
            if (elemento.esCorrecta === true) {
                algunaCorrecta = true;
            }
        });
        if (algunaCorrecta === false) {
            valido = false;
            alert('Al menos alguna pregunta debe ser correcta');
        }
        if ($scope.respuestas.length === 0) {
            valido = false;
            alert('Al menos debe haber una respuesta para almacenar la pregunta');
        }
        $scope.respuestas.forEach(function (elemento) {
            if (elemento.respuesta === null || elemento.respuesta === undefined || elemento.respuesta.trim() === "") {
                valido = false;
                alert('No puede haber un enunciado de respuesta vacío.');
            }
        });
        if (valido === true) {
            const datos = {
                enunciado: $scope.enunciado,
                respuestas: $scope.respuestas,
                fuente: $scope.fuente,
                comentario: $scope.comentario,
                categoriasSeleccionadas: $scope.categoriasSeleccionadas
            };
            $http({
                method: 'POST',
                url: '/Atenea/Servlet/InsertarPreguntaBancoServlet',
                data: JSON.stringify(datos),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                alert(response);
            });
        }
    };
});



