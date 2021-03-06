/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.JugadorDao;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Usuario;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yeison
 */

@ManagedBean
@SessionScoped

public class JugadorBean implements Serializable{
    private Jugador jugador = new Jugador();
    private Equipo equipo = new Equipo();
    private Grupo grupo = new Grupo();
    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private List<Jugador> listaJugador;
    private String accion;
    
    private String imageJugador; //imagen txt base64
    private UploadedFile imgFile; //file para la imagen del jugador
    private String fotoDefault=""; //foto por defecto
    

    public JugadorBean() {
 
    }

    
    public void handleFileUpload(FileUploadEvent event) {
        imgFile =event.getFile();
       
        // Reading a Image file from file system
        if(imgFile!=null){
        byte imageData[] = imgFile.getContents();
        imageJugador = Base64.getEncoder().encodeToString(imageData);
        }else{           
            imageJugador = this.jugador.getFotoJugador();
        }
         
    }
    
    


    public void operar(){
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
    this.jugador = new Jugador();
    imageJugador = "";
    this.imgFile = null;
    }
    
    public void registrar() {
        JugadorDao dao;
        dao = new JugadorDao();
        this.jugador.setIdEquipoJugador(equipo.getIdEquipo());
        this.jugador.setIdUsuario(usuario.getIdUsuario());
        this.jugador.setFotoJugador(imageJugador);
        if (dao.registrar(jugador)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jugador creado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.listar();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear Jugador");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void modificar() {
    JugadorDao dao;
    
       if(imgFile==null){
        imageJugador = this.jugador.getFotoJugador();
        }

        dao = new JugadorDao();
        jugador.setFotoJugador(imageJugador);
        
        if (dao.modificar(jugador)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jugador Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.listar();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar Jugador");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
    }
    
    
 private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    public void listarInicio() throws Exception{
    JugadorDao dao;
    
        if(this.isPostBack() == false){
        dao = new JugadorDao();
        equipo = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        //Equipo camp = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        
        listaJugador = dao.listar(equipo);

        }
    
    }
    
    public void listar(){
    JugadorDao dao;
   
        dao = new JugadorDao();
        equipo = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        //Equipo camp = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        listaJugador = dao.listar(equipo);

    }
    
    public void listarGoleadores() {
    JugadorDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new JugadorDao();        
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaJugador = dao.listarGoleadores(campeonato);

        }
    }catch(Exception e){   
       
    }
    }
    
    
    public void leerID (Jugador usu){
            this.jugador = usu;
            //this.jugador.setFotoJugador(usu.getFotoJugador());
            this.accion = "Modificar";
    }
    
     public String verJugadoresEquipos() throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jugador", jugador);
        direccion = "listaJugador?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    

   
    public void eliminar(Jugador usu) {
    JugadorDao dao;
    
        dao = new JugadorDao();
        dao.eliminar(usu);
        this.listar();
      
    }
    
   public String habilitarPermisos(Jugador camp, int i){
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
               if(equipo.getIdUsuario() == usuario.getIdUsuario() || "Administrador".equals(usuario.getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;   
       }     
    return bol;
    }

    public UploadedFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(UploadedFile imgFile) {
        this.imgFile = imgFile;
    }
   
    
    public String getFotoDefault() {
        return fotoDefault;
    }

    public void setFotoDefault(String fotoDefault) {
        this.fotoDefault = fotoDefault;
    }
    
    
    
    public String getImageJugador() {
        return imageJugador;
    }

    public void setImageJugador(String imageJugador) {
        this.imageJugador = imageJugador;
    }
    
    
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Jugador> getListaJugador() {
        return listaJugador;
    }

    public void setListaJugador(List<Jugador> listaJugador) {
        this.listaJugador = listaJugador;
    }

    

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        //esta accion se hace cuando se oprime el boton nuevo
        this.limpiar();
        this.accion = accion;
    }
   
    
    
}
