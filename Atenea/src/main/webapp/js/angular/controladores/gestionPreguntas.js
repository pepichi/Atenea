var gestionPreguntas = angular.module('gestionPreguntas', ['textAngular', 'ngSanitize', 'listaCategoriasApp']);



gestionPreguntas.controller('controladorEnunciadoPregunta', function ($scope, $http) {
    $scope.enunciado = '<p>Escribe aquí, <strong>el enunciado de la pregunta</strong>!</p>';
    $scope.numeroRespuestas = 1;
    $scope.respuestas = [{respuesta: '',esCorrecta: false, feedback: ''}];
    $scope.comentario = '';
    $scope.fuente = '';

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



