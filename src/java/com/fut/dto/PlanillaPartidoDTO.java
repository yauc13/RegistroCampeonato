/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Arbitro;
import com.fut.model.Campeonato;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.Penal;
import com.fut.model.PlayOff;
import com.fut.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author YeisonUrrea
 */
public class PlanillaPartidoDTO {
    private Partido partido;
    private Grupo grupo;
    private Usuario usuario;
    private PlayOff playOff;
    private Jornada jornada;
    private Campeonato campeonato;
    private List<Gol> listaGolesA;
    private List<Gol> listaGolesB;
    private List<Penal> listaPenalesA;
    private List<Penal> listaPenalesB;
    private List<Jugador> listaJugadoresA;
    private List<Jugador> listaJugadoresB;
    private List<Arbitro> listaArbitros;
    private boolean enaBtnIniciar = false;
    private boolean enaBtnFin = true;
    private boolean enaBtngol = true;
    private boolean enaBtnTar = true;
    private boolean enaBtnPenal = true;
    private boolean renPanelPenal = false;
    private int idArbitro;
    private String fotoPlanilla;
    private UploadedFile imgFile; 

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

    public boolean isEnaBtnIniciar() {
        return enaBtnIniciar;
    }

    public void setEnaBtnIniciar(boolean enaBtnIniciar) {
        this.enaBtnIniciar = enaBtnIniciar;
    }

    public boolean isEnaBtnFin() {
        return enaBtnFin;
    }

    public void setEnaBtnFin(boolean enaBtnFin) {
        this.enaBtnFin = enaBtnFin;
    }

    public boolean isEnaBtngol() {
        return enaBtngol;
    }

    public void setEnaBtngol(boolean enaBtngol) {
        this.enaBtngol = enaBtngol;
    }

    public boolean isEnaBtnTar() {
        return enaBtnTar;
    }

    public void setEnaBtnTar(boolean enaBtnTar) {
        this.enaBtnTar = enaBtnTar;
    }

    public boolean isRenPanelPenal() {
        return renPanelPenal;
    }

    public void setRenPanelPenal(boolean renPanelPenal) {
        this.renPanelPenal = renPanelPenal;
    }

    public boolean isEnaBtnPenal() {
        return enaBtnPenal;
    }

    public void setEnaBtnPenal(boolean enaBtnPenal) {
        this.enaBtnPenal = enaBtnPenal;
    }

    public List<Penal> getListaPenalesA() {
        if(listaPenalesA==null){
            listaPenalesA = new ArrayList<>();
        }
        return listaPenalesA;
    }

    public void setListaPenalesA(List<Penal> listaPenalesA) {
        this.listaPenalesA = listaPenalesA;
    }

    public List<Penal> getListaPenalesB() {
        if(listaPenalesB==null){
            listaPenalesB = new ArrayList<>();
        }
        return listaPenalesB;
    }

    public void setListaPenalesB(List<Penal> listaPenalesB) {
        this.listaPenalesB = listaPenalesB;
    }

    public List<Arbitro> getListaArbitros() {
        if(listaArbitros==null){
            listaArbitros = new ArrayList<>();
        }
        return listaArbitros;
    }

    public void setListaArbitros(List<Arbitro> listaArbitros) {
        this.listaArbitros = listaArbitros;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public Jornada getJornada() {
        if(jornada==null){
            jornada = new Jornada();
        }
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public String getFotoPlanilla() {
        return fotoPlanilla;
    }

    public void setFotoPlanilla(String fotoPlanilla) {
        this.fotoPlanilla = fotoPlanilla;
    }

    public UploadedFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(UploadedFile imgFile) {
        this.imgFile = imgFile;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
