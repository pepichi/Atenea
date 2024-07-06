/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.helper.ORMHelper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author José Puerta Cardelles
 */
public class Respuesta {
    private enum Columnas{
        CORRECTA, FECHA_INSERCION, FEEDBACK, 
        ID_PREGUNTA, ID_RESPUESTA, RESPUESTA
    }
    
    private BigDecimal idRespuesta;
    private BigDecimal idPregunta;
    private String respuesta;
    private String feedback;
    private boolean correcta;
    private GregorianCalendar fechaInsercion;
    private String enumerador;
    
    public Respuesta(){
        
    }
    
    public Respuesta(ResultSet rs)throws SQLException{
        idRespuesta = rs.getBigDecimal(Columnas.ID_RESPUESTA.name());
        idPregunta = rs.getBigDecimal(Columnas.ID_PREGUNTA.name());
        respuesta = rs.getString(Columnas.RESPUESTA.name());
        feedback = rs.getString(Columnas.FEEDBACK.name());
        correcta = rs.getBoolean(Columnas.CORRECTA.name());
        fechaInsercion = ORMHelper.getGregorianCalendar(rs, Columnas.FECHA_INSERCION.name());
    }
    
    public Respuesta(String respuesta, String feedback, boolean esCorrecta){
        this.respuesta = respuesta;
        this.feedback = feedback;
        this.correcta = esCorrecta;
    }

    public BigDecimal getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(BigDecimal idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public BigDecimal getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(BigDecimal idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public GregorianCalendar getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(GregorianCalendar fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getEnumerador() {
        return enumerador;
    }

    public void setEnumerador(String enumerador) {
        this.enumerador = enumerador;
    }
    
    
}
