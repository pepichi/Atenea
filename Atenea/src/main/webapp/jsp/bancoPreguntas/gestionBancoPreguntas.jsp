<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" ng-app = "gestionPreguntas">
    <head>
        <%@include file="/WEB-INF/jspf/comun/configuracionPagina.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsAngular.jspf"%>
        <%@include file="/WEB-INF/jspf/comun/importsSelectBootstrap.jspf"%>
        <script src="/Atenea/js/angular/controladores/gestionPreguntas.js"></script>
        <link rel="stylesheet" href="/Atenea/css/paleta.css">
    </head>
    <body>
        <h1 class="h1MetodoImportacion">Banco de preguntas</h1>
        <div ng-controller="controladorEnunciadoPregunta" class="container app">
            <div  style="display:flex; height: 45px;position: relative;justify-content: center;align-items: center;"  ng-model = "tab" ng-init="tab = 1"> 
                <ul class="nav nav-tabs" style="display: flex">
                    <li class="nav-item active">
                        <a class="nav-link" href="#" ng-click="tab = 1; actualizarTab(1);"  data-toggle="tab" id="tabId1" style="color:#337ab7">Nueva pregunta</a>
                    </li>
                    <li class="nav-item " >
                        <a class="nav-link" href="#" ng-click="tab = 2; actualizarTab(2);"  data-toggle="tab" id="tabId2" style="color:darkgrey">Actualizar</a>
                    </li>
                </ul>
            </div>
            <fieldset ng-show="tab === 2">
                <legend class="tituloBancoPreguntas">Búsqueda de preguntas</legend>
                <select ng-options="pregunta as pregunta.enunciado for pregunta in preguntas"
                        id="selectorPreguntasId" data-live-search="true" data-live-search-normalize="true" 
                        dir="rtl" ng-model="preguntaSeleccionada" class="selectpicker" 
                        style="font-size:15px;" title="Selecciona una pregunta" multiple data-max-options="1" ng-change="recuperarPregunta()" >
                </select>
            </fieldset>
            <fieldset>
                <legend class="tituloBancoPreguntas">Enunciado</legend>
                <text-angular style="display: block" ng-model="enunciado" ></text-angular>
            </fieldset>
            <fieldset>
                <legend class="tituloBancoPreguntas">Respuestas</legend>
                <div ng-repeat="n in [].constructor(numeroRespuestas) track by $index">
                    <label class="numeracionRespuestaPregunta">Respuesta {{$index + 1}}</label>
                    <div>
                        <label class="elementoPregunta">Respuesta correcta</label>
                        <input type="checkbox" ng-model="respuestas[$index].correcta">
                    </div>
                    <text-angular ng-model="respuestas[$index].respuesta"></text-angular>
                    <label class="elementoPregunta">Feedback</label>
                    <text-angular ng-model="respuestas[$index].feedback"></text-angular>
                </div>
                <div class="botoneraConfiguracionExamen" >
                    <button class="botonConfiguracionExamen"  ng-click="incrementarNumeroRespuestas()">Añadir respuesta</button>
                    <button class="botonConfiguracionExamen" ng-click="decrementarNumeroRespuestas()">Quitar respuesta</button>
                </div>
            </fieldset>
            <fieldset>
                <legend class="tituloBancoPreguntas">Comentarios</legend>
                <text-angular style="display: block" ng-model="comentario" ></text-angular>
            </fieldset>
            <fieldset>
                <legend class="tituloBancoPreguntas">Fuente</legend>
                <text-angular style="display: block" ng-model="fuente" ></text-angular>
            </fieldset>
            <fieldset  class="categoriaContainer">
                <legend class="tituloBancoPreguntas">Categorías</legend>
                <div>
                    <div style="float: left" >
                        <h3>Categorías disponibles</h3>
                        <ul class="elementoLista"> 
                            <li ng-repeat="item in categoriasNoSeleccionadas" ng-click="moverADerecha($index)">
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
            <div class="botoneraImportar" >
                <button class="botonConfiguracionExamen" ng-click="validar()">Validar</button>
            </div>
        </div>
    </body>
</html>
