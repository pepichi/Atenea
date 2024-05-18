<%-- 
    Document   : seleccionCategorias
    Created on : 2 may 2024, 18:37:40
    Author     : Pepichi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <fieldset ng-controller="listaCategoriaController">
        <legend>Categorías</legend>
        <div>
            <div style="float: left">
                <h3>Categorías disponibles</h3>
                <ul>
                    <li ng-repeat="item in categoriasNoSeleccionadas" ng-click="moverADerecha($index)">
                        {{ item.nombre}}
                    </li>
                </ul>
            </div>
            <div style="float: right">
                <h3>Categorías asignadas</h3>
                <ul>
                    <li ng-repeat="item in categoriasSeleccionadas" ng-click="moverAIzquierda($index)">
                        {{ item.nombre}}
                    </li>
                </ul>
            </div>
        </div>
    </fieldset>
</html>
