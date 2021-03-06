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
public class Jugador implements Serializable{
    int idJugador;
    String nombreJugador;
    String fechaNacimiento;
    Date birthday;
    int golJugador;
    int idEquipoJugador;
    int idUsuario;
    private String fotoJugador;
    private List<Gol> listGolJug; 
    private int numGol;
    private String nombreEquipo;

    public int getNumGol() {
        return numGol;
    }

    public void setNumGol(int numGol) {
        this.numGol = numGol;
    }
    
    

    public List<Gol> getListGolJug() {
        return listGolJug;
    }

    public void setListGolJug(List<Gol> listGolJug) {
        this.listGolJug = listGolJug;
    }
    
    

    public String getFotoJugador() {
        return fotoJugador;
    }

    public void setFotoJugador(String fotoJugador) {
        this.fotoJugador = fotoJugador;
    }
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
        

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    
    
    
    
    
    
}
