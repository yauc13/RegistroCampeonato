/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.JornadaDao;
import com.fut.model.Campeonato;
import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
@ManagedBean
@SessionScoped
public class JornadaBean {
    private String nombreFecha;
    private Jornada jornada = new Jornada();
    private Usuario usuario = new Usuario();
    private Grupo grupo;
    private List<Jornada> listaJornadas;
    private String loginUsuario;
    private String accion;
    JornadaDao jornadaDao;
    Campeonato campeonato;

    public JornadaBean() {
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        jornadaDao = new JornadaDao();
       
 jornadaDao.listarJornadas(campeonato);

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
    this.jornada.setNombreJornada("");
    
    }
    
    public void registrar() throws Exception {
    jornadaDao = new JornadaDao();
    Jornada jor = new Jornada();
    try{
        
        
        jor.setNombreJornada(nombreFecha);
            jor.setIdCampeonato(grupo.getIdGrupo());            
            jor.setIdUsuario(usuario.getIdUsuario());
        boolean reg= jornadaDao.registrar(jor);
        if(reg){
            
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
     
    try{
        
        
        boolean reg = jornadaDao.modificar(jornada);
        if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    

    public String verGruposJornada(Jornada camp) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jornada", camp);
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
    
    try{
        if(this.isPostBack() == false){
        
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaJornadas = jornadaDao.listarJornadas(campeonato);
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    
    try{
       
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaJornadas = jornadaDao.listarJornadas(campeonato);
        
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Jornada usu) throws Exception{
            this.jornada = usu; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar() throws Exception {
    
    try{
        
        boolean reg =jornadaDao.deleteJornada(jornada);
        if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada Eliminado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:no se pudo Eliminar", "porque tiene grupos creados");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public String habilitarPermisos(Jornada camp, int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar deleteJornada y editar
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


    public String getNombreFecha() {
        return nombreFecha;
    }

    public void setNombreFecha(String nombreFecha) {
        this.nombreFecha = nombreFecha;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Jornada> getListaJornadas() {
        return listaJornadas;
    }

    public void setListaJornadas(List<Jornada> listaJornadas) {
        this.listaJornadas = listaJornadas;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public JornadaDao getJornadaDao() {
        return jornadaDao;
    }

    public void setJornadaDao(JornadaDao jornadaDao) {
        this.jornadaDao = jornadaDao;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
    
    
    
    
    
    
    
    
}
