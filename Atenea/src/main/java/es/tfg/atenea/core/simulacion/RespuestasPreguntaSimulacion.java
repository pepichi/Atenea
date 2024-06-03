/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class RespuestasPreguntaSimulacion {
    
    private BigDecimal idPregunta;
    private List<BigDecimal> idRespuestas;

    public BigDecimal getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(BigDecimal idPregunta) {
        this.idPregunta = idPregunta;
    }

    public List<BigDecimal> getIdRespuestas() {
        return idRespuestas;
    }

    public void setIdRespuestas(List<BigDecimal> idRespuestas) {
        this.idRespuestas = idRespuestas;
    }

}
