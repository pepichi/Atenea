<%-- 
    Document   : configuracionPregunta
    Created on : 1 may 2024, 17:55:39
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
            <label for="mostrarFeedbackId">Mostrar feedback si tiene</label>
            <input id="mostrarFeedbackId" type="checkbox" checked/>            
        </div>
        <div class="contenedorElementoConfiguracionExamen">
            <label for="mostrarRespuestaCorrectaAlMarcarId">Mostrar respuesta correcta al marcar</label>
            <input id="mostrarRespuestaCorrectaAlMarcarId" type="checkbox"/>            
        </div>
    </body>
</html>
