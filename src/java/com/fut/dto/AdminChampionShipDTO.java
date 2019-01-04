/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

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
    private Grupo grupo;
    private PlayOff playOff;
    private Campeonato campeonato; //campeonato seleccioonado que viene desde otro bean
    private Usuario usuario;
    private Jornada jornada; //jornada seleccionada
    private Jornada jornadaNew; //jornada para crear nueva
    private Partido partidoSelecJor;
    private Tarjeta tarjetaSel;
    private List<Grupo> listaGrupo;
    private List<TablaEquipos> listaPosiciones;
    private List<Equipo> listaEquipos;
    private List<Jugador> listaGoleadores;
    private List<Tarjeta> listaTarjetas;
    private List<Tarjeta> listaTarjetasCan;
    private List<Tarjeta> listaTarjetasPag;
    private List<Jornada> listaJornada;
    private List<PlayOff> listaPlayOff;
    private List<Partido> listaPartidosJornada;
    private String accion;
   
    private int rowSelJor; //columna de jornada expandida
    
    private List<SelectItem> selectItemOneGrupos; //para seleccionar grupo segun el campeonato
    private List<SelectItem> selectItemOnePartidos; //para seleccionar grupo segun el campeonato
    private int itemGrupoSelected;
    private int itemPartidoSelected; //partido seleccionado para agregar a una jornada

    public Grupo getGrupo() {
        if( grupo == null){
            grupo = new Grupo();
        }
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public PlayOff getPlayOff() {
        if( playOff == null){
          playOff  = new PlayOff();
        }
        return playOff;
    }

    public void setPlayOff(PlayOff playOff) {
        this.playOff = playOff;
    }

    public Campeonato getCampeonato() {
        if( campeonato == null){
            campeonato = new Campeonato();
        }
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Usuario getUsuario() {
        if( usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jornada getJornada() {
        if( jornada == null){
            jornada = new Jornada();
        }
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Jornada getJornadaNew() {
        if( jornadaNew == null){
            jornadaNew = new Jornada();
        }
        return jornadaNew;
    }

    public void setJornadaNew(Jornada jornadaNew) {
        this.jornadaNew = jornadaNew;
    }

    public Partido getPartidoSelecJor() {
        if( partidoSelecJor == null){
            partidoSelecJor = new Partido();
        }
        return partidoSelecJor;
    }

    public void setPartidoSelecJor(Partido partidoSelecJor) {
        this.partidoSelecJor = partidoSelecJor;
    }

    public Tarjeta getTarjetaSel() {
        if( tarjetaSel == null){
            tarjetaSel = new Tarjeta();
        }
        return tarjetaSel;
    }

    public void setTarjetaSel(Tarjeta tarjetaSel) {
        this.tarjetaSel = tarjetaSel;
    }

    public List<Grupo> getListaGrupo() {
        if( listaGrupo == null){
            listaGrupo = new ArrayList<>();
        }
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public List<TablaEquipos> getListaPosiciones() {
        if( listaPosiciones == null){
            listaPosiciones = new ArrayList<>();
        }
        return listaPosiciones;
    }

    public void setListaPosiciones(List<TablaEquipos> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }

    public List<Equipo> getListaEquipos() {
        if( listaEquipos == null){
           listaEquipos = new ArrayList<>();
        }
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public List<Jugador> getListaGoleadores() {
        if( listaGoleadores == null){
           listaGoleadores = new ArrayList<>();
        }
        return listaGoleadores;
    }

    public void setListaGoleadores(List<Jugador> listaGoleadores) {
        this.listaGoleadores = listaGoleadores;
    }

    public List<Tarjeta> getListaTarjetas() {
        if( listaTarjetas == null){
           listaTarjetas = new ArrayList<>();
        }
        return listaTarjetas;
    }

    public void setListaTarjetas(List<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public List<Tarjeta> getListaTarjetasCan() {
        if( listaTarjetasCan == null){
           listaTarjetasCan = new ArrayList<>();
        }
        return listaTarjetasCan;
    }

    public void setListaTarjetasCan(List<Tarjeta> listaTarjetasCan) {
        this.listaTarjetasCan = listaTarjetasCan;
    }

    public List<Tarjeta> getListaTarjetasPag() {
        if( listaTarjetasPag == null){
           listaTarjetasPag = new ArrayList<>();
        }
        return listaTarjetasPag;
    }

    public void setListaTarjetasPag(List<Tarjeta> listaTarjetasPag) {
        this.listaTarjetasPag = listaTarjetasPag;
    }

    public List<Jornada> getListaJornada() {
        if( listaJornada == null){
           listaJornada = new ArrayList<>();
        }
        return listaJornada;
    }

    public void setListaJornada(List<Jornada> listaJornada) {
        this.listaJornada = listaJornada;
    }

    public List<PlayOff> getListaPlayOff() {
        if( listaPlayOff == null){
           listaPlayOff = new ArrayList<>();
        }
        return listaPlayOff;
    }

    public void setListaPlayOff(List<PlayOff> listaPlayOff) {
        this.listaPlayOff = listaPlayOff;
    }

    public List<Partido> getListaPartidosJornada() {
        if( listaPartidosJornada == null){
          listaPartidosJornada  = new ArrayList<>();
        }
        return listaPartidosJornada;
    }

    public void setListaPartidosJornada(List<Partido> listaPartidosJornada) {
        this.listaPartidosJornada = listaPartidosJornada;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getRowSelJor() {
        return rowSelJor;
    }

    public void setRowSelJor(int rowSelJor) {
        this.rowSelJor = rowSelJor;
    }

    public List<SelectItem> getSelectItemOneGrupos() {
        if( selectItemOneGrupos == null){
           selectItemOneGrupos = new ArrayList<>();
        }
        return selectItemOneGrupos;
    }

    public void setSelectItemOneGrupos(List<SelectItem> selectItemOneGrupos) {
        this.selectItemOneGrupos = selectItemOneGrupos;
    }

    public List<SelectItem> getSelectItemOnePartidos() {
        if( selectItemOnePartidos == null){
           selectItemOnePartidos = new ArrayList<>();
        }
        return selectItemOnePartidos;
    }

    public void setSelectItemOnePartidos(List<SelectItem> selectItemOnePartidos) {
        this.selectItemOnePartidos = selectItemOnePartidos;
    }

    public int getItemGrupoSelected() {
        return itemGrupoSelected;
    }

    public void setItemGrupoSelected(int itemGrupoSelected) {
        this.itemGrupoSelected = itemGrupoSelected;
    }

    public int getItemPartidoSelected() {
        return itemPartidoSelected;
    }

    public void setItemPartidoSelected(int itemPartidoSelected) {
        this.itemPartidoSelected = itemPartidoSelected;
    }
    
    
    
    
}
