/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.GolDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.dao.TarjetaDao;
import com.fut.dto.PlanillaPartidoDTO;
import com.fut.model.Equipo;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.Tarjeta;
import com.fut.model.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
@ManagedBean
@ViewScoped

public class PlanillaPartidoBean implements Serializable{
    
    private PlanillaPartidoDTO dto;
    private final PartidoDao daoPar;
    private final JugadorDao daoJug;  
    private final GolDao daoGol;
    private final TarjetaDao daoTar;

    public PlanillaPartidoBean() {
        dto = new PlanillaPartidoDTO();
        daoPar = new PartidoDao();
        daoJug = new JugadorDao();
        daoGol = new GolDao();
        daoTar = new TarjetaDao();
        dto.setUsuario((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
        dto.setPartido((Partido) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("partido"));
        dto.setGrupo((Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo"));
        enableButtonStart(dto);
    }
    
    
    
    public void listarPlanillasInicio() {     
        
        if(this.isPostBack() == false){                 
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoB()));
        listarGoles();        
        }   
    }
    
    public void listarPlanillas(){     
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoB()));
        listarGoles();
    }
    
    public void listarGoles() {        
        dto.setListaGolesA(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoA()));
        dto.setListaGolesB(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoB()));
    }
     
    public String finalizarPartido() {
        String direccion="";       
        dto.getPartido().setGolA(dto.getListaGolesA().size());
        dto.getPartido().setGolB(dto.getListaGolesB().size());
        daoPar.finalizarPartido(dto.getPartido());
        if (dto.getGrupo() != null) {
            direccion = "vistaGrupo?faces-redirect=true";
        } else if (dto.getPlayOff() != null) {
            direccion = "listaPartidoPlayOff?faces-redirect=true";
        }       
        return direccion;
    }
    
     public void iniciarPartido() {        
        boolean resp =daoPar.iniciarPartido(dto.getPartido());
        if(resp){   
             enableButtonStartNext();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "partido iniciado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "el partido no se pudo iniciar");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }      
        
    }
    
    public void anotarGol(int jug, int equ, Partido par) {
        Gol gol = new Gol();
        gol.setIdJugador(jug);
        gol.setIdEquipo(equ);
        gol.setIdPartido(par.getIdPartido());
        if(equ==par.getIdEquipoA()){
            gol.setIdEquipoB(par.getIdEquipoB());
        }else{
            gol.setIdEquipoB(par.getIdEquipoA());
        }
        daoGol.insertGol(gol);
        this.listarPlanillas();
    }
    
    public void anotarTarjeta(String typeTar,int jug, int equ, Partido par){
        Tarjeta tar = new Tarjeta();
        tar.setIdJugador(jug);
        tar.setIdEquipo(equ);
        tar.setIdPartido(par.getIdPartido());
        tar.setTypeCard(typeTar);
        if(equ==par.getIdEquipoA()){
            tar.setIdEquipoB(par.getIdEquipoB());
        }else{
            tar.setIdEquipoB(par.getIdEquipoA());
        }
        if(daoTar.insertTarjeta(tar)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Tarjeta Registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar tarjeta");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }

    private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    private void enableButtonStart(PlanillaPartidoDTO dto){
        if(dto.getPartido().getEstadoPartido().equals("Por Jugar")){
            dto.setEnaBtnIniciar(false);
            dto.setEnaBtnFin(true);
            dto.setEnaBtnTar(true);
            dto.setEnaBtngol(true);
        }else if(dto.getPartido().getEstadoPartido().equals("Jugando")){
            dto.setEnaBtnIniciar(true);
            dto.setEnaBtnFin(false);
            dto.setEnaBtnTar(false);
            dto.setEnaBtngol(false);
        }if(dto.getPartido().getEstadoPartido().equals("Finalizado")){
            dto.setEnaBtnIniciar(true);
            dto.setEnaBtnFin(true);
            dto.setEnaBtnTar(true);
            dto.setEnaBtngol(true);
        }
    }
    
     private void enableButtonStartNext(){
        
            dto.setEnaBtnIniciar(true);
            dto.setEnaBtnFin(false);
            dto.setEnaBtnTar(false);
            dto.setEnaBtngol(false);
  
    }
     

    public PlanillaPartidoDTO getDto() {
        return dto;
    }

    public void setDto(PlanillaPartidoDTO dto) {
        this.dto = dto;
    }
     
    
    
}
