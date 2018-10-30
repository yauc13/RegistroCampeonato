/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Campeonato;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
import com.fut.model.Usuario;
import java.util.List;

/**
 *
 * @author YeisonUrrea
 */
public class PlanillaPartidoDTO {
    private Partido partido;
    private Grupo grupo;
    private Usuario usuario;
    private PlayOff playOff;
    private Campeonato campeonato;
    private List<Gol> listaGolesA;
    private List<Gol> listaGolesB;
    private List<Jugador> listaJugadoresA;
    private List<Jugador> listaJugadoresB;

    public PlanillaPartidoDTO() {
         partido = new Partido();
         grupo = new Grupo();
         usuario = new Usuario();
          playOff = new PlayOff();
          campeonato = new Campeonato();
          
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PlayOff getPlayOff() {
        return playOff;
    }

    public void setPlayOff(PlayOff playOff) {
        this.playOff = playOff;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public List<Gol> getListaGolesA() {
        return listaGolesA;
    }

    public void setListaGolesA(List<Gol> listaGolesA) {
        this.listaGolesA = listaGolesA;
    }

    public List<Gol> getListaGolesB() {
        return listaGolesB;
    }

    public void setListaGolesB(List<Gol> listaGolesB) {
        this.listaGolesB = listaGolesB;
    }

    public List<Jugador> getListaJugadoresA() {
        return listaJugadoresA;
    }

    public void setListaJugadoresA(List<Jugador> listaJugadoresA) {
        this.listaJugadoresA = listaJugadoresA;
    }

    public List<Jugador> getListaJugadoresB() {
        return listaJugadoresB;
    }

    public void setListaJugadoresB(List<Jugador> listaJugadoresB) {
        this.listaJugadoresB = listaJugadoresB;
    }
    
    
    
    
}
