/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion.aiken;

import com.mysql.cj.util.StringUtils;
import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.Pregunta;
import es.tfg.atenea.core.banco.Respuesta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José Puerta Cardelles
 */
public class ImportadorPreguntasAikenHelper {

    private static final String EXPRESION_REGULAR_RESPUESTA = "^\\w+\\) ";
    private static final Pattern PATRON_RESPUESTA = Pattern.compile(EXPRESION_REGULAR_RESPUESTA);
    private static final String PATRON_RESPUESTA_CORRECTA = "ANSWER: ";
    private static final String FUENTE = "Importación AIKEN desde fichero: %s";

    private ImportadorPreguntasAikenHelper() {

    }

    public static List<BloquePreguntaRespuestas> getBloquesPreguntasRespuestas(byte[] buffer, String nombreFichero, String patronEnunciado) {
        List<BloquePreguntaRespuestas> bloques = new ArrayList<>();
        String texto = new String(buffer);
        List<String> lineas = Arrays.asList(texto.split(System.lineSeparator()));
        List<Respuesta> respuestas = new ArrayList<>();
        BloquePreguntaRespuestas bloque = new BloquePreguntaRespuestas();
        boolean inicioPregunta = true;
        int numeroPreguntas = 0;
        for (String linea : lineas) {
            if (inicioPregunta) {
                respuestas = new ArrayList<>();
                bloque = new BloquePreguntaRespuestas();
                inicioPregunta = false;
            }
            if (PATRON_RESPUESTA.matcher(linea).find()) {
                anadirRespuestaLinea(linea, respuestas);
            } else if (linea.startsWith(PATRON_RESPUESTA_CORRECTA)) {
                asignarRespuestaCorrecta(linea, respuestas);
            } else if (!StringUtils.isNullOrEmpty(linea.trim())) {
                bloque.setPregunta(getPreguntaLinea(linea, patronEnunciado, nombreFichero));
                numeroPreguntas++;
            } else {
                bloque.setRespuestas(respuestas);
                bloques.add(bloque);
                inicioPregunta = true;
            }
        }
        if (numeroPreguntas != bloques.size()) {
            bloque.setRespuestas(respuestas);
            bloques.add(bloque);
        }
        return bloques;
    }

    private static void anadirRespuestaLinea(String linea, List<Respuesta> respuestas) {
        Respuesta respuesta = new Respuesta();
        Matcher matcher = PATRON_RESPUESTA.matcher(linea);
        if (matcher.find()) {
            respuesta.setRespuesta(linea.substring(matcher.end()).trim());
            respuesta.setEnumerador(matcher.group().replace(")", "").trim());
            respuestas.add(respuesta);
        }
    }

    private static void asignarRespuestaCorrecta(String linea, List<Respuesta> respuestas) {
        String enumeradorCorrecto = linea.replace(PATRON_RESPUESTA_CORRECTA, "").trim();
        for (Respuesta respuesta : respuestas) {
            if (enumeradorCorrecto.equals(respuesta.getEnumerador())) {
                respuesta.setCorrecta(true);
            }
        }
    }

    private static Pregunta getPreguntaLinea(String linea, String patronEnunciado, String nombreFichero) {
        String enunciadoAUtilizar = linea.trim();
        if (!StringUtils.isNullOrEmpty(patronEnunciado)) {
            Pattern patronAQuitar = Pattern.compile(patronEnunciado.replace("#", "\\w+"));
            Matcher matcher = patronAQuitar.matcher(linea);
            enunciadoAUtilizar = matcher.find() ? linea.substring(matcher.end()).trim() : linea.trim();
        }
        Pregunta pregunta = new Pregunta();
        pregunta.setActivada(true);
        pregunta.setEnunciado(enunciadoAUtilizar);
        pregunta.setFuentePregunta(String.format(FUENTE, nombreFichero));
        return pregunta;
    }
}
