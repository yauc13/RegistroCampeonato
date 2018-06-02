/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

/**
 *
 * @author Yeison
 */
public class Jornada {
    int idJornada;
    String nombreJornada;   
    int idPartidoJornada;
    int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getIdPartidoJornada() {
        return idPartidoJornada;
    }

    public void setIdPartidoJornada(int idPartidoJornada) {
        this.idPartidoJornada = idPartidoJornada;
    }

 

   
    
    
}
