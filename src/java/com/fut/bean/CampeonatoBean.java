/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.CampeonatoDao;
import com.fut.dto.CampeonatoDTO;
import com.fut.model.Campeonato;
import com.fut.model.Usuario;
import com.fut.util.Util;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped


public class CampeonatoBean implements Serializable{
    
    CampeonatoDTO dto;

    public CampeonatoBean() { 
        dto = new CampeonatoDTO();
        dto.setUsuario((Usuario) Util.getObjectOfContext("usuario"));
           
    }

    public void operar() throws Exception{
    switch(dto.getAccion()){
        case "Registrar":
            this.registrar();
            this.limpiarCampeonato();
            break;
        case "Modificar":
            this.modificar();
            this.limpiarCampeonato();
            break;
    }
    }
    
    public void limpiarCampeonato(){
        dto.setCampeonato(new Campeonato());
    }
    
    public void preparedNew(){
        dto.setAccion("Registrar");
        limpiarCampeonato();
    }
    
    
    
    public void registrar() {
    CampeonatoDao dao;

        dao = new CampeonatoDao();
        
        dto.getCampeonato().setIdUsuario(dto.getUsuario().getIdUsuario());
        boolean reg= dao.registrar(dto.getCampeonato());
        if(reg){
           Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Campeonato creado");        
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");       
        }
        this.listarCampeonatos();
     
    }
    
    public void modificar() throws Exception {
    CampeonatoDao dao;
    try{
        
        dao = new CampeonatoDao();
        boolean reg = dao.modificar(dto.getCampeonato());
        if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Campeonato Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listarCampeonatos();
    }catch(Exception e){  
        throw e;
    }   
    }
    

    public String verGruposCampeonato(Campeonato camp) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("campeonato", camp);
        direccion = "adminChampionShip?faces-redirect=true";
        
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
        
    private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    public void listarCampeonatosInicio(){
    CampeonatoDao dao;    
        if(this.isPostBack() == false){
        dao = new CampeonatoDao();
        
        listaCampeonato = dao.listar();
        }    
    }
    
    public void listarCampeonatos(){
    CampeonatoDao dao;    
        dao = new CampeonatoDao();
        
        listaCampeonato = dao.listar();
    }
    
    
    public void preparedEditChampionShip (Campeonato usu) throws Exception{
            this.campeonato = usu; 
            this.accion = "Modificar";
    }
    

   
    public void deleteCampeonato() {
    CampeonatoDao dao;
    
        dao = new CampeonatoDao();
        boolean reg =dao.deleteCampeonato(campeonato);
        if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Campeonato Eliminado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:no se pudo Eliminar", "porque tiene jornadas o grupos asociados");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listarCampeonatos();
      
    }
    
    public String habilitarPermisos(Campeonato camp, int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar deleteCampeonato y editar
               if(camp.getIdUsuario() == usuario.getIdUsuario() || "Administrador".equals(usuario.getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;
               
            case 2:
                          
                //habilitar boton nuevo
               if("Organizador".equals(usuario.getRolUsuario()) || "Administrador".equals(usuario.getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;
       
       }
        
    return bol;
    }
    

    
    
    
    
}
