/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.GolDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.dao.PenalDao;
import com.fut.dao.TarjetaDao;
import com.fut.dto.PlanillaPartidoDTO;
import com.fut.logic.PlanillaPartidoBo;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.Penal;
import com.fut.model.Tarjeta;
import com.fut.model.Usuario;
import com.fut.util.Cons;
import com.fut.util.Util;
import java.io.File;
import java.io.Serializable;
import java.util.Base64;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author YeisonUrrea
 */
@ManagedBean
@ViewScoped

public class PlanillaPartidoBean implements Serializable{
    
    private PlanillaPartidoDTO dto;
    private final PlanillaPartidoBo bo;
    private final PartidoDao daoPar;
    private final JugadorDao daoJug;  
    private final GolDao daoGol;
    private final PenalDao daoPen;
    private final TarjetaDao daoTar;

    public PlanillaPartidoBean() {
        dto = new PlanillaPartidoDTO();
        bo = new PlanillaPartidoBo();
                
        daoPar = new PartidoDao();
        daoJug = new JugadorDao();
        daoGol = new GolDao();
        daoTar = new TarjetaDao();
        daoPen = new PenalDao();
        
        dto.setUsuario((Usuario)Util.getObjectOfContext("usuario"));
        dto.setPartido((Partido) Util.getObjectOfContext("partido"));
        dto.setGrupo((Grupo) Util.getObjectOfContext("grupo"));
        dto.setCampeonato((Campeonato) Util.getObjectOfContext("campeonato"));
        enableButtonStart(dto);
        bo.listarArbitros(dto);
        
    }
    
    
    
    public void listarPlanillasInicio() {             
        if(this.isPostBack() == false){                 
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoB()));
        listarGoles();  
        listarPenales();
        }   
    }
    
    public void listarPlanillas(){     
        dto.setListaJugadoresA(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoA()));
        dto.setListaJugadoresB(daoJug.listarJugadoresEquipo(dto.getPartido().getIdEquipoB()));
        listarGoles();
        listarPenales();
        
    }
    
    public void listarGoles() {        
        dto.setListaGolesA(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoA()));
        dto.setListaGolesB(daoGol.listarGolesPartidoEquipoJoin(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoB()));
    }
    
    public void listarPenales(){
        dto.setListaPenalesA(daoPen.listarPenalesPartidoEquipo(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoA()));
        dto.setListaPenalesB(daoPen.listarPenalesPartidoEquipo(dto.getPartido().getIdPartido(), dto.getPartido().getIdEquipoB()));
    }
     
    public String finalizarPartido() {
        String direccion = bo.finalizarPartido(dto);        
        return direccion;
    }
    
     public String finalizarPenalties() {
        String direccion="";       
       
        daoPar.finalizarPenalties(dto.getPartido());
         if (dto.getPlayOff() != null) {
                direccion = "listaPartidoPlayOff?faces-redirect=true";
            }       
        return direccion;
    }
    
     public void iniciarPartido() {        
        boolean resp =daoPar.iniciarPartido(dto.getPartido());
        if(resp){ 
            agregarArbitroPartido();
            enableButtonStartNext();
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "partido iniciado");        
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL, "Error", "el partido no se pudo iniciar");
        }      
        
    }
     
    private void agregarArbitroPartido(){
        bo.asignarArbitroPartido(dto.getPartido().getIdArbitro(), dto.getPartido().getIdPartido());
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
    
    public void anotarPenal(int jug, int equ, Partido par) {
        if (par.getIdPlayOff() > 0) {
            Penal pen = new Penal();
            pen.setIdJugador(jug);
            pen.setIdEquipo(equ);
            pen.setIdPartido(par.getIdPartido());
            if (equ == par.getIdEquipoA()) {
                pen.setIdEquipoB(par.getIdEquipoB());
            } else {
                pen.setIdEquipoB(par.getIdEquipoA());
            }
            if(daoPen.insertPenal(pen)){
            listarPenales();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Cons.MSG_SUCCESSFUL, "Penal Registrado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Cons.MSG_ERROR, "Error al registrar penal");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, Cons.MSG_WARN, "El tipo de partido no permite penales");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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
    
    public void loadFotoPlanilla(){
        dto.setFotoPlanilla(bo.verFotoPlanilla(dto.getPartido()));
    }
    
     public void handleFileUpload(FileUploadEvent event) {
        dto.setImgFile(event.getFile());              
        if(dto.getImgFile()!=null){
        String img = dto.getPartido().getIdPartido()+".JPG";
        String ruta = "C:\\payara5\\images\\";
        byte imageData[] = dto.getImgFile().getContents();
        dto.setFotoPlanilla(Base64.getEncoder().encodeToString(imageData));
        System.err.println("nombre actual: "+dto.getImgFile().getFileName());
        File f = (File) dto.getImgFile();
        File foto = new File(f,ruta+img);
        System.err.println("nombre final: ");
        }
         
    }

    private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    private void enableButtonStart(PlanillaPartidoDTO dto){
        bo.enableButtonStart(dto);
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
