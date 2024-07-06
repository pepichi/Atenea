/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author José Puerta Cardelles
 */
public abstract class ServletBase extends HttpServlet {

    private static final String MENSAJE_RENDIMIENTO = "El servlet: '%s', ha tardado en ejecutarse: %s milisegundos";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        long tiempoInicio = System.currentTimeMillis();
        super.service(req, resp);
        long tiempoFinal = System.currentTimeMillis();
        LogHelper.anotarRendimentoLog(String.format(MENSAJE_RENDIMIENTO, req.getRequestURI(), tiempoFinal - tiempoInicio));
    }
}
