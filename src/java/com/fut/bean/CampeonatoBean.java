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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped


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
            this.limpiar();
            break;
        case "Modificar":
            this.modificar();
            this.limpiar();
            break;
    }
    }
    
    public void limpiar(){
    this.campeonato.setIdCampeonato(0);
    this.campeonato.setNombreCampeonato("");
    }
    
    public void registrar() throws Exception {
    CampeonatoDao dao;
    
    try{
        dao = new CampeonatoDao();
        
        this.campeonato.setIdUsuario(usuario.getIdUsuario());
        dao.registrar(campeonato);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    CampeonatoDao dao;
    try{
        
        dao = new CampeonatoDao();
        dao.modificar(campeonato);
        this.listar();
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
    
    public void listarInicio() throws Exception{
    CampeonatoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new CampeonatoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaCampeonato = dao.listar();
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    CampeonatoDao dao;
    try{
        dao = new CampeonatoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaCampeonato = dao.listar();
        
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Campeonato usu) throws Exception{
            this.campeonato = usu; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar(Campeonato usu) throws Exception {
    CampeonatoDao dao;
    try{
        dao = new CampeonatoDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public String habilitarPermisos(Campeonato camp, int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar eliminar y editar
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
