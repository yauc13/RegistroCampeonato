/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.GrupoDao;
import com.fut.model.Campeonato;

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
 * 
 */

@ManagedBean
@ViewScoped

public class GrupoBean implements Serializable{
    private Grupo grupo = new Grupo();
    private Campeonato campeonato = new Campeonato();
    private List<Grupo> listaGrupo;
    private String accion;
    

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }



    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    
    
    public int verIdCampeonato(){
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        int idCamp = camp.getIdCampeonato();
    return idCamp;
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
    this.grupo.setIdGrupo(0);
    this.grupo.setNombreGrupo("");
    }
    
    public void registrar() throws Exception {
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        //Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        this.grupo.setIdCampeonato(campeonato.getIdCampeonato());
        dao.registrar(grupo);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        dao.modificar(grupo);
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
    GrupoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new GrupoDao();
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaGrupo = dao.listar(camp);
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaGrupo = dao.listar(camp);
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Grupo usu) throws Exception{
            this.grupo = usu; 
            this.accion = "Modificar";
    }
    
     public String verEquiposGrupos(Grupo grup) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupo", grup);
        direccion = "listaEquipo?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    

   
    public void eliminar(Grupo usu) throws Exception {
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
}
