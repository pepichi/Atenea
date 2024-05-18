<%-- 
    Document   : configuracionCategoria
    Created on : 1 may 2024, 18:46:25
    Author     : Pepichi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="contenedorElementoConfiguracionExamen">
            <label for="todasCategoriasMismoPesoId">Usar todas las categorías con el mismo peso</label>
            <input id="todasCategoriasMismoPesoId" type="checkbox"  ng-model="noMostrarSeleccionCategoria" />            
        </div>
        <div ng-show="!noMostrarSeleccionCategoria" >
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
            <div class="contenedorElementoConfiguracionExamen">
                <label for="categoriasSeleccionadasMismoPesoId">Usar las categorías seleccionadas con el mismo peso</label>
                <input id="categoriasSeleccionadasMismoPesoId" type="checkbox"  ng-model="noMostrarSeleccionCategoriaPeso"/>            
            </div>
        </div>
        <div ng-show="!noMostrarSeleccionCategoriaPeso" ng-init="count = 0" >
            <hr class="lineaHorizontal80">  
            <div ng-repeat="item in categoriasSeleccionadasPesos" class="contenedorElementoConfiguracionExamen">
                <label>{{ item.categoria}}</label>
                <input type="number" min="0" max="100" step="1" ng-model="item.peso" >
            </div>
        </div>
    </body>
</html>
