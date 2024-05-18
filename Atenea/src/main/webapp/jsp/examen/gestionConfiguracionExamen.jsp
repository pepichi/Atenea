<%-- 
    Document   : gestionConfiguracionExamen
    Created on : 30 abr 2024, 21:03:49
    Author     : Pepichi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app="configuracionExamen">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src="/Atenea/js/angular/controladores/listaCategorias.js"></script>
        <script src="/Atenea/js/angular/controladores/configuracionExamen.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
        <meta charset="UTF-8">
    </head>
    <body ng-controller="AcordeonCtrl">
        <div >
            <div class="acordeon centrado">
                <div class="panel" ng-repeat="elemento in elementos" ng-click="mostrarPanel($index)" ng-class="{ 'active': esActivo($index) }">
                    <div class="panel-header boton panelCentrado">{{ elemento.titulo}}</div>
                    <div class="panel-body " ng-include="elemento.contenido"> ng-show="esActivo($index)">
                    </div>
                </div>
            </div>
        </div>
        <div class="botoneraConfiguracionExamen" >
            <button class="botonConfiguracionExamen" uib-tooltip="Volver al menú" tooltip-placement="top" ng-click="irAMenu()">Volver</button>
            <button class="botonConfiguracionExamen" ng-click="mostrarVentanaGuardar()">Guardar</button>
            <button class="botonConfiguracionExamen">Realizar examen</button>
        </div>
    </body>
</html>
