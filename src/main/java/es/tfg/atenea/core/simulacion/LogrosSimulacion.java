/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.gamificacion.Trofeo;

/**
 *
 * @author José Puerta Cardelles
 */
public class LogrosSimulacion {
    
    private int aciertos;
    private int fallos;
    private int sinContestar;
    private boolean subidaNivel;
    private int nuevoNivel;
    private boolean nuevoTrofeo;
    private Trofeo trofeo;
    private Double numeroSegundos;

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getSinContestar() {
        return sinContestar;
    }

    public void setSinContestar(int sinContestar) {
        this.sinContestar = sinContestar;
    }

    public boolean isSubidaNivel() {
        return subidaNivel;
    }

    public void setSubidaNivel(boolean subidaNivel) {
        this.subidaNivel = subidaNivel;
    }

    public int getNuevoNivel() {
        return nuevoNivel;
    }

    public void setNuevoNivel(int nuevoNivel) {
        this.nuevoNivel = nuevoNivel;
    }

    public boolean isNuevoTrofeo() {
        return nuevoTrofeo;
    }

    public void setNuevoTrofeo(boolean nuevoTrofeo) {
        this.nuevoTrofeo = nuevoTrofeo;
    }

    public Trofeo getTrofeo() {
        return trofeo;
    }

    public void setTrofeo(Trofeo trofeo) {
        this.trofeo = trofeo;
    }

    public Double getNumeroSegundos() {
        return numeroSegundos;
    }

    public void setNumeroSegundos(Double numeroSegundos) {
        this.numeroSegundos = numeroSegundos;
    }   
    
}
