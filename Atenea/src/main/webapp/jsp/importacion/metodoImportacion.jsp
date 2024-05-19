<!DOCTYPE html>
<html lang="es" ng-app="metodoImportacionApp">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src="/Atenea/js/angular/controladores/metodoImportacion.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
    </head>
    <body>
        <div class="centrado" ng-controller="metodoImportacionController">
            <div ng-repeat="entradaMenu in entradasMenu">
                <div class="boton botonCentrado" ng-click="redirigir(entradaMenu.url)">{{ entradaMenu.nombre }}</div>
            </div>
        </div>
    </body>
</html>