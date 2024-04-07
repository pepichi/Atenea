package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.helper.ORMHelper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pregunta {
    
    private enum Columnas{
        ACTIVADA, COMENTARIOS, ENUNCIADO, 
        FECHA_INSERCION, FUENTE_PREGUNTA, ID_PREGUNTA
    }
    
    private BigDecimal idPregunta;
    private String enunciado;
    private String fuentePregunta;
    private String comentarios;
    private GregorianCalendar fechaInsercion;
    private boolean activada;
    
    public Pregunta(ResultSet rs)throws SQLException{
        idPregunta = rs.getBigDecimal(Columnas.ID_PREGUNTA.name());
        enunciado = rs.getString(Columnas.ENUNCIADO.name());
        fuentePregunta = rs.getString(Columnas.FUENTE_PREGUNTA.name());
        comentarios = rs.getString(Columnas.COMENTARIOS.name());
        fechaInsercion = ORMHelper.getGregorianCalendar(rs,Columnas.FECHA_INSERCION.name());
        activada = rs.getBoolean(Columnas.ACTIVADA.name());
    }

    public BigDecimal getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(BigDecimal idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getFuentePregunta() {
        return fuentePregunta;
    }

    public void setFuentePregunta(String fuentePregunta) {
        this.fuentePregunta = fuentePregunta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Calendar getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(GregorianCalendar fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }
    
}
