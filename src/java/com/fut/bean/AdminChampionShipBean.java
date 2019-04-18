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
import com.fut.dao.TarjetaDao;
import com.fut.dto.AdminChampionShipDTO;
import com.fut.logic.AdminChampionshipBo;
import com.fut.model.Arbitro;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;

import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Jugador;
import com.fut.model.PagoPlanilla;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
import com.fut.model.TablaEquipos;
import com.fut.model.Tarjeta;
import com.fut.model.Usuario;
import com.fut.util.Cons;
import com.fut.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Yeison Andres Urrea Chaves
 * 
 */

@ManagedBean
@ViewScoped

public class AdminChampionShipBean implements Serializable{
    private Grupo grupo = new Grupo();
    private PlayOff playOff = new PlayOff();
    
    private Usuario usuario = new Usuario();
    private Jornada jornada = new Jornada(); 
    private Jornada jornadaNew = new Jornada();
    private Partido partidoSelecJor;
    private Tarjeta tarjetaSel = new Tarjeta();
    private PagoPlanilla pagoPlanilla;
    private Equipo equipoPago;
    private List<Grupo> listaGrupo;
    private List<TablaEquipos> listaPosiciones;
    private List<Equipo> listaEquipos;
    private List<Jugador> listaGoleadores;
    private List<Tarjeta> listaTarjetas;
    private List<Tarjeta> listaTarjetasCan;
    private List<Tarjeta> listaTarjetasPag;
    
    private List<PlayOff> listaPlayOff;
    private List<Partido> listaPartidosJornada;
    private List<Equipo> listaPagoEquipos;
    private List<PagoPlanilla> listaPagoPlanilla; //lista de los pagos de planilla de de un equipo
    private String accion;
   
    private int rowSelJor; //columna de jornada expandida
    
    private List<SelectItem> selectItemOneGrupos; //para seleccionar grupo segun el campeonato
    private List<SelectItem> selectItemOnePartidos; //para seleccionar grupo segun el campeonato
    
    private Date horaPartido; //hora del partido
    
    
    private JugadorDao jugDao = new JugadorDao();
    private JornadaDao jorDao = new JornadaDao();
    private PartidoDao daoPar = new PartidoDao();
    private GrupoDao gruDao = new GrupoDao();
    private PlayOffDao plaDao = new PlayOffDao();
    private TarjetaDao tarDao = new TarjetaDao();
    private EquipoDao equDao = new EquipoDao();
    
    private final AdminChampionshipBo bo;
    private AdminChampionShipDTO dto;
    
    public AdminChampionShipBean() {
        bo = new AdminChampionshipBo();
        dto = new  AdminChampionShipDTO();
        
        partidoSelecJor = new Partido();
        rowSelJor = 0;
        usuario = (Usuario) Util.getObjectOfContext("usuario");
        dto.setCampeonato((Campeonato) Util.getObjectOfContext("campeonato"));    
        grupo = (Grupo) Util.getObjectOfContext("grupo");
        listaGoleadores = jugDao.listarGoleadores(dto.getCampeonato());
        listaTarjetas = tarDao.listAllCard(dto.getCampeonato().getIdCampeonato());
        listaTarjetasPag = tarDao.listPagarCard(dto.getCampeonato().getIdCampeonato());
        listaTarjetasCan = tarDao.listCanCard(dto.getCampeonato().getIdCampeonato());
        listaPagoEquipos = equDao.listarEquiposTotalPago(dto.getCampeonato().getIdCampeonato());
    }
    
    public void generateExcel(){
        bo.generateExcelFixture(dto);
    }
    
    public int verIdCampeonato(){        
        int idCamp = dto.getCampeonato().getIdCampeonato();
        return idCamp;
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        event.getTab().setLoaded(true);
    }
    
    
    /*Metodos para tab Jornada*/
    
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
        
    public void registrarJornada() {       
        jornadaNew.setIdCampeonato(dto.getCampeonato().getIdCampeonato());
        jornadaNew.setIdUsuario(usuario.getIdUsuario());
        boolean reg = jorDao.registrar(jornadaNew);
        if(reg){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada creada");       
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear Jornada");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.listar();  
    }
    
    public void modificarJornada() {
        if (jorDao.modificar(jornadaNew)) {
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada modificada");                   
        } else {
            Util.setMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo crear");             
        }
        this.listar();
    }
    
    public void preparedNewFixture(){
        this.accion = "Registrar";
        this.limpiarJornada();
    }
    
    private void limpiarJornada(){
        this.jornadaNew = new Jornada();
    }
    
    public void preparedAddMatchToFixture(Jornada jor){
        dto.setRenItemGroup(false);
        dto.setRenItemPlayoff(false);
        dto.setItemGroupPlaySelJor(0);
        dto.setItemGroupJor(0);
        dto.setItemPlayoffJor(0);
        this.jornada = jor;
        this.horaPartido = jornada.getFechaJornada();
        
        
    }
       
    public void loadListGroupOrPlayoff(){
        if(dto.getItemGroupPlaySelJor()==1){
            dto.setRenItemGroup(true);
            dto.setRenItemPlayoff(false);
            dto.setListGruposItemJor(gruDao.listGroupByChampionShip(dto.getCampeonato().getIdCampeonato()));
        }else if (dto.getItemGroupPlaySelJor()==2){
            dto.setRenItemGroup(false);
            dto.setRenItemPlayoff(true);
            dto.setListPlayoffItemJor(plaDao.listPlayoffByChampionShip(dto.getCampeonato().getIdCampeonato()));
        }
    }
    
    public void loadListMatchByGroupOrPlay(int origin){
         if(origin==1){
             //desde grupo
            dto.setListPartidosItemJor(daoPar.listarPartidosGrupoJor(dto.getItemGroupJor()));
        }else if (origin==2){
            dto.setListPartidosItemJor(daoPar.listarPartidosPlayOffJor(dto.getItemPlayoffJor()));
        }
        
    
    }
    
    
    public void agregarPartidoJornada()  {  
        
        if(daoPar.agregarPartidoJornada(jornada, dto.getItemMatchSelJor(), horaPartido)){
            rowSelJor = jornada.getIdJornada();
            this.listar();   
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Partido agregado a la jornada");                   
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo agregar el partido");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
         
    }
    
    public List<Partido> listarPartidosJornada(Jornada jor){
    return daoPar.listarPartidosJornada(jor);
    }
    
    public void deleteJornada(Jornada jor) {
    JornadaDao dao;    
        dao = new JornadaDao();
        boolean reg = dao.deleteJornada(jor);
         if(reg){
             Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Jornada Eliminada");          
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:no se pudo Eliminar", "porque tiene partidos asociados");
        FacesContext.getCurrentInstance().addMessage(null, message);}
        this.listar();  
    }
        
            
    public void quitarPartidoJornada(){
        rowSelJor = partidoSelecJor.getIdJornada();
        boolean resp = daoPar.quitarPartidoJornada(partidoSelecJor);
        if(resp){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Partido quitado de la jornada"); 
        }else{
            Util.setMessage(FacesMessage.SEVERITY_ERROR, "Error", "el partido no se pudo quitar"); 
        }
        partidoSelecJor = new Partido();
        this.listar();
    }
    
        
        
        
       /*Metodos para tab Grupo*/
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
    
     public void registrarGrupo() {
        this.grupo.setIdCampeonato(dto.getCampeonato().getIdCampeonato());
        this.grupo.setIdUsuario(usuario.getIdUsuario());       
        if(gruDao.insertGroup(grupo)){
           this.listar();
           Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Grupo Creado");           
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo Crear");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
    }
        
    public void modificarGrupo() {
        GrupoDao dao;
        dao = new GrupoDao();
        if (dao.updateGroup(grupo)) {
            this.listar();
            Util.setMessage(FacesMessage.SEVERITY_INFO, Cons.MSG_SUCCESSFUL, "Grupo Modificado");             
        } else {
            Util.setMessage(FacesMessage.SEVERITY_FATAL, Cons.MSG_ERROR, "no se pudo modificar");    
        }
    }
        
    public void preparedNewGroup(){
        this.accion = "Registrar";
        this.grupo = new Grupo();       
    }
    
    public void limpiarGrupo(){
        this.grupo = new Grupo();    
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
        Util.setObjectOfContext("grupo", grup);
        Util.setObjectOfContext("jornada", null);
        Util.setObjectOfContext("playoff", null);
        direccion = "vistaGrupo?faces-redirect=true";
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
    
        public void  listarPosicionesGrupo(){
        PartidoDao partidoDao= new PartidoDao();       
            //listaPosiciones.clear();
            
            listaPosiciones = partidoDao.listarTablaPosiciones(grupo.getIdGrupo());        
    }
        
        
    public List<Equipo> loadListTeamByGroup(int idGroup) {
        EquipoDao equipoDao = new EquipoDao();
        try {
            listaEquipos = equipoDao.listarEquiposPorGrupo(idGroup);
        } catch (Exception ex) {
            Logger.getLogger(AdminChampionShipBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEquipos;
    }

    
    
    
    /*Metodos para tab Playoff*/
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
    
    public void registrarPlayOff() {
        
        plaDao = new PlayOffDao();
        this.playOff.setIdCampeonato(dto.getCampeonato().getIdCampeonato());
        this.playOff.setListTeam(dto.getListaEquiposClasificados());
        if (plaDao.insertPlayOff(playOff)) {
            this.listar();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Play-Off Creado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo Crear");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void preparedNewPlayOff(){
        
        this.accion = "Registrar";
        this.limpiarPlayOff();
        dto.setListaEquiposClasificados(bo.listDefineTeamsForPlayoff(dto.getCampeonato().getIdCampeonato()));    
    }
    
     private void limpiarPlayOff(){
        this.playOff = new PlayOff();
    }
       
    public void modificarPlayOff() {

        plaDao = new PlayOffDao();
        if (plaDao.updatePlayOff(playOff)) {
            this.listar();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Grupo Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo modificar");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
        
    public String verPlayOff(PlayOff play){
    
    String direccion; 
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("play", play);
        direccion = "listaPartidoPlayOff?faces-redirect=true";
    return direccion;
    }
        

    /* Metodos para Equipos*/
    
    public void selectedTeamPay(Equipo equ){
        this.equipoPago = equ;
    }
    
    public void payTeam(){
        this.pagoPlanilla.setIdCampeonato(dto.getCampeonato().getIdCampeonato());
        this.pagoPlanilla.setIdEquipo(equipoPago.getIdEquipo());
        if(equDao.insertPayTeam(pagoPlanilla)){
        this.equipoPago = null;
        this.pagoPlanilla = null;
        listaPagoEquipos = equDao.listarEquiposTotalPago(dto.getCampeonato().getIdCampeonato());
        Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Se Agrego el pago");        
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo agregar el pago");          
            this.equipoPago = null;
            this.pagoPlanilla = null;
        }
    }
    
    public void listPayTeam(Equipo equ){
        selectedTeamPay(equ);
        this.listaPagoPlanilla = equDao.listarPagosPorEquipo(equipoPago.getIdEquipo());       
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
        listaGrupo = dao.listGroupByChampionShip(dto.getCampeonato().getIdCampeonato());
        listaGoleadores = jugDao.listarGoleadores(dto.getCampeonato());
        dto.setListaJornada(jorDao.listarJornadas(dto.getCampeonato()));
        listaPlayOff = plaDao.listPlayoffByChampionShip(dto.getCampeonato().getIdCampeonato());
        bo.listarArbitros(dto);
        }
    
    }
    
    public void listar() {
    GrupoDao dao;
    
        dao = new GrupoDao();       
        listaGrupo = dao.listGroupByChampionShip(dto.getCampeonato().getIdCampeonato());
        listaGoleadores = jugDao.listarGoleadores(dto.getCampeonato());
        dto.setListaJornada(jorDao.listarJornadas(dto.getCampeonato()));
        listaPlayOff = plaDao.listPlayoffByChampionShip(dto.getCampeonato().getIdCampeonato());
        bo.listarArbitros(dto);
    }
    
    
    public void payCard() {
        if (tarDao.cancelarTarjeta(tarjetaSel)) {
            listaTarjetas = tarDao.listAllCard(dto.getCampeonato().getIdCampeonato());
            listaTarjetasPag = tarDao.listPagarCard(dto.getCampeonato().getIdCampeonato());
            listaTarjetasCan = tarDao.listCanCard(dto.getCampeonato().getIdCampeonato());            
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Tarjeta Pagada");
        } else {
            Util.setMessage(FacesMessage.SEVERITY_ERROR, "Error", "Tarjeta no Pagada");
        }
    }
    
    
    /*metodos arbitro*/
    public void operateReferee() {
        switch (accion) {
            case "Registrar":
                this.registrarArbitro();
                
                break;
            case "Modificar":
                this.modificarArbitro();
                dto.setArbitro(new Arbitro());
                break;
        }
    }
    
    public void preparedNewReferee(){        
        this.accion = "Registrar";
       dto.setArbitro(new Arbitro());          
    }
    
    public void preparedEditReferee (Arbitro arb){
            dto.setArbitro(arb);
            this.accion = "Modificar";
    }
    
    
    public void registrarArbitro() {  
        bo.registrarArbitro(dto);
    }
    
    public void modificarArbitro() {  
        bo.modificarArbitro(dto);
    }
    
    public void deleteArbitro(Arbitro arb) {
        dto.setArbitro(arb);
        bo.deleteArbitro(dto);
    }
    
    public void listarArbitro() {  
        bo.listarArbitros(dto);
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

    public List<Equipo> getListaEquipos() {
        if(listaEquipos==null){
            listaEquipos = new ArrayList<>();
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

    public PartidoDao getDaoPar() {
        return daoPar;
    }

    public void setDaoPar(PartidoDao daoPar) {
        this.daoPar = daoPar;
    }

    public List<SelectItem> getSelectItemOneGrupos() {
         try {
            this.selectItemOneGrupos = new ArrayList<>();
            
            List<Grupo> listGrupo = gruDao.listGroupByChampionShip(dto.getCampeonato().getIdCampeonato());
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

    public List<Tarjeta> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(List<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public Tarjeta getTarjetaSel() {
        return tarjetaSel;
    }

    public void setTarjetaSel(Tarjeta tarjetaSel) {
        this.tarjetaSel = tarjetaSel;
    }

    public TarjetaDao getTarDao() {
        return tarDao;
    }

    public void setTarDao(TarjetaDao tarDao) {
        this.tarDao = tarDao;
    }

    public List<Tarjeta> getListaTarjetasCan() {
        if(listaTarjetasCan==null){
            listaTarjetasCan = new ArrayList<>();
        }
        return listaTarjetasCan;
    }

    public void setListaTarjetasCan(List<Tarjeta> listaTarjetasCan) {
        this.listaTarjetasCan = listaTarjetasCan;
    }

    public List<Tarjeta> getListaTarjetasPag() {
        if(listaTarjetasPag==null){
            listaTarjetasPag = new ArrayList<>();
        }
        return listaTarjetasPag;
    }

    public void setListaTarjetasPag(List<Tarjeta> listaTarjetasPag) {
        this.listaTarjetasPag = listaTarjetasPag;
    }

    public List<Equipo> getListaPagoEquipos() {
         if(listaPagoEquipos==null){
            listaPagoEquipos = new ArrayList<>();
        }
        return listaPagoEquipos;
    }

    public void setListaPagoEquipos(List<Equipo> listaPagoEquipos) {
        this.listaPagoEquipos = listaPagoEquipos;
    }

    public PagoPlanilla getPagoPlanilla() {
        if(pagoPlanilla==null){
            pagoPlanilla = new PagoPlanilla();
        }
        return pagoPlanilla;
    }

    public void setPagoPlanilla(PagoPlanilla pagoPlanilla) {
        this.pagoPlanilla = pagoPlanilla;
    }

    public EquipoDao getEquDao() {
        return equDao;
    }

    public void setEquDao(EquipoDao equDao) {
        this.equDao = equDao;
    }

    public Equipo getEquipoPago() {
        if(equipoPago==null){
            equipoPago = new Equipo();
        }
        return equipoPago;
    }

    public void setEquipoPago(Equipo equipoPago) {
        this.equipoPago = equipoPago;
    }

    public List<PagoPlanilla> getListaPagoPlanilla() {
        if(listaPagoPlanilla==null){
            listaPagoPlanilla = new ArrayList<>();
        }
        return listaPagoPlanilla;
    }

    public void setListaPagoPlanilla(List<PagoPlanilla> listaPagoPlanilla) {
        this.listaPagoPlanilla = listaPagoPlanilla;
    }

    public Date getHoraPartido() {
        return horaPartido;
    }

    public void setHoraPartido(Date horaPartido) {
        this.horaPartido = horaPartido;
    }

    public AdminChampionShipDTO getDto() {
        return dto;
    }

    public void setDto(AdminChampionShipDTO dto) {
        this.dto = dto;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
   
    
    
    
    
    
    
    
    

    
    
    
        

}
