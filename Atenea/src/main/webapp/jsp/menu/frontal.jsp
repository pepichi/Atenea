<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : frontal
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
--%>

<!DOCTYPE html>
<html lang="es" ng-app="menuApp">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src="/Atenea/js/angular/controladores/menu.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
    </head>
    <body>
        <div class="centrado" ng-controller="menuController">
            <div ng-repeat="entradaMenu in entradasMenu">
                <div class="boton botonCentrado" ng-click="redirigir(entradaMenu.url)">{{ entradaMenu.nombre }}</div>
            </div>
        </div>
    </body>
</html>
