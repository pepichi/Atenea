/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.gamificacion;

/**
 *
 * @author José Puerta Cardelles
 */
public class Gamificacion {
    
    private int nivel;
    private Trofeo trofeo;
    
    public Gamificacion(){
        
    }
    
    public Gamificacion(int nivel, Trofeo trofeo){
        this.nivel = nivel;
        this.trofeo = trofeo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Trofeo getTrofeo() {
        return trofeo;
    }

    public void setTrofeo(Trofeo trofeo) {
        this.trofeo = trofeo;
    }
}
