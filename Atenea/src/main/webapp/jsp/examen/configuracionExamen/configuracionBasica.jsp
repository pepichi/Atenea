<%-- 
    Document   : configuracionBasica
    Created on : 1 may 2024, 17:31:22
    Author     : Pepichi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" > 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="contenedorElementoConfiguracionExamen">
            <label for="numeroPreguntasId">Número de preguntas</label>
            <input id="numeroPreguntasId" type="number" min="1" value="100"/>
        </div>
        <div class="contenedorElementoConfiguracionExamen">
            <label for="respuestasPorPreguntaId">Respuestas por pregunta</label>
            <input id="respuestasPorPreguntaId" type="number" min="2" value="4"/>
        </div>
        <div class="contenedorElementoConfiguracionExamen">
            <label for="multirrespuestaId">Multirrespuesta</label>
            <input id="multirrespuestaId" type="checkbox" />            
        </div>
    </body>
</html>