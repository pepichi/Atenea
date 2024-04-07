<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app = "app">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <script src="/Atenea/js/angular/controladores/app.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
    </head>
    <body>
        <h1>Hello World!</h1>
        <div ng-controller="controladorEnunciadoPregunta" class="container app">
            <text-angular ng-model="htmlContent" ></text-angular>
            <h3>Preview:</h3>
            <div ng-bind-html="htmlContent"></div>
        </div>
    </body>
</html>
