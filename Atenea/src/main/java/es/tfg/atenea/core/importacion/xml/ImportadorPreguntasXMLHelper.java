/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion.xml;

import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.Pregunta;
import es.tfg.atenea.core.banco.Respuesta;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author José Puerta Cardelles
 */
public class ImportadorPreguntasXMLHelper {

    private static final String FUENTE = "Importación XML desde fichero: %s";

    private ImportadorPreguntasXMLHelper() {

    }

    public static List<BloquePreguntaRespuestas> getBloquesPreguntasRespuestas(byte[] buffer, String nombreFichero) throws XPathExpressionException {
        List<BloquePreguntaRespuestas> bloques = new ArrayList<>();
        BloquePreguntaRespuestas bloque;
        List<Respuesta> respuestas;
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("/quiz/question[@type='multichoice']");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        InputSource inputSource = new InputSource(inputStream);
        NodeList listaNodosPreguntas = (NodeList) expr.evaluate(inputSource, XPathConstants.NODESET);
        for (int i = 0; i < listaNodosPreguntas.getLength(); i++) {
            Node nodoPregunta = listaNodosPreguntas.item(i);
            bloque = new BloquePreguntaRespuestas();
            if (nodoPregunta.getNodeType() == Node.ELEMENT_NODE) {
                Element pregunta = (Element) nodoPregunta;
                bloque.setPregunta(getPregunta(pregunta.getElementsByTagName("questiontext").item(0).getTextContent().trim(), nombreFichero));
                NodeList listaRespuestas = pregunta.getElementsByTagName("answer");
                respuestas = new ArrayList<>();
                for (int j = 0; j < listaRespuestas.getLength(); j++) {
                    Element elementoRespuesta = (Element) listaRespuestas.item(j);
                    String respuesta = elementoRespuesta.getElementsByTagName("text").item(0).getTextContent().trim();
                    String feedBack = elementoRespuesta.getElementsByTagName("feedback").item(0).getTextContent().trim();
                    String fraccion = elementoRespuesta.getAttribute("fraction");
                    respuestas.add(getRespuesta(respuesta, feedBack, fraccion));
                }
                bloque.setRespuestas(respuestas);
                bloques.add(bloque);
            }
        }
        return bloques;
    }

    private static Pregunta getPregunta(String enunciado, String nombreFichero) {
        Pregunta pregunta = new Pregunta();
        pregunta.setActivada(true);
        pregunta.setFuentePregunta(String.format(FUENTE, nombreFichero));
        pregunta.setEnunciado(enunciado);
        return pregunta;
    }

    private static Respuesta getRespuesta(String enunciado, String feedBack, String fraccion) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(enunciado);
        respuesta.setFeedback(feedBack);
        int valorFraccion = Integer.parseInt(fraccion);
        respuesta.setCorrecta(valorFraccion > 0);
        return respuesta;
    }
}
