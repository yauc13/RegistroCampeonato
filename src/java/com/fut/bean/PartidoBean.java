/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.EquipoDao;
import com.fut.dao.PartidoDao;
import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Partido;
import com.fut.model.Usuario;
import java.io.Serializable;
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

public class PartidoBean implements Serializable{
    private Partido partido = new Partido();
    private Grupo grupo = new Grupo();
    private Usuario usuario = new Usuario();
    private List<Partido> listaPartido;
    private Partido verPartido;
    private String accion;
    private Equipo equipoA;
    private Equipo equipoB;
    

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
    
    
    public void leerID (Partido usu) throws Exception{
            this.partido = usu; 
            this.accion = "Modificar";
    }
    
     public String verPartidosGrupos() throws Exception {
    
    String direccion = null;
    try{
        //sirve para pasar datos entres los beans
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("partido", partido);
        direccion = "listaGrupo?faces-redirect=true";
        
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
     
}
