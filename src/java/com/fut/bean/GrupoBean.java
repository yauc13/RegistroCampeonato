/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.EquipoDao;
import com.fut.dao.GrupoDao;
import com.fut.dao.JornadaDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;

import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.TablaEquipos;
import com.fut.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    private Jornada jornada; 
    private List<Grupo> listaGrupo;
    private List<TablaEquipos> listaPosiciones;
    private List<Equipo> listaEquipos;
    private List<Jugador> listaGoleadores;
    private List<Jornada> listaJornada;
    private List<Partido> listaPartidosJornada;
    private String accion;
    
    private List<SelectItem> selectItemOneGrupos; //para seleccionar grupo segun el campeonato
    private List<SelectItem> selectItemOnePartidos; //para seleccionar grupo segun el campeonato
    private int itemGrupoSelected;
    private int itemPartidoSelected; //partido seleccionado para agregar a una jornada
    
    private JugadorDao jugDao = new JugadorDao();
    private JornadaDao jorDao = new JornadaDao();
    private PartidoDao parDao = new PartidoDao();
    private GrupoDao gruDao = new GrupoDao();

    public GrupoBean() {
        
       jornada = new Jornada();
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
    
    public void operarJornada() {
    switch(accion){
        case "Registrar":
            this.registrarJornada();
            this.limpiar();
            break;
        case "Modificar":
            this.modificarJornada();
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
    
    public void registrarJornada() {

        
        jornada.setIdCampeonato(campeonato.getIdCampeonato());
        jornada.setIdUsuario(usuario.getIdUsuario());
        if(jorDao.registrar(jornada)){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear Jornada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.listar();
        
       
    }
    
    public void modificarJornada()  {
    
    try{
        
        if(jorDao.registrar(jornada)){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.listar();
    }catch(Exception e){  
        
    }   
    }
    
        public void modificar() {
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        dao.modificar(grupo);
        this.listar();
    }catch(Exception e){  
        
    }   
    }
        
    public void agregarPartidoJornada()  {  
        
        parDao.agregarPartidoJornada(jornada.getIdJornada(), itemPartidoSelected);        
        this.listar();    
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
        listaJornada = jorDao.listar(campeonato);
        
        
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() {
    GrupoDao dao;
    try{
        dao = new GrupoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        Campeonato camp = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        listaGrupo = dao.listar(camp);
        listaGoleadores = jugDao.listarGoleadores(campeonato);
        listaJornada = jorDao.listar(campeonato);
    }catch(Exception e){   
       
    }
    }
    
    public List<Partido> listarPartidosJornada(Jornada jor){
    return parDao.listarPartidosJornada(jor);
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

    public List<Jornada> getListaJornada() {
        return listaJornada;
    }

    public void setListaJornada(List<Jornada> listaJornada) {
        this.listaJornada = listaJornada;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public JornadaDao getJorDao() {
        return jorDao;
    }

    public void setJorDao(JornadaDao jorDao) {
        this.jorDao = jorDao;
    }

    public List<Partido> getListaPartidosJornada() {
        return listaPartidosJornada;
    }

    public void setListaPartidosJornada(List<Partido> listaPartidosJornada) {
        this.listaPartidosJornada = listaPartidosJornada;
    }

    public PartidoDao getParDao() {
        return parDao;
    }

    public void setParDao(PartidoDao parDao) {
        this.parDao = parDao;
    }

    public List<SelectItem> getSelectItemOneGrupos() {
         try {
            this.selectItemOneGrupos = new ArrayList<>();
            
            List<Grupo> listGrupo = gruDao.listar(campeonato);
            selectItemOneGrupos.clear();
            for (Grupo g:listGrupo){
            SelectItem selectItem = new SelectItem(g.getIdGrupo(),g.getNombreGrupo());
            selectItemOneGrupos.add(selectItem);
            }                              
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        return selectItemOneGrupos;
    }

    public void setSelectItemOneGrupos(List<SelectItem> selectItemOneGrupos) {
        this.selectItemOneGrupos = selectItemOneGrupos;
    }

    public GrupoDao getGruDao() {
        return gruDao;
    }

    public void setGruDao(GrupoDao gruDao) {
        this.gruDao = gruDao;
    }

    public int getItemGrupoSelected() {
        return itemGrupoSelected;
    }

    public void setItemGrupoSelected(int itemGrupoSelected) {
        this.itemGrupoSelected = itemGrupoSelected;
    }

    public List<SelectItem> getSelectItemOnePartidos() {
        try {
            this.selectItemOnePartidos = new ArrayList<>();
            
            List<Partido> listPartido = parDao.listarJoin(itemGrupoSelected);
            selectItemOnePartidos.clear();
            for (Partido p:listPartido){
            SelectItem selectItem = new SelectItem(p.getIdPartido(),p.getEquipoA().getNombreEquipo()+" - "+p.getEquipoB().getNombreEquipo());
            selectItemOnePartidos.add(selectItem);
            }                              
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return selectItemOnePartidos;
    }

    public void setSelectItemOnePartidos(List<SelectItem> selectItemOnePartidos) {
        this.selectItemOnePartidos = selectItemOnePartidos;
    }

    public int getItemPartidoSelected() {
        return itemPartidoSelected;
    }

    public void setItemPartidoSelected(int itemPartidoSelected) {
        this.itemPartidoSelected = itemPartidoSelected;
    }


    
    
   
    
    
    
    
    
    
    
    

    
    
    
        

}
