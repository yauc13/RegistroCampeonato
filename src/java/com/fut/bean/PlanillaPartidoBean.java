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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    }
    
    
    
    public void listarPlanillasInicio() {       
        if(this.isPostBack() == false){                 
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getEquipoB()));
        listarGoles();        
        }   
    }
    
    public void listarPlanillas(){     
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getEquipoB()));
        listarGoles();
    }
    
    public void listarGoles() {        
        dto.setListaGolesA(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido(), dto.getPartido().getEquipoA()));
        dto.setListaGolesB(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido(), dto.getPartido().getEquipoB()));
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
    
    public void anotarGol(Jugador jug, Equipo equ, Partido par) {
        Gol gol = new Gol();
        gol.setJugador(jug);
        gol.setEquipo(equ);
        gol.setPartido(par);
        daoGol.insertGol(gol);
        this.listarPlanillas();
    }
    
    public void anotarTarjeta(String typeTar,Jugador jug, Equipo equ, Partido par){
        Tarjeta tar = new Tarjeta();
        tar.setIdJugador(jug.getIdJugador());
        tar.setIdEquipo(equ.getIdEquipo());
        tar.setIdPartido(par.getIdPartido());
        tar.setTypeCard(typeTar);
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
     

    public PlanillaPartidoDTO getDto() {
        return dto;
    }

    public void setDto(PlanillaPartidoDTO dto) {
        this.dto = dto;
    }
     
    
    
}
