/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ResultadoSimulacionExamen {
    
    private List<BloquePreguntaRespuestas> bloques;
    private List<RespuestasPreguntaSimulacion> respuestas;
    private BigDecimal idConfiguracion;
    private Double tiempoUtilizado;

    public List<BloquePreguntaRespuestas> getBloques() {
        return bloques;
    }

    public void setBloques(List<BloquePreguntaRespuestas> bloques) {
        this.bloques = bloques;
    }

    public List<RespuestasPreguntaSimulacion> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestasPreguntaSimulacion> respuestas) {
        this.respuestas = respuestas;
    }

    public BigDecimal getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(BigDecimal idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public Double getTiempoUtilizado() {
        return tiempoUtilizado;
    }

    public void setTiempoUtilizado(Double tiempoUtilizado) {
        this.tiempoUtilizado = tiempoUtilizado;
    }
    
}
