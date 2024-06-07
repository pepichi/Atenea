/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion.pdf;

import com.mysql.cj.util.StringUtils;
import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.Pregunta;
import es.tfg.atenea.core.banco.Respuesta;
import es.tfg.atenea.core.helper.LogHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author José Puerta Cardelles
 */
public class ImportadorPreguntasPDFHelper {

    private static final String EXPRESION_REGULAR_RESPUESTA = "^[a-zA-Z]\\) ";
    private static final String EXPRESION_REGULAR_INICIO_PREGUNTA = "^\\d+\\) ";
    private static final String FUENTE = "Importación PDF desde fichero: %s";

    private ImportadorPreguntasPDFHelper() {

    }

    public static List<BloquePreguntaRespuestas> getBloquesPreguntasRespuestas(byte[] buffer, String nombreFichero, String patronEnunciado) {
        List<BloquePreguntaRespuestas> bloques = new ArrayList<>();
        String texto = extraerTextoDelPDF(buffer);
        List<String> lineas = Arrays.asList(texto.split(System.lineSeparator()));
        List<Respuesta> respuestas = new ArrayList<>();
        BloquePreguntaRespuestas bloque = new BloquePreguntaRespuestas();
        boolean inicioPregunta = false;
        boolean inicioRespuesta = false;
        String pregunta = "";
        String respuesta = "";
        int indice = 1;
        for (String linea : lineas) {
            if (StringUtils.isEmptyOrWhitespaceOnly(linea) && inicioRespuesta) {
                inicioRespuesta = false;
                respuestas.add(getRespuesta(respuesta));
                bloque.setRespuestas(respuestas);
                bloques.add(bloque);
                respuestas = new ArrayList<>();
                bloque = new BloquePreguntaRespuestas();
                respuesta = "";
            }
            if (inicioPregunta && !esInicioRespuesta(linea)) {
                pregunta += " " + getParteEnunciado(linea);
            }
            if (inicioRespuesta && !esInicioRespuesta(linea) && !esInicioPregunta(linea)) {
                respuesta += " " + getParteRespuesta(linea);
            }
            if (inicioRespuesta && (esInicioRespuesta(linea) || esInicioPregunta(linea))) {
                inicioRespuesta = false;
                respuestas.add(getRespuesta(respuesta));
                if (esInicioPregunta(linea)) {
                    bloque.setRespuestas(respuestas);
                    bloques.add(bloque);
                    respuestas = new ArrayList<>();
                    bloque = new BloquePreguntaRespuestas();
                }
                respuesta = "";
            }
            if (esInicioPregunta(linea)) {
                inicioPregunta = true;
                bloque = new BloquePreguntaRespuestas();
                pregunta = getParteEnunciado(linea);
            } else {
                if (esInicioRespuesta(linea) && inicioPregunta) {
                    inicioPregunta = false;
                    bloque.setPregunta(getPregunta(pregunta, nombreFichero));
                    pregunta = "";
                }
                if (esInicioRespuesta(linea) && !inicioPregunta) {
                    inicioRespuesta = true;
                    respuesta += getParteRespuesta(linea);
                }
            }
            if (indice == lineas.size() && !StringUtils.isNullOrEmpty(respuesta)) {
                respuestas.add(getRespuesta(respuesta));
                bloque.setRespuestas(respuestas);
                bloques.add(bloque);
            }
            indice++;
        }
        return bloques;
    }

    private static boolean esInicioPregunta(String linea) {
        Pattern patronAQuitar = Pattern.compile(EXPRESION_REGULAR_INICIO_PREGUNTA);
        Matcher matcher = patronAQuitar.matcher(linea);
        return matcher.find();
    }

    private static String getParteEnunciado(String linea) {
        Pattern patronAQuitar = Pattern.compile(EXPRESION_REGULAR_INICIO_PREGUNTA);
        Matcher matcher = patronAQuitar.matcher(linea);
        return matcher.find() ? linea.substring(matcher.end()).trim() : linea.trim();
    }

    private static boolean esInicioRespuesta(String linea) {
        Pattern patronAQuitar = Pattern.compile(EXPRESION_REGULAR_RESPUESTA);
        Matcher matcher = patronAQuitar.matcher(linea);
        return matcher.find();
    }

    private static Pregunta getPregunta(String enunciado, String nombreFichero) {
        Pregunta pregunta = new Pregunta();
        pregunta.setActivada(true);
        pregunta.setFuentePregunta(String.format(FUENTE, nombreFichero));
        pregunta.setEnunciado(getCadenaSinDoblesEspacios(enunciado));
        return pregunta;
    }

    private static String getParteRespuesta(String linea) {
        Pattern patronAQuitar = Pattern.compile(EXPRESION_REGULAR_RESPUESTA);
        Matcher matcher = patronAQuitar.matcher(linea);
        return matcher.find() ? linea.substring(matcher.end()).trim() : linea.trim();
    }

    private static Respuesta getRespuesta(String enunciado) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(getCadenaSinDoblesEspacios(enunciado));
        return respuesta;
    }

    private static String getCadenaSinDoblesEspacios(String elemento) {
        int tamanoOriginal;
        String nuevaCadena = elemento;
        do {
            tamanoOriginal = nuevaCadena.length();
            nuevaCadena = nuevaCadena.replace("  ", " ");
        } while (tamanoOriginal > nuevaCadena.length());
        return nuevaCadena;

    }

    public static String extraerTextoDelPDF(byte[] pdfBytes) {
        String text = "";
        try (PDDocument document = Loader.loadPDF(pdfBytes)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            LogHelper.anotarExcepcionLog(e);
        }
        return text;
    }
}
