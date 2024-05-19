/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion.aiken;

import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.InsertarPreguntaBancoHelper;
import es.tfg.atenea.core.constants.ResponseTypes;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.FileHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "ImportarPreguntasAikenServlet", urlPatterns = {"/Servlet/ImportarPreguntasAikenServlet"})
@MultipartConfig
public class ImportarPreguntasAikenServlet extends HttpServlet {

    private static final String ERROR_IMPORTACION = "Ha ocurrido un error durante el proceso de importación del método AIKEN";
    private static final String NOMBRE_FICHERO_PART = "file";
    private static final String NOMBRE_DATOS_PART = "datos";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (Connection conexion = DataBaseHelper.getConexionTransacional()) {
            ConfiguracionAiken configuracion = ServletHelper.getObjectFromPart(request, ConfiguracionAiken.class, NOMBRE_DATOS_PART);
            Part ficheroPart = request.getPart(NOMBRE_FICHERO_PART);
            String nombreFichero = FileHelper.getNombreFichero(ficheroPart);
            byte[] fichero = ficheroPart.getInputStream().readAllBytes();
            List<BloquePreguntaRespuestas> bloques = ImportadorPreguntasAikenHelper.getBloquesPreguntasRespuestas(fichero, nombreFichero, configuracion.getPatron());
            InsertarPreguntaBancoHelper.insertarBloques(conexion, bloques, configuracion.getCategorias());
            conexion.commit();
            ServletHelper.responseObject(ResponseTypes.CORRECTO, response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_IMPORTACION, ERROR_IMPORTACION, ex, response);
        }
    }
}
