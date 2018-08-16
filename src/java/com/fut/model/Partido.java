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
public class Partido {
    int idPartido;
    int idEquipoA;
    int idEquipoB;
    int idGrupo;
    int golA;
    int golB;
    int idUsuario;
    Grupo grupo;
    Equipo equipoA;
    Equipo equipoB;
    Gol [] golEA;
    Gol [] golEB;
    private String estadoPartido;
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
        

    public int getGolA() {
        return golA;
    }

    public void setGolA(int golA) {
        this.golA = golA;
    }

    public int getGolB() {
        return golB;
    }

    public void setGolB(int golB) {
        this.golB = golB;
    }
    
    

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getIdEquipoA() {
        return idEquipoA;
    }

    public void setIdEquipoA(int idEquipoA) {
        this.idEquipoA = idEquipoA;
    }

    public int getIdEquipoB() {
        return idEquipoB;
    }

    public void setIdEquipoB(int idEquipoB) {
        this.idEquipoB = idEquipoB;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Equipo getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        this.equipoB = equipoB;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Gol[] getGolEA() {
        return golEA;
    }

    public void setGolEA(Gol[] golEA) {
        this.golEA = golEA;
    }

    public Gol[] getGolEB() {
        return golEB;
    }

    public void setGolEB(Gol[] golEB) {
        this.golEB = golEB;
    }

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }


    
    
    
}
