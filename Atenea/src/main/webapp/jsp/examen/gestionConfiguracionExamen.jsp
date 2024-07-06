<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : gestionConfiguracionExamen
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app="configuracionExamen">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src = "/Atenea/js/angular/ui-bootstrap-tpls-2.5.0.min.js"></script>
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
            <button class="botonConfiguracionExamen" ng-click="irAExamen()">Realizar examen</button>
        </div>
    </body>
</html>
