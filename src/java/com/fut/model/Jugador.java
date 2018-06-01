/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.io.Serializable;

/**
 *
 * @author Yeison
 */
public class Jugador implements Serializable{
    int idJugador;
    String nombreJugador;
    String fechaNacimiento;
    int golJugador;
    int idEquipoJugador;

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getGolJugador() {
        return golJugador;
    }

    public void setGolJugador(int golJugador) {
        this.golJugador = golJugador;
    }

    public int getIdEquipoJugador() {
        return idEquipoJugador;
    }

    public void setIdEquipoJugador(int idEquipoJugador) {
        this.idEquipoJugador = idEquipoJugador;
    }
    
    
    
}
