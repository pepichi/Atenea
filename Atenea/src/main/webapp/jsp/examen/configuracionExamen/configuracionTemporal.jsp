<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : configuracionTemporal
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body >
        <div class="contenedorElementoConfiguracionExamen">
            <label for="sinLimiteTiempoId">Sin límite de tiempo</label>
            <input id="sinLimiteTiempoId" type="checkbox"  ng-model="noMostrarConfiguracionConTiempo" />            
        </div>
        <div ng-show="!noMostrarConfiguracionConTiempo">
            <div class="contenedorElementoConfiguracionExamen">
                <label for="tiempoExamenId">Tiempo para el examen</label>
                <input id="tiempoExamenId" type="time" id="duracion" name="duracion" min="00:10" max="05:00" step="600" required value="01:00:00">
            </div>
            <div class="contenedorElementoConfiguracionExamen">
                <label for="mostrarTiempoRestanteId">Mostrar tiempo restante</label>
                <input id="mostrarTiempoRestanteId" type="checkbox"/>            
            </div>
        </div>
    </body>
</html>
