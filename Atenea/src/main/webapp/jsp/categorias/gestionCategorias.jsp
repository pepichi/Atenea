<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="categoria">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <link rel="stylesheet" type="text/css" href="/Atenea/css/ui-grid/ui-grid.min.css">
        <script src="/Atenea/js/ui-grid/ui-grid.min.js"></script>
        <script src="/Atenea/js/ui-grid/ui-grid.cellnav.min.js"></script>
        <script src="/Atenea/js/ui-grid/ui-grid.core.min.js"></script>
        <script src="/Atenea/js/ui-grid/ui-grid.edit.min.js"></script>
        <script src="/Atenea/js/ui-grid/ui-grid.row-edit.min.js"></script>
        <script src="/Atenea/js/ui-grid/ui-grid.selection.min.js"></script>
        <script src="/Atenea/js/angular/controladores/categorias.js"></script>
    </head>
    <body>
        <h1 style="text-align: center">Categorías</h1>
        <div ng-controller="categoriaController">
            <div id="grid1" ui-grid="gridOptions" class="grid" ui-grid-edit ui-grid-row-edit ui-grid-cellNav ui-grid-selection></div>
            <button ng-click="anadirFila()">Añadir Fila</button>
            <button ng-click="borrarFilasSeleccionadas()">Borrar filas seleccionadas</button>
        </div>
    </body>
</html>
