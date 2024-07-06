<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : confirmacionGuardadoExamen
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body style="height: 300px; width: 750px">
        <h1 style="text-align: center;">Confirmación de guardado</h1>
        <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;">
            <label for="nombrePlantillaId"  uib-tooltip="Nombre de la plantilla">Nombre</label>
            <input id="nombrePlantillaId" type="text" maxlength="255" style="width: 80%" ng-model="nombreConfiguracion">
        </div>
        <div class="botoneraConfiguracionExamen" style="position: absolute">
            <button class="botonConfiguracionExamen" uib-tooltip="Cerrar ventana" tooltip-placement="top" ng-click="closeThisDialog()">Cancelar</button>
            <button class="botonConfiguracionExamen" ng-click="guardarPlantilla(nombreConfiguracion)"  ng-disabled="!nombreConfiguracion" ng-show="nombreConfiguracion">Guardar</button>
        </div>
    </body>
</html>
