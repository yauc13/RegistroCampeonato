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
    

    public String verGruposCampeonato(Campeonato camp) {
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("campeonato", camp);
         String direccion = "adminChampionShip?faces-redirect=true";

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
        dto.setListaCampeonato( dao.listar());        
        }    
    }
    
    public void listarCampeonatos(){
        CampeonatoDao dao;    
        dao = new CampeonatoDao();
        dto.setListaCampeonato( dao.listar()); 
    }
    
    
    public void preparedEditChampionShip (Campeonato cam) throws Exception{
            dto.setCampeonato(cam);  
            dto.setAccion("Modificar");
    }
    

   
    public void deleteCampeonato() {
    CampeonatoDao dao;
    
        dao = new CampeonatoDao();
        boolean reg =dao.deleteCampeonato(dto.getCampeonato());
        if(reg){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso",  "Campeonato Eliminado");        
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL,"Error:no se pudo Eliminar",  "porque tiene jornadas o grupos asociados");  
        }
        this.listarCampeonatos();
      
    }
    
    public String habilitarPermisos(Campeonato camp, int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar deleteCampeonato y editar
               if(camp.getIdUsuario() == dto.getUsuario().getIdUsuario() || "Administrador".equals(dto.getUsuario().getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;
               
            case 2:
                          
                //habilitar boton nuevo
               if("Organizador".equals(dto.getUsuario().getRolUsuario()) || "Administrador".equals(dto.getUsuario().getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;
       
       }
        
    return bol;
    }

    public CampeonatoDTO getDto() {
        return dto;
    }

    public void setDto(CampeonatoDTO dto) {
        this.dto = dto;
    }
    

    
    
    
    
}
