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
import com.fut.dao.PlayOffDao;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;

import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
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

public class AdminChampionShipBean implements Serializable{
    private Grupo grupo = new Grupo();
    private PlayOff playOff = new PlayOff();
    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private Jornada jornada = new Jornada(); 
    private Jornada jornadaNew = new Jornada();
    private Partido partidoSelecJor;
    private List<Grupo> listaGrupo;
    private List<TablaEquipos> listaPosiciones;
    private List<Equipo> listaEquipos;
    private List<Jugador> listaGoleadores;
    private List<Jornada> listaJornada;
    private List<PlayOff> listaPlayOff;
    private List<Partido> listaPartidosJornada;
    private String accion;
   
    private int rowSelJor; //columna de jornada expandida
    
    private List<SelectItem> selectItemOneGrupos; //para seleccionar grupo segun el campeonato
    private List<SelectItem> selectItemOnePartidos; //para seleccionar grupo segun el campeonato
    private int itemGrupoSelected;
    private int itemPartidoSelected; //partido seleccionado para agregar a una jornada
    
    private JugadorDao jugDao = new JugadorDao();
    private JornadaDao jorDao = new JornadaDao();
    private PartidoDao parDao = new PartidoDao();
    private GrupoDao gruDao = new GrupoDao();
    private PlayOffDao plaDao = new PlayOffDao();
    
    public AdminChampionShipBean() {
        partidoSelecJor = new Partido();
        rowSelJor = 0;
       
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
    
    public void operarGroup() {
    switch(accion){
        case "Registrar":
            this.registrarGrupo();
            this.limpiarGrupo();
            break;
        case "Modificar":
            this.modificarGrupo();
            this.limpiarGrupo();
            break;
    }
    }
    
    public void operatePlayOff() {
    switch(accion){
        case "Registrar":
            this.registrarPlayOff();
            this.limpiarPlayOff();
            break;
        case "Modificar":
            this.modificarPlayOff();
            this.limpiarPlayOff();
            break;
    }
    }
    
    public void operarJornada() {
    switch(accion){
        case "Registrar":
            this.registrarJornada();
            this.limpiarJornada();
            break;
        case "Modificar":
            this.modificarJornada();
            this.limpiarJornada();
            break;
    }
    }
    

    
    public void registrarGrupo() {
    GrupoDao dao;
    
        dao = new GrupoDao();
        
        this.grupo.setIdCampeonato(campeonato.getIdCampeonato());
        this.grupo.setIdUsuario(usuario.getIdUsuario());       
        if(dao.insertGroup(grupo)){
           this.listar();
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Grupo Creado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo Crear");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
    }
    
    public void registrarPlayOff() {
        
        plaDao = new PlayOffDao();
        this.playOff.setIdCampeonato(campeonato.getIdCampeonato());
        if (plaDao.insertPlayOff(playOff)) {
            this.listar();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Play-Off Creado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo Crear");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
        public void registrarJornada() {       
        jornadaNew.setIdCampeonato(campeonato.getIdCampeonato());
        jornadaNew.setIdUsuario(usuario.getIdUsuario());
        boolean reg = jorDao.registrar(jornadaNew);
        if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear Jornada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.listar();  
    }
    
    public void preparedNewFixture(){
        this.accion = "Registrar";
        this.limpiarJornada();
    }
    
    public void preparedNewPlayOff(){
        this.accion = "Registrar";
        this.limpiarPlayOff();
    }
    
    public void preparedNewGroup(){
        this.accion = "Registrar";
        this.grupo = new Grupo();
        
    }
    
    public void limpiarGrupo(){
        this.grupo = new Grupo();    
    }
    
    private void limpiarJornada(){
        this.jornadaNew = new Jornada();
    }
    
     private void limpiarPlayOff(){
        this.playOff = new PlayOff();
    }
    
    
    

    
    public void modificarJornada()  {
        if(jorDao.modificar(jornadaNew)){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.listar();    
    }
    
        public void modificarGrupo() {
    GrupoDao dao;
    
        dao = new GrupoDao();
        if(dao.updateGroup(grupo)){
           this.listar();
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Grupo Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
       
    }
        
    public void modificarPlayOff() {
       
        plaDao = new PlayOffDao();
        if(plaDao.updatePlayOff(playOff)){
           this.listar();
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Grupo Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
       
    }
        
    public void agregarPartidoJornada()  {  
        
        parDao.agregarPartidoJornada(jornada.getIdJornada(), itemPartidoSelected);    
        rowSelJor = jornada.getIdJornada();
        this.listar();    
    }
    
    
 private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
    }
    
    public void listarInicio(){
    GrupoDao dao;
    
        if(this.isPostBack() == false){
        dao = new GrupoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        
        listaGrupo = dao.listGroupByChampionShip(campeonato);
        listaGoleadores = jugDao.listarGoleadores(campeonato);
        listaJornada = jorDao.listarJornadas(campeonato);
        listaPlayOff = plaDao.listGroupByChampionShip(campeonato);
        }
    
    }
    
    public void listar() {
    GrupoDao dao;
    
        dao = new GrupoDao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");        
        listaGrupo = dao.listGroupByChampionShip(campeonato);
        listaGoleadores = jugDao.listarGoleadores(campeonato);
        listaJornada = jorDao.listarJornadas(campeonato);
        listaPlayOff = plaDao.listGroupByChampionShip(campeonato);
    
    }
    
    public List<Partido> listarPartidosJornada(Jornada jor){
    return parDao.listarPartidosJornada(jor);
    }
    
    
    public void preparedEditGroup (Grupo gru){
            this.grupo = gru; 
            this.accion = "Modificar";
    }
    

    
    public String verEquiposGrupos() {
    
    String direccion = null;   
        //sirve para pasar datos entres los beans
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        direccion = "listaEquipo?faces-redirect=true";
    return direccion;
    }
    
    public String verGrupo(Grupo grup){
    
    String direccion = null; 
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupo", grup);
        direccion = "vistaGrupo?faces-redirect=true";
    return direccion;
    }
    
    public String verPlayOff(PlayOff play){
    
    String direccion; 
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("play", play);
        direccion = "listaPartidoPlayOff?faces-redirect=true";
    return direccion;
    }
    
     public String verPartidosGrupo()  {    
    String direccion;
        //sirve para pasar datos entres los beans
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        direccion = "listaPartido?faces-redirect=true";
    return direccion;
    }
    
   
    public void deleteGroup(Grupo usu) {
    GrupoDao dao;    
        dao = new GrupoDao();
        dao.deleteGroup(usu);
        this.listar();  
    }
    
    public void deleteJornada(Jornada jor) {
    JornadaDao dao;    
        dao = new JornadaDao();
        boolean reg = dao.deleteJornada(jor);
         if(reg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada Eliminada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:no se pudo Eliminar", "porque tiene partidos asociados");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listar();  
    }

    public String habilitarPermisos(Campeonato camp, int i){
        String bol = null;
       switch (i){
           case 1:
               //habilitar deleteGroup y editar
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
            //listaPosiciones.clear();
            grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
            listaPosiciones = partidoDao.listarTablaPosiciones(grupo);        
    }
    
    public void quitarPartidoJornada(){
        rowSelJor = partidoSelecJor.getIdJornada();
        boolean resp = parDao.quitarPartidoJornada(partidoSelecJor);
        if(resp){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Partido quitado de la jornada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "el partido no se pudo quitar");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        //rowSelJor = jornada.getIdJornada();
        
        partidoSelecJor = new Partido();
        this.listar();
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
            Logger.getLogger(AdminChampionShipBean.class.getName()).log(Level.SEVERE, null, ex);
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
            
            List<Grupo> listGrupo = gruDao.listGroupByChampionShip(campeonato);
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
            
            List<Partido> listPartido = parDao.listarPartidosGrupoJor(itemGrupoSelected);
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

    public int getRowSelJor() {
        return rowSelJor;
    }

    public void setRowSelJor(int rowSelJor) {
        this.rowSelJor = rowSelJor;
    }

    public Partido getPartidoSelecJor() {
        return partidoSelecJor;
    }

    public void setPartidoSelecJor(Partido partidoSelecJor) {
        this.partidoSelecJor = partidoSelecJor;
    }

    public Jornada getJornadaNew() {
        return jornadaNew;
    }

    public void setJornadaNew(Jornada jornadaNew) {
        this.jornadaNew = jornadaNew;
    }

    public PlayOff getPlayOff() {
        return playOff;
    }

    public void setPlayOff(PlayOff playOff) {
        this.playOff = playOff;
    }

    public List<PlayOff> getListaPlayOff() {
        return listaPlayOff;
    }

    public void setListaPlayOff(List<PlayOff> listaPlayOff) {
        this.listaPlayOff = listaPlayOff;
    }

    public PlayOffDao getPlaDao() {
        return plaDao;
    }

    public void setPlaDao(PlayOffDao plaDao) {
        this.plaDao = plaDao;
    }
    
    
    
    
    
    
    


    
    
    
   
    
    
    
    
    
    
    
    

    
    
    
        

}
