/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionExamen {

    private enum Columnas {
        ID_CONFIGURACION_EXAMEN, NOMBRE_CONFIGURACION, NUMERO_PREGUNTAS, RESPUESTA_PREGUNTA,
        MULTIRRESPUESTA, LIMITE_TIEMPO, MOSTRAR_TIEMPO, MOSTRAR_FEEDBACK, MOSTRAR_INMEDIATAMENTE,
        USAR_TODAS_CATEGORIAS, CATEGORIAS_SELECCIONADAS_MISMO_PESO
    }

    private BigDecimal idConfiguracionExamen;
    private String nombreConfiguracion;
    private int numeroPreguntas;
    private boolean multirrespuesta;
    private int respuestasXPregunta;
    private int tiempo;
    private boolean mostrarTiempo;
    private boolean mostrarFeedback;
    private boolean mostrarInmediatamente;
    private boolean usarTodasCategorias;
    private boolean categoriasSeleccionadasMismoPeso;

    public ConfiguracionExamen(ResultSet rs) throws SQLException {
        idConfiguracionExamen = rs.getBigDecimal(Columnas.ID_CONFIGURACION_EXAMEN.name());
        nombreConfiguracion = rs.getString(Columnas.NOMBRE_CONFIGURACION.name());
        numeroPreguntas = rs.getInt(Columnas.NUMERO_PREGUNTAS.name());
        multirrespuesta = rs.getBoolean(Columnas.MULTIRRESPUESTA.name());
        respuestasXPregunta = rs.getInt(Columnas.RESPUESTA_PREGUNTA.name());
        tiempo = rs.getInt(Columnas.LIMITE_TIEMPO.name());
        mostrarTiempo = rs.getBoolean(Columnas.MOSTRAR_TIEMPO.name());
        mostrarFeedback = rs.getBoolean(Columnas.MOSTRAR_FEEDBACK.name());
        mostrarInmediatamente = rs.getBoolean(Columnas.MOSTRAR_INMEDIATAMENTE.name());
        usarTodasCategorias = rs.getBoolean(Columnas.USAR_TODAS_CATEGORIAS.name());
        categoriasSeleccionadasMismoPeso = rs.getBoolean(Columnas.CATEGORIAS_SELECCIONADAS_MISMO_PESO.name());
    }

    public BigDecimal getIdConfiguracionExamen() {
        return idConfiguracionExamen;
    }

    public void setIdConfiguracionExamen(BigDecimal idConfiguracionExamen) {
        this.idConfiguracionExamen = idConfiguracionExamen;
    }

    public String getNombreConfiguracion() {
        return nombreConfiguracion;
    }

    public void setNombreConfiguracion(String nombreConfiguracion) {
        this.nombreConfiguracion = nombreConfiguracion;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public boolean isMultirrespuesta() {
        return multirrespuesta;
    }

    public void setMultirrespuesta(boolean multirrespuesta) {
        this.multirrespuesta = multirrespuesta;
    }

    public int getRespuestasXPregunta() {
        return respuestasXPregunta;
    }

    public void setRespuestasXPregunta(int respuestasXPregunta) {
        this.respuestasXPregunta = respuestasXPregunta;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isMostrarTiempo() {
        return mostrarTiempo;
    }

    public void setMostrarTiempo(boolean mostrarTiempo) {
        this.mostrarTiempo = mostrarTiempo;
    }

    public boolean isMostrarFeedback() {
        return mostrarFeedback;
    }

    public void setMostrarFeedback(boolean mostrarFeedback) {
        this.mostrarFeedback = mostrarFeedback;
    }

    public boolean isMostrarInmediatamente() {
        return mostrarInmediatamente;
    }

    public void setMostrarInmediatamente(boolean mostrarInmediatamente) {
        this.mostrarInmediatamente = mostrarInmediatamente;
    }

    public boolean isUsarTodasCategorias() {
        return usarTodasCategorias;
    }

    public void setUsarTodasCategorias(boolean usarTodasCategorias) {
        this.usarTodasCategorias = usarTodasCategorias;
    }

    public boolean isCategoriasSeleccionadasMismoPeso() {
        return categoriasSeleccionadasMismoPeso;
    }

    public void setCategoriasSeleccionadasMismoPeso(boolean categoriasSeleccionadasMismoPeso) {
        this.categoriasSeleccionadasMismoPeso = categoriasSeleccionadasMismoPeso;
    }

}
