/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.UsuarioDao;
import com.fut.model.Usuario;
import com.fut.util.Cons;
import com.fut.util.Util;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author DIANA G
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    String ejex= "45,45.5,46,46.5,47,47.5,48,48.5,49,49.5,50,50.5,51,51.5,52,52.5,53,53.5,54,54.5,55,55.5,56,56.5,57,57.5,58,58.5,59,59.5,60,60.5,61,61.5,62,62.5,63,63.5,64,64.5,65,65.5,66,66.5,67,67.5,68,68.5,69,69.5,70,70.5,71,71.5,72,72.5,73,73.5,74,74.5,75,75.5,76,76.5,77,77.5,78,78.5,79,79.5,80,80.5,81,81.5,82,82.5,83,83.5,84,84.5,85,85.5,86,86.5,87,87.5,88,88.5,89,89.5,90,90.5,91,91.5,92,92.5,93,93.5,94,94.5,95,95.5,96,96.5,97,97.5,98,98.5,99,99.5,100,100.5,101,101.5,102,102.5,103,103.5,104,104.5,105,105.5,106,106.5,107,107.5,108,108.5,109,109.5,110";
    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuario;
    
    private String accion;
    private String ip;
    private String mac;
    JSONObject  jasonObj ;
     List<double[]> listDataGrap;

    public UsuarioBean() {
        listDataGrap= new ArrayList<>();
        double v0[]={2.4,2.5,2.6,2.7,2.8,2.9,2.9,3,3.1,3.2,3.3,3.4,3.5,3.6,3.8,3.9,4,4.1,4.3,4.4,4.5,4.7,4.8,5,5.1,5.3,5.4,5.6,5.7,5.9,6,6.1,6.3,6.4,6.5,6.7,6.8,6.9,7,7.1,7.3,7.4,7.5,7.6,7.7,7.9,8,8.1,8.2,8.3,8.4,8.5,8.6,8.8,8.9,9,9.1,9.2,9.3,9.4,9.5,9.6,9.7,9.8,9.9,10,10.1,10.2,10.3,10.4,10.4,10.5,10.6,10.7,10.8,10.9,11,11.2,11.3,11.4,11.5,11.6,11.7,11.9,12,12.1,12.2,12.4,12.5,12.6,12.7,12.8,13,13.1,13.2,13.3,13.4,13.5,13.7,13.8,13.9,14,14.1,14.3,14.4,14.5,14.6,14.8,14.9,15,15.2,15.3,15.4,15.6,15.7,15.9,16,16.2,16.3,16.5,16.6,16.8,16.9,17.1,17.3,17.4,17.6,17.8,17.9,18.1,18.3};
        listDataGrap.add(v0);
        double v1[]={2.7,2.8,2.9,3,3,3.1,3.2,3.3,3.4,3.5,3.6,3.8,3.9,4,4.1,4.2,4.4,4.5,4.7,4.8,5,5.1,5.3,5.4,5.6,5.7,5.9,6.1,6.2,6.4,6.5,6.7,6.8,7,7.1,7.2,7.4,7.5,7.6,7.8,7.9,8,8.2,8.3,8.4,8.5,8.7,8.8,8.9,9,9.2,9.3,9.4,9.5,9.6,9.8,9.9,10,10.1,10.2,10.3,10.4,10.6,10.7,10.8,10.9,11,11.1,11.2,11.3,11.4,11.5,11.6,11.7,11.8,11.9,12,12.1,12.2,12.4,12.5,12.6,12.8,12.9,13,13.2,13.3,13.4,13.5,13.7,13.8,13.9,14.1,14.2,14.3,14.4,14.6,14.7,14.8,14.9,15.1,15.2,15.3,15.5,15.6,15.7,15.9,16,16.2,16.3,16.5,16.6,16.8,16.9,17.1,17.3,17.4,17.6,17.8,17.9,18.1,18.3,18.5,18.6,18.8,19,19.2,19.4,19.6,19.8,20};
        listDataGrap.add(v1);
        double v2[]={3,3.1,3.1,3.2,3.3,3.4,3.6,3.7,3.8,3.9,4,4.1,4.2,4.4,4.5,4.6,4.8,4.9,5.1,5.3,5.4,5.6,5.8,5.9,6.1,6.3,6.4,6.6,6.8,7,7.1,7.3,7.4,7.6,7.7,7.9,8,8.2,8.3,8.5,8.6,8.7,8.9,9,9.2,9.3,9.4,9.6,9.7,9.8,10,10.1,10.2,10.4,10.5,10.6,10.8,10.9,11,11.2,11.3,11.4,11.5,11.6,11.7,11.9,12,12.1,12.2,12.3,12.4,12.5,12.6,12.7,12.8,13,13.1,13.2,13.3,13.5,13.6,13.7,13.9,14,14.2,14.3,14.5,14.6,14.7,14.9,15,15.1,15.3,15.4,15.6,15.7,15.8,16,16.1,16.3,16.4,16.5,16.7,16.8,17,17.1,17.3,17.5,17.6,17.8,18,18.1,18.3,18.5,18.7,18.8,19,19.2,19.4,19.6,19.8,20,20.2,20.4,20.6,20.8,21,21.2,21.4,21.7,21.9};
        listDataGrap.add(v2);
        double v3[]={3.3,3.4,3.5,3.6,3.7,3.8,3.9,4,4.2,4.3,4.4,4.5,4.7,4.8,5,5.1,5.3,5.4,5.6,5.8,6,6.1,6.3,6.5,6.7,6.9,7.1,7.2,7.4,7.6,7.8,8,8.1,8.3,8.5,8.6,8.8,8.9,9.1,9.3,9.4,9.6,9.7,9.9,10,10.2,10.3,10.5,10.6,10.8,10.9,11.1,11.2,11.3,11.5,11.6,11.8,11.9,12.1,12.2,12.3,12.5,12.6,12.7,12.8,13,13.1,13.2,13.3,13.4,13.6,13.7,13.8,13.9,14,14.2,14.3,14.4,14.6,14.7,14.9,15,15.2,15.3,15.5,15.6,15.8,15.9,16.1,16.2,16.4,16.5,16.7,16.8,17,17.1,17.3,17.4,17.6,17.7,17.9,18,18.2,18.4,18.5,18.7,18.9,19.1,19.2,19.4,19.6,19.8,20,20.2,20.4,20.6,20.8,21,21.2,21.5,21.7,21.9,22.1,22.4,22.6,22.8,23.1,23.3,23.6,23.8,24.1};
        listDataGrap.add(v3);
        
   armarJason();
    }
     
    private void armarJason(){
        String ag="";
        for(double[] a:listDataGrap){
            String ay="";
            System.err.println("[length] : "+a.length);
            for(int i=0;i<a.length;i++){
                ay=ay+a[i]+",";
            }
            ay=ay.substring(0,ay.length()-1);
           ag= ag+Cons.ARRAY_Y.replaceAll("arrayy", ay)+",";
        }
        ag=ag.substring(0,ag.length()-1);
        String par="{\"label\": \"datos\",\"showLine\":false,\"pointRadius\": 3,\"borderColor\": \"rgba(0,0,0,1)\",\"fill\": false,\"data\": [{x: 46,y: 2.5},{x: 49.5,y: 3.1},{x: 54,y: 4.1}]}";
        ag=ag+","+par;
        String strJ= Cons.ARRAY_GRAP.replaceAll("arraydataset", ag).replaceAll("arraylabel",ejex );
        jasonObj = new JSONObject(strJ);
    }
     

    public JSONObject getJasonObj() {
        return jasonObj;
    }

    public void setJasonObj(JSONObject jasonObj) {
        this.jasonObj = jasonObj;
    }
    
 

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
