<%-- 
    Copyright 2024 José Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : configuracionPregunta
    Created on : 1 may 2024, 17:31:22
    Author     : José Puerta Cardelles
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
