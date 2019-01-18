/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;
import java.util.List;

/**
 *
 * @author YeisonUrrea
 */
public class PlayOff {
   int idPlayOff;
   String namePlayOff;
   int numPartidos;
   int idCampeonato;
   List<Partido> listMatch;
   List<Equipo> listTeam;

    public int getIdPlayOff() {
        return idPlayOff;
    }

    public void setIdPlayOff(int idPlayOff) {
        this.idPlayOff = idPlayOff;
    }

    public String getNamePlayOff() {
        return namePlayOff;
    }

    public void setNamePlayOff(String namePlayOff) {
        this.namePlayOff = namePlayOff;
    }

    public int getNumPartidos() {
        return numPartidos;
    }

    public void setNumPartidos(int numPartidos) {
        this.numPartidos = numPartidos;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public List<Partido> getListMatch() {
        return listMatch;
    }

    public void setListMatch(List<Partido> listMatch) {
        this.listMatch = listMatch;
    }

    public List<Equipo> getListTeam() {
        return listTeam;
    }

    public void setListTeam(List<Equipo> listTeam) {
        this.listTeam = listTeam;
    }
    
    
   
   
}
