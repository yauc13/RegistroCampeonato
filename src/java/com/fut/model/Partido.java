/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Yeison
 */
public class Partido implements Serializable  {
    int idPartido;
    int idEquipoA;
    int idEquipoB;
    int idGrupo;
    int idPlayOff;
    int idArbitro;
    int golA;
    int golB;
    int penalA;
    int penalB;
    int idUsuario;
    Grupo grupo;
    PlayOff playOff;
    Equipo equipoA;
    Equipo equipoB;
    Gol [] golEA; //captura los goles de A para saber si el partido lo gana
    Gol [] golEB;
    private String estadoPartido;
    private int idJornada;
    private Jornada jornada;
    private Date fechaPartido;
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
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

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public int getIdPlayOff() {
        return idPlayOff;
    }

    public void setIdPlayOff(int idPlayOff) {
        this.idPlayOff = idPlayOff;
    }

    public PlayOff getPlayOff() {
        return playOff;
    }

    public void setPlayOff(PlayOff playOff) {
        this.playOff = playOff;
    }

    public int getPenalA() {
        return penalA;
    }

    public void setPenalA(int penalA) {
        this.penalA = penalA;
    }

    public int getPenalB() {
        return penalB;
    }

    public void setPenalB(int penalB) {
        this.penalB = penalB;
    }

    public Date getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }
    
    
    
    
    


    
    
    
}
