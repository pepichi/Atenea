/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
var ngDialogHelper = angular.module('ngDialogHelper', []);
function mostrarVentanaAviso(ngDialog, textoAviso, tiempoMostrado = 2000, ancho = '150 px', alto = '250 px') {
    var dialog = ngDialog.open({
        template: '<img class="warningImage" src="/Atenea/imagenes/error.png" alt="Aviso error realizando operación"/><p class="alerta">' +
                '<p class="notificacionTexto">' + textoAviso + '</p>',
        plain: true,
        closeByDocument: false,
        closeByEscape: false,
        height: alto,
        width: ancho,
        cache: false,
        className: 'ngdialog-theme-default'
    });
    setTimeout(function () {
        dialog.close();
    }, tiempoMostrado);
}

function mostrarVentanaConfirmacion(ngDialog, textoAviso, tiempoMostrado = 2000) {
    var dialog = ngDialog.open({
        template: '<p class="notificacion">' + textoAviso + '</p>',
        plain: true,
        closeByDocument: false,
        closeByEscape: false,
        className: 'ngdialog-theme-default'
    });
    setTimeout(function () {
        dialog.close();
    }, tiempoMostrado);
}

