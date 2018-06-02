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
public class Equipo implements Serializable{
    int idEquipo;
    String nombreEquipo;   
    int pgEquipo;
    int peEquipo;
    int ppEquipo;
    int gfEquipo;
    int gcEquipo;
    int idGrupoEquipo;
    int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public int getPgEquipo() {
        return pgEquipo;
    }

    public void setPgEquipo(int pgEquipo) {
        this.pgEquipo = pgEquipo;
    }

    public int getPeEquipo() {
        return peEquipo;
    }

    public void setPeEquipo(int peEquipo) {
        this.peEquipo = peEquipo;
    }

    public int getPpEquipo() {
        return ppEquipo;
    }

    public void setPpEquipo(int ppEquipo) {
        this.ppEquipo = ppEquipo;
    }

    public int getGfEquipo() {
        return gfEquipo;
    }

    public void setGfEquipo(int gfEquipo) {
        this.gfEquipo = gfEquipo;
    }

    public int getGcEquipo() {
        return gcEquipo;
    }

    public void setGcEquipo(int gcEquipo) {
        this.gcEquipo = gcEquipo;
    }
    
    
    
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getIdGrupoEquipo() {
        return idGrupoEquipo;
    }

    public void setIdGrupoEquipo(int idGrupoEquipo) {
        this.idGrupoEquipo = idGrupoEquipo;
    }

    
    
}
