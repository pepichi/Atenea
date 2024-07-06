<%-- 
    Copyright 2024 Jos� Puerta Cardelles
    Permiso otorgado bajo la Licencia MIT
    Document   : login
    Created on : 1 may 2024, 17:31:22
    Author     : Jos� Puerta Cardelles
--%>

<!DOCTYPE html>
<html lang="es">
    <form action="/Atenea/Servlet/LoginServlet" method="post" ua-error="error-msg">
        <input name="nombreUsuario" placeholder="Nombre de usuario" required><br>
        <input name="password" placeholder="Contrase�a" type="password" required><br>
        <button type="submit">Enviar</button>
        <p id="error-msg"></p>
        <% if ("01".equals( request.getParameter("mensaje"))) { %>
        <p>"El usuario o la contrase�a son incorrectos"</p>
        <% }%>
    </form>
</html>
