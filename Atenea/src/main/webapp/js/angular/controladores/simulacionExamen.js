/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
const CORRECTO = 'CORRECTO';
var app = angular.module('simulacionExamenApp', ['ngDialog', 'ngDialogHelper', 'ngSanitize']);


app.controller('seleccionTipoExamenCtrl', function ($scope, $http, $interval, ngDialog) {
    $scope.configuracionSeleccionada = '';
    $scope.configuraciones = [];
    $scope.cargandoSimulacion = false;
    $scope.inicioExamen = false;
    $scope.configuracionExamenSeleccionada = null;
    $scope.respuestasSeleccionadas = [];
    $scope.comienzoReloj = false;
    $scope.tiempoLimite = null;
    $scope.tiempoInicioExamen = null;
    $scope.finExamen = false;
    $scope.avisoColorIntermedio = 29;
    $scope.avisoFinal = 28;
    $scope.datosGamificacion = null;
    $scope.existeTrofeo = false;
    $scope.estadisticas = null;

    $http({
        method: 'POST',
        url: '/Atenea/Servlet/ConfiguracionExamenServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.configuraciones = response.data;
        angular.element(document).ready(function () {
            $('select').selectpicker("destroy");
            $('select').selectpicker("render");
        });
    });

    $http({
        method: 'POST',
        url: '/Atenea/Servlet/GamificacionServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.datosGamificacion = response.data;
        $scope.existeTrofeo = 'trofeo' in response.data;
    });

    $scope.getConfiguracionSeleccionada = function () {
        for (var i = 0; i < $scope.configuraciones.length; i++) {
            if ($scope.configuraciones[i].idConfiguracionExamen === $scope.configuracionSeleccionada[0]) {
                return $scope.configuraciones[i];
            }
        }
    };
    $scope.comenzarExamen = function () {
        document.getElementById('seleccionConfiguracionContainerId').style.display = 'none';
        $scope.cargandoSimulacion = true;
        const datos = {
            configuracion: $scope.configuracionSeleccionada[0]
        };
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/CrearExamenServlet',
            data: datos,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.examen = response.data;
            console.log($scope.examen);
            $scope.cargandoSimulacion = false;
            $scope.inicioExamen = true;
            $scope.configuracionExamenSeleccionada = $scope.getConfiguracionSeleccionada();
            establecerTiempoLimite();
            updateCountdown();
        });
    };

    $scope.getIndicePregunta = function (idPregunta) {
        for (var i = 0; i < $scope.respuestasSeleccionadas.length; i++) {
            if ($scope.respuestasSeleccionadas[i].idPregunta === idPregunta) {
                return i;
            }
        }
        return -1;
    };

    $scope.getIndiceRespuesta = function (idRespuesta, respuestas) {
        for (var i = 0; i < respuestas.length; i++) {
            if (respuestas[i] === idRespuesta) {
                return i;
            }
        }
        return -1;
    };

    $scope.decorarRespuesta = function (respuesta) {
        if (respuesta.correcta && !document.getElementById(respuesta.idRespuesta).checked) {
            document.getElementById('s' + respuesta.idRespuesta).style.color = '#FF0000';
        } else if (!respuesta.correcta && document.getElementById(respuesta.idRespuesta).checked) {
            document.getElementById('s' + respuesta.idRespuesta).style.color = '#FF0000';
        } else if (respuesta.correcta && document.getElementById(respuesta.idRespuesta).checked) {
            document.getElementById('s' + respuesta.idRespuesta).style.fontWeight = 'bold';
        }
        if (respuesta.feedback !== null) {
            document.getElementById('f' + respuesta.idRespuesta).style.display = 'block';
        }
    };

    $scope.decorarSoloUnaRespuesta = function (idRespuesta) {
        for (var i = 0; i < $scope.examen.length; i++) {
            for (var j = 0; j < $scope.examen[i].respuestas.length; j++) {
                let respuesta = $scope.examen[i].respuestas[j];
                if (idRespuesta === respuesta.idRespuesta) {
                    $scope.decorarRespuesta(respuesta);
                }
            }
        }
    };

    $scope.actualizarSeleccion = function (idPregunta, idRespuesta) {
        let multirespuesta = $scope.configuracionExamenSeleccionada.multirespuesta;
        let marcandoOpcion = document.getElementById(idRespuesta).checked;
        let indicePregunta = $scope.getIndicePregunta(idPregunta);
        if (marcandoOpcion && !multirespuesta) {
            let respuestasPreguntas = document.getElementsByName(idPregunta);
            for (var i = 0; i < respuestasPreguntas.length; i++) {
                respuestasPreguntas[i].checked = false;
                document.getElementById('s' + respuestasPreguntas[i].id).style.fontWeight = "normal";
                document.getElementById('s' + respuestasPreguntas[i].id).style.color = '#000000';
                document.getElementById('f' + respuestasPreguntas[i].id).style.display = 'none';
            }
            document.getElementById(idRespuesta).checked = true;
        }
        if (marcandoOpcion) {
            document.getElementById('s' + idRespuesta).style.fontWeight = "bold";
        } else {
            document.getElementById('s' + idRespuesta).style.fontWeight = "normal";
            document.getElementById('s' + idRespuesta).style.color = '#000000';
            document.getElementById('f' + idRespuesta).style.display = 'none';

        }
        if (indicePregunta === -1) {
            $scope.respuestasSeleccionadas.push({idPregunta: idPregunta, idRespuestas: [idRespuesta]});
        } else {
            if (multirespuesta) {
                let bloque = $scope.respuestasSeleccionadas[indicePregunta];
                let indiceRespuesta = $scope.getIndiceRespuesta(idRespuesta, bloque.idRespuestas);
                if (indiceRespuesta === -1) {
                    $scope.respuestasSeleccionadas[indicePregunta].idRespuestas.push(idRespuesta);
                } else {
                    $scope.respuestasSeleccionadas[indicePregunta].idRespuestas.splice(indiceRespuesta, 1);
                    if ($scope.respuestasSeleccionadas[indicePregunta].idRespuestas.length === 0) {
                        $scope.respuestasSeleccionadas.splice(indicePregunta, 1);
                    }
                }
            } else {
                if (indicePregunta !== -1) {
                    $scope.respuestasSeleccionadas.splice(indicePregunta, 1);
                }
                if (marcandoOpcion) {
                    $scope.respuestasSeleccionadas.push({idPregunta: idPregunta, idRespuestas: [idRespuesta]});
                }
            }
        }

        if (marcandoOpcion && $scope.configuracionExamenSeleccionada.mostrarInmediatamente) {
            $scope.decorarSoloUnaRespuesta(idRespuesta);
        }
    };

    function establecerTiempoLimite() {
        var targetDate = new Date();
        $scope.tiempoInicioExamen = new Date();
        targetDate.setMinutes(targetDate.getMinutes() + $scope.configuracionExamenSeleccionada.tiempo);
        $scope.tiempoLimite = targetDate;
    }

    function updateCountdown() {
        if (!$scope.finExamen && $scope.configuracionExamenSeleccionada.tiempo !== 0) {
            var now = new Date();
            var difference = $scope.tiempoLimite.getTime() - now.getTime();
            if (difference <= 0) {
                $scope.countdown = '¡Tiempo agotado!';
                document.getElementById('bloqueoExamenId').style.display = 'block';
            } else {
                var horas = Math.floor((difference / (1000 * 60 * 60)) % 24);
                var minutos = Math.floor((difference / (1000 * 60)) % 60);
                var segundos = Math.floor((difference / 1000) % 60);
                var horaFormateada = horas.toString().padStart(2, '0');
                var minutosFormateados = minutos.toString().padStart(2, '0');
                var segundosFormateados = segundos.toString().padStart(2, '0');
                if (horas === 0 && minutos <= $scope.avisoColorIntermedio && minutos >= $scope.avisoFinal) {
                    document.getElementById('countdown').style.backgroundColor = '#FFA500';
                }
                if (horas === 0 && minutos <= $scope.avisoFinal) {
                    document.getElementById('countdown').style.backgroundColor = '#FF0000';
                }
                $scope.countdown = horaFormateada + ' : ' + minutosFormateados + ' : ' + segundosFormateados;
            }
        }

        if (!$scope.comienzoReloj) {
            if ($scope.configuracionExamenSeleccionada.tiempo !== 0) {
                $scope.intervalo = $interval(updateCountdown, 1000, $scope.configuracionExamenSeleccionada.tiempo * 60);
                $scope.comienzoReloj = true;
            } else {
                document.getElementById('countdown').style.display = 'none';
            }
        }
    }
    ;

    $scope.finalizarExamen = function () {
        let tiempoEmpleado = Math.floor((new Date().getTime() - $scope.tiempoInicioExamen.getTime()) / 1000);
        document.getElementById('bloqueoExamenId').style.display = 'block';
        $scope.finExamen = true;
        for (var i = 0; i < $scope.examen.length; i++) {
            for (var j = 0; j < $scope.examen[i].respuestas.length; j++) {
                let respuesta = $scope.examen[i].respuestas[j];
                $scope.decorarRespuesta(respuesta);
            }
        }
        const datos = {
            bloques: $scope.examen,
            respuestas: $scope.respuestasSeleccionadas,
            idConfiguracion: $scope.configuracionSeleccionada[0],
            tiempoUtilizado: tiempoEmpleado
        };
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/GuardarResultadosSimulacionServlet',
            data: datos,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            document.getElementById('botoneraFinalizarExamenId').style.display = 'none';
            document.getElementById('botoneraEstadisticaId').style.display = 'block';
            $scope.estadisticas = response.data;
        });
    };

    $scope.comprobarRespuesta = function (idRespuesta) {
        document.getElementById('bloqueoExamenId').style.display = 'block';
        $scope.finExamen = true;
        for (var i = 0; i < $scope.examen.length; i++) {
            for (var j = 0; j < $scope.examen[i].respuestas.length; j++) {
                let respuesta = $scope.examen[i].respuestas[j];
                $scope.decorarSoloUnaRespuesta(respuesta, idRespuesta);
            }
        }
    };
    $scope.irAMenu = function () {
        window.location.href = '/Atenea/jsp/menu/frontal.jsp';
    };
    $scope.mostrarEstadisticas = function () {
        $scope.ventanaConfirmacion = ngDialog.open({
            template: '/Atenea/jsp/examen/estadisticasSimulacion.jsp',
            cache: false,
            height: '400px',
            width: '600px',
            scope: $scope});
    };



});


