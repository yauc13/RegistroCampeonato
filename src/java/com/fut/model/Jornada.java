/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Yeison
 */
public class Jornada implements Serializable{
    private int idJornada;
    private String nombreJornada;   
    private int idCampeonato;
    private int idUsuario;
    private Date fechaJornada;
    private List<Partido> listMatch;
    

    public Jornada() {
    }
    
    

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

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    public Date getFechaJornada() {
        return fechaJornada;
    }

    public void setFechaJornada(Date fechaJornada) {
        this.fechaJornada = fechaJornada;
    }

    public List<Partido> getListMatch() {
        return listMatch;
    }

    public void setListMatch(List<Partido> listMatch) {
        this.listMatch = listMatch;
    }

    
 

 

   
    
    
}
