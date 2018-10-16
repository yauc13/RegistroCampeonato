/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.CampeonatoDao;
import com.fut.model.Campeonato;
import com.fut.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */

@ManagedBean
@SessionScoped


public class CampeonatoBean implements Serializable{
    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private List<Campeonato> listaCampeonato;
    private String loginUsuario;
    private String accion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public List<Campeonato> getListaCampeonato() {
        return listaCampeonato;
    }

    public void setListaCampeonato(List<Campeonato> listaCampeonato) {
        this.listaCampeonato = listaCampeonato;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    
    
    public void operar() throws Exception{
    switch(accion){
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
        this.campeonato = new Campeonato();
    }
    
    public void preparedNew(){
        this.setAccion("Registrar");
        limpiarCampeonato();
    }
    
    
    
    public void registrar() throws Exception {
    CampeonatoDao dao;
    
    try{
        dao = new CampeonatoDao();
        
        this.campeonato.setIdUsuario(usuario.getIdUsuario());
        boolean reg= dao.registrar(campeonato);
        if(reg){
            
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Campeonato creado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listarCampeonatos();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    CampeonatoDao dao;
    try{
        
        dao = new CampeonatoDao();
        boolean reg = dao.modificar(campeonato);
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
        direccion = "listaGrupo?faces-redirect=true";
        
        
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
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaCampeonato = dao.listar();
        }    
    }
    
    public void listarCampeonatos(){
    CampeonatoDao dao;    
        dao = new CampeonatoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaCampeonato = dao.listar();
    }
    
    
    public void leerID (Campeonato usu) throws Exception{
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
