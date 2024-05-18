/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
const EXISTE = 'EXISTE';
const CORRECTO = 'CORRECTO';
angular.module('configuracionExamen', ['ngSanitize', 'ngAnimate', 'ui.bootstrap', 'ngDialog', 'ngDialogHelper']);
angular.module('configuracionExamen').config(['ngDialogProvider', function (ngDialogProvider) {
        // Configurar la clase de diálogo predeterminada
        ngDialogProvider.setDefaults({
            className: 'ngdialog-theme-default'
        });
    }]);

angular.module('configuracionExamen').controller('AcordeonCtrl', function ($scope, $http, ngDialog) {
    $scope.noMostrarConfiguracionConTiempo = true;
    $scope.noMostrarSeleccionCategoria = true;
    $scope.noMostrarSeleccionCategoriaPeso = true;
    $scope.categoriasNoSeleccionadas = [];
    $scope.categoriasSeleccionadas = [];
    $scope.categoriasSeleccionadasPesos = [];
    $scope.noExisteNombreConfiguracion = false;
    $scope.ventanaConfirmacion;

    $scope.elementos = [
        {titulo: 'Configuración básica', contenido: '/Atenea/jsp/examen/configuracionExamen/configuracionBasica.jsp'},
        {titulo: 'Configuración temporal', contenido: '/Atenea/jsp/examen/configuracionExamen/configuracionTemporal.jsp'},
        {titulo: 'Configuración pregunta', contenido: '/Atenea/jsp/examen/configuracionExamen/configuracionPregunta.jsp'},
        {titulo: 'Configuración categorías', contenido: '/Atenea/jsp/examen/configuracionExamen/configuracionCategoria.jsp'}
    ];

    $scope.indiceActivo = null;

    $scope.mostrarPanel = function (index) {
        $scope.indiceActivo = index;
    };

    $scope.esActivo = function (index) {
        return $scope.indiceActivo === index;
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
        $scope.actualizarPesos();
    };

    $scope.moverAIzquierda = function (index) {
        var item = $scope.categoriasSeleccionadas.splice(index, 1)[0];
        $scope.categoriasNoSeleccionadas.push(item);
        $scope.actualizarPesos();
    };

    $scope.actualizarPesos = function () {
        $scope.categoriasSeleccionadasPesos = [];
        $scope.valorInicialPeso = 100 / $scope.categoriasSeleccionadas.length;
        $scope.categoriasSeleccionadas.forEach(function (elemento) {
            $scope.categoriasSeleccionadasPesos.push({idCategoria: elemento.idCategoria, categoria: elemento.nombre, peso: $scope.valorInicialPeso});
        });
    };

    $scope.irAMenu = function () {
        window.location.href = '/Atenea/jsp/menu/frontal.jsp';
    };

    $scope.getDuracionEnMinutos = function () {
        if (document.getElementById('sinLimiteTiempoId').checked) {
            return 0;
        }
        let tiempo = document.getElementById('tiempoExamenId').value;
        let partes = tiempo.split(':');
        let horas = parseInt(partes[0]);
        let minutos = parseInt(partes[1]);
        return horas * 60 + minutos;
    };

    $scope.guardar = function (nombreConfiguracion) {
        var configuracionExamen = {
            "configuracionExamen":
                    {"nombreConfiguracion": nombreConfiguracion,
                        "numeroPreguntas": document.getElementById('numeroPreguntasId').value,
                        "multirespuesta": document.getElementById('multirespuestaId').checked,
                        "respuestasXPregunta": document.getElementById('respuestasPorPreguntaId').value,
                        "tiempo": $scope.getDuracionEnMinutos(),
                        "mostrarTiempo": document.getElementById('mostrarTiempoRestanteId').checked,
                        "mostrarFeedback": document.getElementById('mostrarFeedbackId').checked,
                        "mostrarInmediatamente": document.getElementById('mostrarRespuestaCorrectaAlMarcarId').checked,
                        "usarTodasCategorias": document.getElementById('todasCategoriasMismoPesoId').checked,
                        "categoriasSeleccionadasMismoPeso": document.getElementById('categoriasSeleccionadasMismoPesoId').checked
                    },
                    "categoriasExamen": $scope.categoriasSeleccionadasPesos.map(({ categoria, ...rest }) => rest)
        };
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/GuardarConfiguracionExamenServlet',
            data: JSON.stringify(configuracionExamen),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if (response.data === CORRECTO) {
                mostrarVentanaConfirmacion(ngDialog, 'Se ha guardado correctamente la configuración de examen');
                $scope.ventanaConfirmacion.close();
            } else {
                mostrarVentanaAviso(ngDialog, 'Ha ocurrido un error al realizar el guardado de la configuración:' + response);
            }
        });
    };

    $scope.guardarPlantilla = function (nombreConfiguracion) {
        const datos = {
            "nombreConfiguracion": nombreConfiguracion
        };
        $http({
            method: 'POST',
            url: '/Atenea/Servlet/ComprobarNombreUnicoConfiguracionServlet',
            data: datos,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if (response.data === EXISTE) {
                mostrarVentanaAviso(ngDialog, 'El nombre utilizado ya existe');
                $scope.noExisteNombreConfiguracion = false;
            } else {
                $scope.guardar(nombreConfiguracion);
            }
        });
    };

    $scope.mostrarVentanaGuardar = function () {
        $scope.ventanaConfirmacion = ngDialog.open({
            template: '/Atenea/jsp/examen/configuracionExamen/confirmacionGuardadoExamen.jsp',
            cache: false,
            height: '300px',
            width: '600px',
            scope: $scope});
    };
});
