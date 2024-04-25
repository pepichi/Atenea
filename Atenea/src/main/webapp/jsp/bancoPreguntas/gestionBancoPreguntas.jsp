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
        <div ng-controller="controladorEnunciadoPregunta" class="container app">
            <fieldset>
                <legend>Enunciado</legend>
                <text-angular style="display: block" ng-model="enunciado" ></text-angular>
            </fieldset>
            <fieldset>
                <legend>Respuestas</legend>
                <div ng-repeat="n in [].constructor(numeroRespuestas) track by $index">
                    <label>Respuesta {{$index + 1}}</label>
                    <div>
                        <label>Respuesta correcta</label>
                        <input type="checkbox" ng-model="respuestas[$index].esCorrecta">
                    </div>
                    <text-angular ng-model="respuestas[$index].respuesta"></text-angular>
                    <label>Feedback</label>
                    <text-angular ng-model="respuestas[$index].feedback"></text-angular>
                </div>
                <div>
                    <button ng-click="incrementarNumeroRespuestas()">Añadir respuesta</button>
                    <button ng-click="decrementarNumeroRespuestas()">Quitar respuesta</button>
                </div>
            </fieldset>
            <fieldset>
                <legend>Comentarios</legend>
                <text-angular style="display: block" ng-model="comentario" ></text-angular>
            </fieldset>
            <fieldset>
                <legend>Fuente</legend>
                <text-angular style="display: block" ng-model="fuente" ></text-angular>
            </fieldset>
            <fieldset>
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
            <div style="display: flex;
                 justify-content: center;
                 align-items: center;">
                <button ng-click="validar()">Validar</button>
            </div>
        </div>
    </body>
</html>
