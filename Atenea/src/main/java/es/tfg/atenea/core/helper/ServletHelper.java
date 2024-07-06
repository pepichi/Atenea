/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

/**
 *
 * @author José Puerta Cardelles
 */
public class ServletHelper {

    private ServletHelper() {

    }

    public static void responderMensajeError(String mensaje, String mensajeUsuario, Exception ex, HttpServletResponse response) {
        LogHelper.anotarExcepcionLog(mensaje, ex);
        responseObjectSafe(mensajeUsuario, response);
    }

    public static void responseObject(Object objeto, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(new Gson().toJson(objeto));
    }

    public static void responseObjectSafe(Object objeto, HttpServletResponse response) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(new Gson().toJson(objeto));
        } catch (IOException ex) {
            LogHelper.anotarExcepcionLog(ex);
        }
    }

    public static <T> T getRequestObject(HttpServletRequest request, Class<T> tipo) throws IOException {
        return new Gson().fromJson(getJsonFromRequest(request), tipo);
    }

    public static <T> T getRequestObject(HttpServletRequest request, Type tipo) throws IOException {
        return new Gson().fromJson(getJsonFromRequest(request), tipo);
    }

    public static <T> T getRequestObject(HttpServletRequest request, Class<T> tipo, String nombreParametro) throws IOException {        
        return tipo.cast(new JSONObject(getJsonFromRequest(request)).get(nombreParametro));
    }

    public static <T> T getObjectFromPart(HttpServletRequest request, Class<T> tipo, String nombreParte) throws IOException, ServletException {
        Part datosPart = request.getPart(nombreParte);
        byte[] datos = datosPart.getInputStream().readAllBytes();
        String datosJson = new String(datos);
        return new Gson().fromJson(datosJson, tipo);
    }

    private static String getJsonFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder jsonBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }
        return jsonBody.toString();
    }
}
