<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : aiken
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app="aikenApp">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src="/Atenea/js/angular/controladores/importadores/aiken.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
    </head>
    <body>
        <h1 class="h1MetodoImportacion">Importación en formato AIKEN</h1>
        <div ng-controller="subidaFicheroController">
            <fieldset class="categoriaContainer centrado">
                <hr class="lineaHorizontal80">
                <legend>Fichero de importación</legend>
                <p>Aquí hay que seleccionar el fichero que tiene las preguntas en formato AIKEN</p>
                <div class="contenedorElementoConfiguracionExamen paddingArribaAbajo25"> 
                    <label for="ficheroImportacionAikenId">Fichero a importar</label>
                    <input id="ficheroImportacionAikenId" type="file" file-model="fichero" required/>
                </div>
            </fieldset>
            <div class="centrado">
                <fieldset class="categoriaContainer">
                    <hr class="lineaHorizontal80">
                    <legend>Categorías</legend>
                    <div>
                        <div style="float: left">
                            <h3>Categorías disponibles</h3>
                            <ul class="elementoLista">
                                <li ng-repeat="item in categoriasNoSeleccionadas" ng-click="moverADerecha($index)"  >
                                    {{ item.nombre}}
                                </li>
                            </ul>
                        </div>
                        <div style="float: right">
                            <h3>Categorías asignadas</h3>
                            <ul class="elementoLista">
                                <li ng-repeat="item in categoriasSeleccionadas" ng-click="moverAIzquierda($index)">
                                    {{ item.nombre}}
                                </li>
                            </ul>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="categoriaContainer">
                    <hr class="lineaHorizontal80">
                    <legend>Patrón de numeración del enunciado</legend>
                    <p style="text-align: left">Aquí puede indicar el patrón de la numeración de la pregunta para que al importar no se incluya. 
                        Los símbolos #, se sustituiran por el número o letra de la pregunta.</p>
                    <p style="text-align: left">Por ejemplo, Pregunta 1: --> Patrón: Pregunta #:</p>
                    <div class="contenedorElementoConfiguracionExamen paddingArribaAbajo25">
                        <label for="patronId">Patrón</label>
                        <input id="patronId" type="text" ng-model="patron" />
                    </div>
                </fieldset>
            </div>
            <div class="botoneraImportar" >
                <button class="botonConfiguracionExamen" ng-click="subirFichero()">Importar</button>
            </div>
        </div>
    </body>
</html>
