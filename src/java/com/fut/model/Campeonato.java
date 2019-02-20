/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.io.Serializable;

/**
 *
 * @author Yeison Andres Urrea Chaves
 */
public class Campeonato implements Serializable {
    int idCampeonato;
    String nombreCampeonato;
    int idUsuario;
    int costoPlanilla;
    int costoAma;
    int costoAzu;
    int costoRoj;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNombreCampeonato() {
        return nombreCampeonato;
    }

    public void setNombreCampeonato(String nombreCampeonato) {
        this.nombreCampeonato = nombreCampeonato;
    }

    public int getCostoPlanilla() {
        return costoPlanilla;
    }

    public void setCostoPlanilla(int costoPlanilla) {
        this.costoPlanilla = costoPlanilla;
    }

    public int getCostoAma() {
        return costoAma;
    }

    public void setCostoAma(int costoAma) {
        this.costoAma = costoAma;
    }

    public int getCostoAzu() {
        return costoAzu;
    }

    public void setCostoAzu(int costoAzu) {
        this.costoAzu = costoAzu;
    }

    public int getCostoRoj() {
        return costoRoj;
    }

    public void setCostoRoj(int costoRoj) {
        this.costoRoj = costoRoj;
    }
    
    
    
}
