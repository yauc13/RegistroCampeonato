/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.UsuarioDao;
import com.fut.model.Usuario;
import com.fut.util.Util;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private String ip;
    private String mac;


    
    

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
    

    
    
    
    public String iniciarSesion(){
        String redireccion = null;
        UsuarioDao dao;

            dao = new UsuarioDao();
            Usuario u = dao.leerID(usuario);
            if(u !=null){
                this.usuario = u;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                getIpMac();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "inicion sesion"));
               redireccion = "listaCampeonato?faces-redirect=true";
               
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
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
            //FacesContext.getCurrentInstance().getExternalContext().redirect("permisos?faces-redirect=true");
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
    
    public void registrar() throws Exception {
    UsuarioDao dao;
    String direc = "true";
    try{
        
        dao = new UsuarioDao();
        Usuario u= dao.leerIDRegistro(usuario);
        if(u != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Usuario ya esta registrado"));
        }else{
            boolean reg = dao.registrar(usuario);
            if(reg == true){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro exitoso") );
                
                direc = "false";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
            }
        }
    }catch(Exception e){  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
        throw e;
    }   
    
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
    
    
    public String habilitarPermisos(int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar eliminar y editar
               if( "Administrador".equals(usuario.getRolUsuario())){
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
            case 3:
                
                break;
       
       }
        
    return bol;
    }
    
    private void getIpMac() {
        this.ip = Util.getIpAddress();
       // this.mac = Util.getMacAddress();
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    
        
}
