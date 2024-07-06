/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class BloquePreguntaRespuestas {
    
    public BloquePreguntaRespuestas(Pregunta pregunta, List<Respuesta> respuestas){
        this.pregunta = pregunta;
        this.respuestas = respuestas;
    }
    
    public BloquePreguntaRespuestas(){
        
    }
    
    private Pregunta pregunta;
    private List<Respuesta> respuestas; 

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
    
}
