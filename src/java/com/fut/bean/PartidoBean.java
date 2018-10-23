/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.EquipoDao;
import com.fut.dao.GolDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
import com.fut.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped

public class PartidoBean implements Serializable{
    private Partido partido = new Partido();
    private Grupo grupo = new Grupo();
    private Usuario usuario = new Usuario();
    private PlayOff playOff = new PlayOff();
    private Campeonato campeonato = new Campeonato();
    private List<Partido> listaPartido;
    private List<Partido> listaPartidoPlayOff;
    private List<Jugador> listaJugadoresA;
    private List<Jugador> listaJugadoresB;
    private List<Gol> listaGolesA;
    private List<Gol> listaGolesB;
    private List<Partido> listMatchPlayOff;
    
    private List<SelectItem> selectItemOneEquipo;
    private List<SelectItem> selectItemOneEquipoB;
    
    private List<SelectItem> selectItemOnePlayEquipoA;
    private List<SelectItem> selectItemOnePlayEquipoB;
    
    private String codEquipoA;
    private String codEquipoB;
    private Partido verPartido;
    private String accion;
    private Equipo equipoA;
    private Equipo equipoB;

    public PartidoBean() {
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");
        playOff = (PlayOff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("play");
    }


    
          

    public String verPartido(Partido u) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entre los beans
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("verPartido", u);
        this.verPartido = u;
        EquipoDao equipoDao = new EquipoDao();
        this.equipoA = equipoDao.leerID(u.getIdEquipoA());
        this.equipoB = equipoDao.leerID(u.getIdEquipoB());
        direccion = "planillaPartido?faces-redirect=true";
        
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }    
    
  
    
    public void operar() throws Exception{
        switch(accion){
            case "Registrar":
                this.registrar();
                this.limpiarPartido();
                break;
            case "Modificar":
                this.modificar();
                this.limpiarPartido();
                break;
        }
    }
    
     public void operarPartidoPlayOff() throws Exception{
        switch(accion){
            case "Registrar":
                this.registrarPartidoPlayOff();
                this.limpiarPartido();
                break;
            case "Modificar":
                this.modificar();
                this.limpiarPartido();
                break;
        }
    }
    
    public void limpiarPartido(){
    this.partido = new Partido();
    this.codEquipoA = "";
    this.codEquipoB = "";
    }
    
    public void preparedRegisterMatch(){
        this.accion = "Registrar";
        
        limpiarPartido();
        this.cargarEquiposPlayOffA();
    }
    
    public void registrar() {
    PartidoDao dao;
    
        dao = new PartidoDao();
        this.partido.setIdGrupo(grupo.getIdGrupo());
        this.partido.setIdUsuario(usuario.getIdUsuario());
        this.partido.setIdEquipoA(Integer.parseInt(this.codEquipoA));
        this.partido.setIdEquipoB(Integer.parseInt(this.codEquipoB));
        dao.registrarPartido(partido);
        
        this.listar();
     
    }
    
     public void registrarPartidoPlayOff() {
    PartidoDao dao;
    
        dao = new PartidoDao();
        this.partido.setIdPlayOff(playOff.getIdPlayOff());
        this.partido.setIdUsuario(usuario.getIdUsuario());
        this.partido.setIdEquipoA(Integer.parseInt(this.codEquipoA));
        this.partido.setIdEquipoB(Integer.parseInt(this.codEquipoB));
        dao.registrarPartido(partido);
        
        this.listar();
     
    }
    
    public void modificar() throws Exception {
    PartidoDao dao;
    try{
        dao = new PartidoDao();
        dao.modificar(partido);
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
    
    public void listarInicio(){
    PartidoDao dao;
    
        if(this.isPostBack() == false){
        dao = new PartidoDao();
        playOff = (PlayOff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("play");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(grupo!=null){
        listaPartido = dao.listarJoin(grupo.getIdGrupo());
        }else if(playOff!=null){
            listMatchPlayOff = dao.listarPartidosPlayOff(playOff.getIdPlayOff());
        }
       // listMatchPlayOff = 
        
        }
  
    }
    
    public void listar() {
    PartidoDao dao;    
        dao = new PartidoDao();
        Grupo u = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(grupo!=null){
        listaPartido = dao.listarJoin(grupo.getIdGrupo());
        }else if(playOff!=null){
            listMatchPlayOff = dao.listarPartidosPlayOff(playOff.getIdPlayOff());
        }
    }
    
 
    
    public void listarPlanillasInicio() throws Exception{
    PartidoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new PartidoDao();
        Grupo camp = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        partido = (Partido) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("partido");
        
        JugadorDao jugadorDao = new JugadorDao();       
        listaJugadoresA = jugadorDao.listarJugadoresEquipo(partido.getEquipoA());
        listaJugadoresB = jugadorDao.listarJugadoresEquipo(partido.getEquipoB());
        
        
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
        public void listarPlanillas() throws Exception{
    PartidoDao dao;
    try{
       
        dao = new PartidoDao();
        Grupo camp = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        partido = (Partido) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("partido");
        
        JugadorDao jugadorDao = new JugadorDao();       
        listaJugadoresA = jugadorDao.listarJugadoresEquipo(partido.getEquipoA());
        listaJugadoresB = jugadorDao.listarJugadoresEquipo(partido.getEquipoB());
  
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void anotarGol(Jugador jug, Equipo equ, Partido par){
        GolDao golDao = new GolDao();
        Gol gol = new Gol();
        gol.setJugador(jug);
        gol.setEquipo(equ);
        gol.setPartido(par);
        try {
            golDao.registrar(gol);
            this.listarPlanillas();
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void leerID (Partido usu) throws Exception{
            this.partido = usu; 
            this.accion = "Modificar";
    }
    
    public String editarPlanilla(Partido par) throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("partido", par);
        direccion = "planillaPartido?faces-redirect=true";
        
    }catch(Exception e){  
        throw e;
    }   
    return direccion;
    }
    
    public String finalizarPartido() throws Exception{
        String direccion;
        PartidoDao dao;
        try{
            dao = new PartidoDao();
            partido.setGolA(listaGolesA.size());
            partido.setGolB(listaGolesB.size());
            dao.finalizarPartido(partido);
            direccion = "vistaGrupo?faces-redirect=true";
        }catch(Exception e){  
            throw e;
        }
        return direccion;
    }
    

   
    public void eliminar(Partido usu) throws Exception {
    PartidoDao dao;
    try{
        dao = new PartidoDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void cargarEquiposPlayOffA(){
        List<SelectItem> listSelecPlay = null;
        
            listSelecPlay = new ArrayList<>();
            EquipoDao dao = new EquipoDao();
            List<Equipo> listEquipo = dao.listarEquiposClasificados(campeonato.getIdCampeonato());
            listSelecPlay.clear();
            for (Equipo p:listEquipo){
            SelectItem selectItem = new SelectItem(p.getIdEquipo(), p.getNombreEquipo());
            listSelecPlay.add(selectItem);
            }                                   
       selectItemOnePlayEquipoA = listSelecPlay;
    }
    
    
    public void cargarEquiposPlayOffB(){
    List<Equipo> listEquipoFinal;
    List<SelectItem> listSelecPlay = null;
        try {
            listSelecPlay = new ArrayList<SelectItem>();
            EquipoDao dao = new EquipoDao();
            PartidoDao daoPartido = new PartidoDao();
            
            List<Equipo> listEquipo = dao.listarEquipoB(grupo,Integer.parseInt(codEquipoA));
            List<Partido> listPartido = daoPartido.listarPartidosEquipo(grupo,Integer.parseInt(codEquipoA));
            
            listSelecPlay.clear();
            int listEquipoRivales [];
            int [] idEquRival; 
            listEquipoFinal = new ArrayList<>();
            
            if(listPartido.size()>0){
                idEquRival= new int[listPartido.size()];
                for(Partido par:listPartido){
                    
                    if(par.getIdEquipoA()==Integer.parseInt(codEquipoA)){
                        idEquRival[listPartido.indexOf(par)]=par.getIdEquipoB();
                        
                    }else if(par.getIdEquipoB()==Integer.parseInt(codEquipoA)){
                        idEquRival[listPartido.indexOf(par)]=par.getIdEquipoA();
                    }               
                } 
 
                for (Equipo equ:listEquipo){
                    int indice= listEquipo.indexOf(equ);

                    int indRival=0;
                        for(int i=0; i<idEquRival.length;i++){    
                            if(idEquRival[i]==equ.getIdEquipo()){                            
                                indRival++;     
                            }
                        }                    
                        if(indRival==0){
                        listEquipoFinal.add(equ); 
                        }
                }
            }else{                
                  listEquipoFinal = listEquipo;                 
            }
            
            for (Equipo p:listEquipoFinal){                
                SelectItem selectItem = new SelectItem(p.getIdEquipo(), p.getNombreEquipo());
                listSelecPlay.add(selectItem);
                }
                                                    
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }    
        selectItemOnePlayEquipoB = listSelecPlay;
    }
    

    public List<Jugador> getListaJugadoresA() {
        return listaJugadoresA;
    }

    public void setListaJugadoresA(List<Jugador> listaJugadoresA) {
        this.listaJugadoresA = listaJugadoresA;
    }

    public List<Jugador> getListaJugadoresB() {
        return listaJugadoresB;
    }

    public void setListaJugadoresB(List<Jugador> listaJugadoresB) {
        this.listaJugadoresB = listaJugadoresB;
    }
    
        public String getCodEquipoA() {
        return codEquipoA;
    }

    public void setCodEquipoA(String codEquipoA) {
        this.codEquipoA = codEquipoA;
    }

    public String getCodEquipoB() {
        return codEquipoB;
    }

    public void setCodEquipoB(String codEquipoB) {
        this.codEquipoB = codEquipoB;
    }
    

    
    
    public Partido getPartido() {
        
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Partido> getListaPartido() {
        return listaPartido;
    }

    public void setListaPartido(List<Partido> listaPartido) {
        this.listaPartido = listaPartido;
    }

    

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<SelectItem> getSelectItemOneEquipo() {
        try {
            this.selectItemOneEquipo = new ArrayList<SelectItem>();
            EquipoDao dao = new EquipoDao();
            List<Equipo> listEquipo = dao.listar(grupo);
            selectItemOneEquipo.clear();
            for (Equipo p:listEquipo){
            SelectItem selectItem = new SelectItem(p.getIdEquipo(), p.getNombreEquipo());
            selectItemOneEquipo.add(selectItem);
            }                              
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return selectItemOneEquipo;
    }

    public void setSelectItemOneEquipo(List<SelectItem> selectItemOneEquipo) {
        this.selectItemOneEquipo = selectItemOneEquipo;
    }

    public List<SelectItem> getSelectItemOneEquipoB() {
        List<Equipo> listEquipoFinal;
        try {
            this.selectItemOneEquipoB = new ArrayList<SelectItem>();
            EquipoDao dao = new EquipoDao();
            PartidoDao daoPartido = new PartidoDao();
            
            List<Equipo> listEquipo = dao.listarEquipoB(grupo,Integer.parseInt(codEquipoA));
            List<Partido> listPartido = daoPartido.listarPartidosEquipo(grupo,Integer.parseInt(codEquipoA));
            
            selectItemOneEquipoB.clear();
            int listEquipoRivales [];
            int [] idEquRival; 
            listEquipoFinal = new ArrayList<>();
            
            if(listPartido.size()>0){
                idEquRival= new int[listPartido.size()];
                for(Partido par:listPartido){
                    
                    if(par.getIdEquipoA()==Integer.parseInt(codEquipoA)){
                        idEquRival[listPartido.indexOf(par)]=par.getIdEquipoB();
                        
                    }else if(par.getIdEquipoB()==Integer.parseInt(codEquipoA)){
                        idEquRival[listPartido.indexOf(par)]=par.getIdEquipoA();
                    }               
                } 
 
                for (Equipo equ:listEquipo){
                    int indice= listEquipo.indexOf(equ);

                    int indRival=0;
                        for(int i=0; i<idEquRival.length;i++){    
                            if(idEquRival[i]==equ.getIdEquipo()){                            
                                indRival++;     
                            }
                        }                    
                        if(indRival==0){
                        listEquipoFinal.add(equ); 
                        }
                }
            }else{                
                  listEquipoFinal = listEquipo;                 
            }
            
            for (Equipo p:listEquipoFinal){                
                SelectItem selectItem = new SelectItem(p.getIdEquipo(), p.getNombreEquipo());
                selectItemOneEquipoB.add(selectItem);
                }
                                                    
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return selectItemOneEquipoB;
    }

    public void setSelectItemOneEquipoB(List<SelectItem> selectItemOneEquipoB) {
        this.selectItemOneEquipoB = selectItemOneEquipoB;
    }

    
    
    
    public Partido getVerPartido() {
        return verPartido;
    }

    public void setVerPartido(Partido verPartido) {
        this.verPartido = verPartido;
    }

    public Equipo getEquipoA() {        
        return equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        this.equipoB = equipoB;
    }

    public List<Gol> getListaGolesA() {
        partido = (Partido) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("partido");
        
        GolDao golDao = new GolDao();       
        try {
            listaGolesA = golDao.listarGolesPartidoEquipoJoin(partido, partido.getEquipoA());
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaGolesA;
    }

    public void setListaGolesA(List<Gol> listaGolesA) {
        this.listaGolesA = listaGolesA;
    }

    public List<Gol> getListaGolesB() {
        partido = (Partido) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("partido");
        
        GolDao golDao = new GolDao();       
        try {
            listaGolesB = golDao.listarGolesPartidoEquipoJoin(partido, partido.getEquipoB());
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGolesB;
    }

    public void setListaGolesB(List<Gol> listaGolesB) {
        this.listaGolesB = listaGolesB;
    }

    public List<Partido> getListMatchPlayOff() {
        return listMatchPlayOff;
    }

    public void setListMatchPlayOff(List<Partido> listMatchPlayOff) {
        this.listMatchPlayOff = listMatchPlayOff;
    }

    public PlayOff getPlayOff() {
        return playOff;
    }

    public void setPlayOff(PlayOff playOff) {
        this.playOff = playOff;
    }

    public List<SelectItem> getSelectItemOnePlayEquipoA() {
        return selectItemOnePlayEquipoA;
    }

    public void setSelectItemOnePlayEquipoA(List<SelectItem> selectItemOnePlayEquipoA) {
        this.selectItemOnePlayEquipoA = selectItemOnePlayEquipoA;
    }

    public List<SelectItem> getSelectItemOnePlayEquipoB() {
        return selectItemOnePlayEquipoB;
    }

    public void setSelectItemOnePlayEquipoB(List<SelectItem> selectItemOnePlayEquipoB) {
        this.selectItemOnePlayEquipoB = selectItemOnePlayEquipoB;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public List<Partido> getListaPartidoPlayOff() {
        return listaPartidoPlayOff;
    }

    public void setListaPartidoPlayOff(List<Partido> listaPartidoPlayOff) {
        this.listaPartidoPlayOff = listaPartidoPlayOff;
    }
    
    
    
    
    
    
    
    
    
    
    
     
}
