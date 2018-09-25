/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.EquipoDao;
import com.fut.dao.GrupoDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;

import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.TablaEquipos;
import com.fut.model.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Yeison
 * 
 */

@ManagedBean
@SessionScoped

public class GrupoBean implements Serializable{
    private Grupo grupo = new Grupo();
    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private List<Grupo> listaGrupo;
    private List<TablaEquipos> listaPosiciones;
    private List<Equipo> listaEquipos;
    private List<Jugador> listaGoleadores;
    private String accion;
    
    private JugadorDao jugDao = new JugadorDao();

    public GrupoBean() {
      campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");  
      listaGoleadores = jugDao.listarGoleadores(campeonato);
    }
    
    

   
    
    public int verIdCampeonato(){
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        int idCamp = camp.getIdCampeonato();
    return idCamp;
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        event.getTab().setLoaded(true);
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
        this.grupo.setIdUsuario(usuario.getIdUsuario());
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
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaGrupo = dao.listar(camp);
        listaGoleadores = jugDao.listarGoleadores(campeonato);
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaGrupo = dao.listar(camp);
        listaGoleadores = jugDao.listarGoleadores(campeonato);
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Grupo usu) throws Exception{
            this.grupo = usu; 
            this.accion = "Modificar";
    }
    
    public String verEquiposGrupos() throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        direccion = "listaEquipo?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    
    public String verGrupo(Grupo grup) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupo", grup);
        direccion = "vistaGrupo?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    
     public String verPartidosGrupo() throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        direccion = "listaPartido?faces-redirect=true";
        
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

    public String habilitarPermisos(Campeonato camp, int i){
        String bol = null;
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
    
    public void  listarPosicionesGrupo(){
        PartidoDao partidoDao= new PartidoDao();
        try {
            //listaPosiciones.clear();
            grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
            listaPosiciones= partidoDao.listarTablaPosiciones(grupo);
        } catch (Exception ex) {
            Logger.getLogger(GrupoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

   
    
     public Usuario getUsuario() {
        return usuario;
    }

    public List<TablaEquipos> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<TablaEquipos> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
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

    public List<Equipo> getListaEquipos(Grupo gru) {
        EquipoDao equipoDao = new EquipoDao();
        try {
            listaEquipos = equipoDao.listar(gru);
        } catch (Exception ex) {
            Logger.getLogger(GrupoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public List<Jugador> getListaGoleadores() {
        return listaGoleadores;
    }

    public void setListaGoleadores(List<Jugador> listaGoleadores) {
        this.listaGoleadores = listaGoleadores;
    }

    public JugadorDao getJugDao() {
        return jugDao;
    }

    public void setJugDao(JugadorDao jugDao) {
        this.jugDao = jugDao;
    }

    
    
    
        

}
