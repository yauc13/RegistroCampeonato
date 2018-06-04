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
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped

public class JugadorBean implements Serializable{
    private Jugador jugador = new Jugador();
    private Equipo equipo = new Equipo();
    private Grupo grupo = new Grupo();
    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private List<Jugador> listaJugador;
    private String accion;
    private Date fechaNacimiento;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
    this.jugador.setIdJugador(0);
    this.jugador.setNombreJugador("");
    }
    
    public void registrar() throws Exception {
    JugadorDao dao;
    try{
       
        dao = new JugadorDao();
        this.jugador.setIdEquipoJugador(equipo.getIdEquipo());
        this.jugador.setFechaNacimiento(fechaNacimiento.toString());
        dao.registrar(jugador);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    JugadorDao dao;
    try{
        dao = new JugadorDao();
        dao.modificar(jugador);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    
 private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    public void listarInicio() throws Exception{
    JugadorDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new JugadorDao();
        equipo = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        //Equipo camp = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        
        listaJugador = dao.listar(equipo);
        
        
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    JugadorDao dao;
    try{
        dao = new JugadorDao();
        equipo = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        //Equipo camp = (Equipo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("equipo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        listaJugador = dao.listar(equipo);
       
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Jugador usu) throws Exception{
            this.jugador = usu; 
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
    

   
    public void eliminar(Jugador usu) throws Exception {
    JugadorDao dao;
    try{
        dao = new JugadorDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
   public String habilitarPermisos(Equipo camp, int i){
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
               if("Club".equals(usuario.getRolUsuario()) || "Administrador".equals(usuario.getRolUsuario())){
                    bol = "false";
                }else{
                    bol = "true";
                }
               break;   
       }     
    return bol;
    }
    
}
