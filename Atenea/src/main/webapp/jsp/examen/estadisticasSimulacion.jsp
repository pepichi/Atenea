<%-- 
    Document   : estadisticasSimulacion.jsp
    Created on : 5 jun 2024, 17:51:47
    Author     : José Puerta Cardelles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body style="height: 300px; width: 750px">
        <h1 style="text-align: center;">Estadísticas del examen</h1>
        <div>
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;">
                <label for="aciertosId"">Aciertos</label>
                <span id="aciertosId">{{estadisticas.aciertos}}</span>
            </div>
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;">
                <label for="fallosId"">Fallos</label>
                <span id="fallosId">{{estadisticas.fallos}}</span>
            </div>
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;">
                <label for="sinContestarId"">Sin contestar</label>
                <span id="sinContestarId">{{estadisticas.sinContestar}}</span>
            </div>
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;">
                <label for="tiempoUtilizadoId"">Tiempo (segundos)</label>
                <span id="tiempoUtilizadoId">{{estadisticas.numeroSegundos}}</span>
            </div>            
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;" ng-show="estadisticas.subidaNivel">
                <label for="nuevoNivelId"">¡¡¡Felicidades!!!, has alcanzado un nuevo nivel</label>
                <span id="nuevoNivelId">{{estadisticas.nuevoNivel}}</span>
            </div>             
            <div class="contenedorElementoConfiguracionExamen" style="margin-left: 5%; width: 90%; margin-top: 25px;" ng-show="estadisticas.nuevoTrofeo">
                <label for="nuevoTrofeoId"">¡¡¡Felicidades!!!, has obtenido un nuevo trofeo</label>
                <span id="nuevoTrofeoId">{{estadisticas.trofeo.descripcion}}</span>
            </div>
        </div>
    </body>
</html>
