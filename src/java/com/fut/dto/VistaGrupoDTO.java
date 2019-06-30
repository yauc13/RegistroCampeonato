/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Campeonato;
import com.fut.model.Grupo;
import com.fut.model.Partido;
import com.fut.model.TablaEquipos;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
public class VistaGrupoDTO {
    private Campeonato campeonato; //campeonato en sesion
    private Grupo grupo;
    
    //tab tabla de posicciones
    private List<TablaEquipos> listaPosiciones; 
    private TablaEquipos teamTableSel;
    private List<Partido> listMatchByTeam;

    public VistaGrupoDTO() {
        //campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");  
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<TablaEquipos> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<TablaEquipos> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }

    public List<Partido> getListMatchByTeam() {
        if(listMatchByTeam==null){
            listMatchByTeam = new ArrayList<>();
        }
        return listMatchByTeam;
    }

    public void setListMatchByTeam(List<Partido> listMatchByTeam) {
        this.listMatchByTeam = listMatchByTeam;
    }

    public TablaEquipos getTeamTableSel() {
        return teamTableSel;
    }

    public void setTeamTableSel(TablaEquipos teamTableSel) {
        this.teamTableSel = teamTableSel;
    }
    
    
    
    
    
    
    
    
    
}
