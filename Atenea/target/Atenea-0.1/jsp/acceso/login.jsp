<html>

    <form action="/Atenea/Servlet/LoginServlet" method="post" ua-error="error-msg">
        <input name="nombreUsuario" placeholder="Nombre de usuario" required><br>
        <input name="password" placeholder="Contraseña" type="password" required><br>
        <button type="submit">Enviar</button>
        <p id="error-msg"></p>
        <% if ("01".equals( request.getParameter("mensaje"))) { %>
        <p>"El usuario o la contraseña son incorrectos"</p>
        <% }%>
    </form>
</html>
