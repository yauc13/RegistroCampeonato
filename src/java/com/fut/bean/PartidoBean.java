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
import com.fut.model.Equipo;
import com.fut.model.Gol;
import com.fut.model.Grupo;
import com.fut.model.Jugador;
import com.fut.model.Partido;
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
    private List<Partido> listaPartido;
    private List<Jugador> listaJugadoresA;
    private List<Jugador> listaJugadoresB;
    private List<Gol> listaGolesA;
    private List<Gol> listaGolesB;
    
    private List<SelectItem> selectItemOneEquipo;
    private List<SelectItem> selectItemOneEquipoB;
    private String codEquipoA;
    private String codEquipoB;
    private Partido verPartido;
    private String accion;
    private Equipo equipoA;
    private Equipo equipoB;


    
          

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
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    public void limpiar(){
    this.partido.setIdPartido(0);
    
    }
    
    public void registrar() throws Exception {
    PartidoDao dao;
    try{
        dao = new PartidoDao();
        this.partido.setIdGrupo(grupo.getIdGrupo());
        this.partido.setIdUsuario(usuario.getIdUsuario());
        this.partido.setIdEquipoA(Integer.parseInt(this.codEquipoA));
        this.partido.setIdEquipoB(Integer.parseInt(this.codEquipoB));
        dao.registrar(partido);
        this.listar();
    }catch(Exception e){  
        throw e;
    }
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
    
    public void listarInicio() throws Exception{
    PartidoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new PartidoDao();
        Grupo camp = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        listaPartido = dao.listar(camp);
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    PartidoDao dao;
    try{
        dao = new PartidoDao();
        Grupo u = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        grupo = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaPartido = dao.listar(u);
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listarPlanillas() throws Exception{
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
    
    public void anotarGol(Jugador jug, Equipo equ, Partido par){
        GolDao golDao = new GolDao();
        Gol gol = new Gol();
        gol.setJugador(jug);
        gol.setEquipo(equ);
        gol.setPartido(par);
        try {
            golDao.registrar(gol);
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
            listaGolesA = golDao.listarGolesPartidoEquipo(partido, partido.getEquipoA());
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
            listaGolesB = golDao.listarGolesPartidoEquipo(partido, partido.getEquipoB());
        } catch (Exception ex) {
            Logger.getLogger(PartidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGolesB;
    }

    public void setListaGolesB(List<Gol> listaGolesB) {
        this.listaGolesB = listaGolesB;
    }
    
    
    
    
     
}
