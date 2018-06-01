/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.EquipoDao;
import com.fut.model.Campeonato;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
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


public class EquipoBean implements Serializable{
    private Equipo equipo = new Equipo();
    private Grupo grupo = new Grupo();
    private Campeonato campeonato = new Campeonato();
    private List<Equipo> listaEquipo;
    private String accion;

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
    
    

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
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
    this.equipo.setIdEquipo(0);
    this.equipo.setNombreEquipo("");
    }
    
    public void registrar() throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        this.equipo.setIdGrupoEquipo(grupo.getIdGrupo());
        dao.registrar(equipo);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        dao.modificar(equipo);
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
    EquipoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new EquipoDao();
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        //Grupo camp = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        listaEquipo = dao.listar(grupo);
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        Grupo camp = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaEquipo = dao.listar(camp);
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Equipo usu) throws Exception{
            this.equipo = usu; 
            this.accion = "Modificar";
    }
    
     public String verJugadoresEquipo() throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("equipo", equipo);
        direccion = "listaJugador?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    

   
    public void eliminar(Equipo usu) throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public String habilitarPermisos(){
        String bol;
        if(campeonato.getIdCampeonato()== 1){
            bol = "true";
        }else{
            bol = "false";
        }
    return bol;
    }
    
}
