/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Arbitro;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
import com.fut.model.TablaEquipos;
import com.fut.model.Tarjeta;
import com.fut.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author YeisonUrrea
 */
public class AdminChampionShipDTO {
   
    
    private List<Equipo> listaEquiposClasificados;  //lista de equipos clasificados precargada
    private List<Jornada> listaJornada; 
    
    // dialogo agregar partido a la jornada
    private int itemGroupPlaySelJor; //dto selesccion de grupo o playoffg
    private int itemGroupJor; //dto grupo selecionado
    private int itemPlayoffJor; //dto  playoff seleccionado
    private int itemMatchSelJor; //id partido seleccionado para agregar a la jornada
    private List<Partido> listPartidosItemJor; //lista desplegable de los partido para agregar a jornada
    private boolean renItemGroup;
    private boolean renItemPlayoff;
    private List<Grupo> listGruposItemJor;
    private List<PlayOff> listPlayoffItemJor;
    private List<Arbitro> listArbitro;
    private Arbitro arbitro; //objeto para insertar y actualizar
    private Campeonato campeonato;

    
    public Campeonato getCampeonato() {
        if (campeonato == null) {
            campeonato = new Campeonato();
        }
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
    

    public Arbitro getArbitro() {
        if (arbitro == null) {
            arbitro = new Arbitro();
        }
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public List<Arbitro> getListArbitro() {
         if (listArbitro == null) {
            listArbitro = new ArrayList<>();
        }
        return listArbitro;
    }

    public void setListArbitro(List<Arbitro> listArbitro) {
        this.listArbitro = listArbitro;
    }

    
    
    
    public List<Grupo> getListGruposItemJor() {
        if (listGruposItemJor == null) {
            listGruposItemJor = new ArrayList<>();
        }
        return listGruposItemJor;
    }

    public void setListGruposItemJor(List<Grupo> listGruposItemJor) {
        this.listGruposItemJor = listGruposItemJor;
    }

    public List<PlayOff> getListPlayoffItemJor() {
        if (listPlayoffItemJor == null) {
            listPlayoffItemJor = new ArrayList<>();
        }
        return listPlayoffItemJor;
    }

    public void setListPlayoffItemJor(List<PlayOff> listPlayoffItemJor) {
        this.listPlayoffItemJor = listPlayoffItemJor;
    }
    

    
    
    
    public boolean isRenItemGroup() {
        return renItemGroup;
    }

    public void setRenItemGroup(boolean renItemGroup) {
        this.renItemGroup = renItemGroup;
    }

    public boolean isRenItemPlayoff() {
        return renItemPlayoff;
    }

    public void setRenItemPlayoff(boolean renItemPlayoff) {
        this.renItemPlayoff = renItemPlayoff;
    }

    
    
    
    
    public List<Partido> getListPartidosItemJor() {
        if (listPartidosItemJor == null) {
            listPartidosItemJor = new ArrayList<>();
        }
        return listPartidosItemJor;
    }

    public void setListPartidosItemJor(List<Partido> listPartidosItemJor) {
        this.listPartidosItemJor = listPartidosItemJor;
    }
    
   
 

   
    
    
    public List<Equipo> getListaEquiposClasificados() {
        if (listaEquiposClasificados == null) {
            listaEquiposClasificados = new ArrayList<>();
        }
        return listaEquiposClasificados;
    }

    public void setListaEquiposClasificados(List<Equipo> listaEquiposClasificados) {
        this.listaEquiposClasificados = listaEquiposClasificados;
    }

    public int getItemGroupPlaySelJor() {
        return itemGroupPlaySelJor;
    }

    public void setItemGroupPlaySelJor(int itemGroupPlaySelJor) {
        this.itemGroupPlaySelJor = itemGroupPlaySelJor;
    }

    public int getItemGroupJor() {
        return itemGroupJor;
    }

    public void setItemGroupJor(int itemGroupJor) {
        this.itemGroupJor = itemGroupJor;
    }

    public int getItemPlayoffJor() {
        return itemPlayoffJor;
    }

    public void setItemPlayoffJor(int itemPlayoffJor) {
        this.itemPlayoffJor = itemPlayoffJor;
    }

    public int getItemMatchSelJor() {
        return itemMatchSelJor;
    }

    public void setItemMatchSelJor(int itemMatchSelJor) {
        this.itemMatchSelJor = itemMatchSelJor;
    }

    public List<Jornada> getListaJornada() {
        if(listaJornada==null){
            listaJornada = new ArrayList<>();
        }
        return listaJornada;
    }

    public void setListaJornada(List<Jornada> listaJornada) {
        this.listaJornada = listaJornada;
    }

  

    
    
    
}
