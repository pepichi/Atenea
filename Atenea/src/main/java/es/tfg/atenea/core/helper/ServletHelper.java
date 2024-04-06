package es.tfg.atenea.core.helper;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ServletHelper {

    private ServletHelper() {

    }

    public static void responseObject(Object objeto, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(new Gson().toJson(objeto));
    }

    public static void responseObjectSafe(Object objeto, HttpServletResponse response){
        try {
            response.setContentType(MediaType.APPLICATION_JSON);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(new Gson().toJson(objeto));
        } catch (IOException ex) {
            LogHelper.anotarExcepcionLog(ex);
        }
    }
}
