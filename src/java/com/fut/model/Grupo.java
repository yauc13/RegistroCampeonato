/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;


import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Yeison
 */
public class Grupo implements Serializable{
    private int idGrupo;
    private String nombreGrupo;
    private int idCampeonato;
    private int idUsuario;
    private int numClasificados;
    private Campeonato campeonato;
    private List<Equipo> listEquipos;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public int getNumClasificados() {
        return numClasificados;
    }

    public void setNumClasificados(int numClasificados) {
        this.numClasificados = numClasificados;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public List<Equipo> getListEquipos() {
        return listEquipos;
    }

    public void setListEquipos(List<Equipo> listEquipos) {
        this.listEquipos = listEquipos;
    }
    
    
    
    
    
}
