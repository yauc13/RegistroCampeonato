/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.UsuarioDao;
import com.fut.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DIANA G
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuario;
    
    private String accion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    

    
    
    
    public String iniciarSesion() throws Exception{
        String redireccion = null;
        UsuarioDao dao;
        try{
            dao = new UsuarioDao();
            Usuario u = dao.leerID(usuario);
            if(u !=null){
                this.usuario = u;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "inicion sesion"));
               redireccion = "listaCampeonato?faces-redirect=true";
               
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        }catch(Exception e){  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
            throw e;
    }
        return redireccion;
    }
    
    public String cerrarSesion(){
        String redireccion = "index?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.limpiar();
        return redireccion;
    }
    
    public void verificarSesion(){
        
        try {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us == null){
            
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml");
            
        }
        } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    this.usuario.setIdUsuario(0);
    this.usuario.setLoginUsuario("");
    this.usuario.setPasswordUsuario("");
    this.usuario.setRolUsuario("");
    }
    
    public String registrar() throws Exception {
    UsuarioDao dao;
    String direc = null;
    try{
        
        dao = new UsuarioDao();
        Usuario u= dao.leerIDRegistro(usuario);
        if(u != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Usuario ya esta registrado"));
        }else{
            boolean reg = dao.registrar(usuario);
            if(reg == true){
                //FacesContext.getCurrentInstance().addMessage("iniciarSesion",new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Inicio sesion exitoso") );
                direc = "index?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
            }
        }
    }catch(Exception e){  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
        throw e;
    }   
    return direc;
    }
    
    public void modificar() throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.modificar(usuario);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void listar() throws Exception{
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        listaUsuario = dao.listar();
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Usuario usu) throws Exception{
            this.usuario = usu; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar(Usuario usu) throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
        
}
