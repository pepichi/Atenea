/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */

const CORRECTO = 'CORRECTO';
var gestionPreguntas = angular.module('gestionPreguntas', ['textAngular', 'ngSanitize', 'ngDialog', 'ngDialogHelper']);



gestionPreguntas.controller('controladorEnunciadoPregunta', function ($scope, $http, ngDialog) {
    $scope.enunciado = '<p>Escribe aquí, <strong>el enunciado de la pregunta</strong>!</p>';
    $scope.numeroRespuestas = 1;
    $scope.respuestas = [{respuesta: '', correcta: false, feedback: ''}];
    $scope.comentario = '';
    $scope.fuente = '';
    $scope.tab = 1;
    $scope.preguntas = [];
    $scope.preguntaSeleccionada = '';
    $scope.respuestasPreguntaSeleccionada = [];
    $scope.categoriasNoSeleccionadas = [];
    $scope.categoriasSeleccionadas = [];
    $scope.categoriasTotales = [];

    $scope.incrementarNumeroRespuestas = function () {
        $scope.numeroRespuestas++;
        $scope.respuestas.push({respuesta: '', correcta: false, feedback: ''});
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
            if (elemento.correcta === true) {
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
                categoriasSeleccionadas: $scope.categoriasSeleccionadas,
                idPreguntaAEliminar: $scope.tab === 1 ? 0 : $scope.preguntaSeleccionada[0].idPregunta
            };
            $http({
                method: 'POST',
                url: '/Atenea/Servlet/InsertarPreguntaBancoServlet',
                data: JSON.stringify(datos),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                if (response.data === CORRECTO) {
                    mostrarVentanaConfirmacion(ngDialog, 'Se ha guardado correctamente la pregunta');
                } else {
                    mostrarVentanaAviso(ngDialog, 'Ha ocurrido un error realizando el guardado: ' + response.data);
                }
            });
        }
    };

    $scope.actualizarTab = function (tabId) {
        $scope.tab = tabId;
        if (tabId === 1) {
            document.getElementById('tabId1').style.color = '#337ab7';
            document.getElementById('tabId2').style.color = 'darkgrey';
        } else {
            document.getElementById('tabId2').style.color = '#337ab7';
            document.getElementById('tabId1').style.color = 'darkgrey';
        }
    };

    $http({
        method: 'POST',
        url: '/Atenea/Servlet/ListadoPreguntasServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.preguntas = response.data;
        angular.element(document).ready(function () {
            $('select').selectpicker("destroy");
            $('select').selectpicker("render");
        });
    });

    $scope.recuperarPregunta = function () {
        $scope.enunciado = $scope.preguntaSeleccionada[0].enunciado;
        $scope.comentario = $scope.preguntaSeleccionada[0].comentarios;
        $scope.fuente = $scope.preguntaSeleccionada[0].fuentePregunta;
        const datos = {
            idPregunta: $scope.preguntaSeleccionada[0].idPregunta
        };
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/ListaRespuestasPreguntaServlet',
            data: datos,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.respuestasPreguntaSeleccionada = response.data;
            $scope.respuestas = $scope.respuestasPreguntaSeleccionada;
            $scope.numeroRespuestas = $scope.respuestas.length;
        });
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/ListaCategoriasPreguntaServlet',
            data: datos,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.categoriasSeleccionadas = response.data;
            $scope.categoriasNoSeleccionadas = $scope.categoriasTotales;
            $scope.categoriasNoSeleccionadas = $scope.categoriasNoSeleccionadas.filter(item => !$scope.categoriasSeleccionadas.some(obj => obj['idCategoria'] === item['idCategoria']));
        });
    };

    $http({
        method: 'POST',
        url: '/Atenea/Servlet/ListadoCategoriaServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.categoriasTotales = $scope.categoriasNoSeleccionadas = response.data;
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



