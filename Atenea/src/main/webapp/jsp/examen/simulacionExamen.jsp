<%-- 
    Document   : simulacionExamen
    Created on : 19 may 2024, 11:33:21
    Author     : José Puerta Cardelles
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app="simulacionExamenApp">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsSelectBootstrap.jspf"%>
        <script src="/Atenea/js/angular/controladores/simulacionExamen.js"></script>
        <script>
            $(document).ready(function () {
                $('.selectpicker').selectpicker();
            });
        </script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
        <meta charset="UTF-8">
    </head>
    <body ng-controller="seleccionTipoExamenCtrl">
        <h1 class="h1MetodoImportacion">Simulación de examen</h1>
        <div id="insigniasId" class="gamificacion" >
            <div ng-show="existeTrofeo" style="display: grid">
                <div class="insigniaCircular">
                    <img src="/Atenea/imagenes/trofeo.png" alt="imagen de un trofeo obtenido con el uso de la aplicación" width="125px">
                </div>
                <span>{{datosGamificacion.trofeo.nombre}}</span>
                <span>Nivel: {{datosGamificacion.nivel}}</span>
            </div>
            <div ng-show="!existeTrofeo" style="display: grid">
                <img src="/Atenea/imagenes/caraTriste.png" alt="imagen de una cara triste" width="125px">
                <span>Sin trofeos</span>
                <span>Nivel: {{datosGamificacion.nivel}}</span>
            </div>
        </div>
        <div id="seleccionConfiguracionContainerId">
            <fieldset class="categoriaContainer centrado">
                <legend>Tipo de configuración</legend>
                <div class="contenedorElementoConfiguracionExamen paddingArribaAbajo25" id="contenedorSelector">
                    <label for="selectorConfiguracionExamenId">Tipo de configuración</label>
                    <select ng-options="configuracion.idConfiguracionExamen as configuracion.nombreConfiguracion for configuracion in configuraciones"
                            id="selectorConfiguracionExamenId" data-live-search="true" data-live-search-normalize="true" 
                            dir="rtl" ng-model="configuracionSeleccionada" class="selectpicker" 
                            style="font-size:15px" title="Selecciona una configuración de examen" multiple data-max-options="1">
                    </select>
                </div>
            </fieldset>
            <div class="botoneraImportar" ng-show="configuracionSeleccionada" >
                <button class="botonConfiguracionExamen" ng-click="comenzarExamen()">Comenzar examen</button>
            </div>
        </div>
        <div ng-show="cargandoSimulacion" class="d-flex justify-content-center" id="spinnerCargaId">
            <div class="spinner-border text-secondary" role="status" style="width: 6rem; height: 6rem;">
                <span class="sr-only">Cargando...</span>
            </div>
        </div>
        <div class="bloqueoExamen" id="bloqueoExamenId" style="display: none"></div>
        <div ng-show="inicioExamen" style="padding-left: 15%;padding-right: 15%;font-size: x-large">
            <div id="countdown" class="reloj">{{countdown}}</div>
            <div ng-repeat="item in examen">
                <div style="padding-top: 15px;">
                    <span style="font-weight: bold;">Pregunta {{$index + 1}}:</span>
                    {{item.pregunta.enunciado}}
                </div>
                <div ng-repeat="respuestaPregunta in item.respuestas">
                    <input type="checkbox" name="{{item.pregunta.idPregunta}}" id="{{respuestaPregunta.idRespuesta}}" ng-model="seleccion" ng-click="actualizarSeleccion(item.pregunta.idPregunta, respuestaPregunta.idRespuesta)"><span id="s{{respuestaPregunta.idRespuesta}}">  {{respuestaPregunta.respuesta}}</span>
                    <span id="f{{respuestaPregunta.idRespuesta}}" class="feedback" >{{respuestaPregunta.feedback}}</span>
                </div>
            </div>
            <div style="height: 70px"></div>
        </div>
        <div ng-show="inicioExamen" class="botoneraExamen" id="botoneraFinalizarExamenId">
            <button class="botonConfiguracionExamen" ng-click="finalizarExamen()">Finalizar examen</button>
        </div>
        <div class="botoneraExamen" id="botoneraEstadisticaId" style="display: none">
            <button class="botonConfiguracionExamen" ng-click="irAMenu()">Volver</button>
            <button class="botonConfiguracionExamen" ng-click="mostrarEstadisticas()">Ver estadísticas</button>
        </div>
    </body>
</html>
