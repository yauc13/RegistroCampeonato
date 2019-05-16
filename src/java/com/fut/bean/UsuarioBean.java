/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dao.UsuarioDao;
import com.fut.model.Usuario;
import com.fut.util.Cons;
import com.fut.util.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.json.JSONObject;

/**
 *
 * @author DIANA G
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    String ejex= "";
    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuario;
    
    private String accion;
    private String ip;
    private String mac;
    JSONObject  jasonObj ;
     List<double[]> listDataGrap;
     String urlCsv;
     int yMax;
     int yMin;
     

    public UsuarioBean() {  
   viewGraphic();
   
    }
     
    public void viewGraphic(){
        //listDataGrap=readCSVPatron("D:\\HORISOES\\Info\\Excel\\0_0_0_1.csv");
        //sex+"_"+edad+"_"+ejex+"_"+ejey+".csv"
        listDataGrap=readCSVPatron("D:\\HORISOES\\Info\\Excel\\"+urlCsv+".csv");
        armarJason();
    }
    
    private List<double[]>  readCSVPatron(String url){
        
        String linea="";
        String [] vline;
        List<String []> listV = new ArrayList<>();
        List<double []> listPatron = new ArrayList<>();
        double [][] mdata;
        int colum = 0;
        File archivo = new File(url);
        FileReader archivoLector;
        
        try{
            archivoLector = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(archivoLector);
            while(buffer.ready()){
                if(!(linea = buffer.readLine()).equals("\000")){
                   
                    vline= linea.split(";");
                    colum=vline.length;
                    String [] vlined= new String[colum];
                    for(int i=0;i<vline.length;i++){
                        
                        vlined[i]=(vline[i]);
                    }
                    listV.add(vlined);
                }
            }
            
            mdata=new double[listV.size()][colum];
            for(String[] v: listV){
                if(listV.indexOf(v)>0){
                for(int i=0;i<v.length;i++){
                    mdata[listV.indexOf(v)][i]= Double.parseDouble(v[i]); 
                }
                }
            }
            
            for (int j = 0; j < mdata[0].length; j++) {
                double[] vp = new double[mdata.length];
                System.err.println("column:"+vp.length);
                for (int i = 0; i < mdata.length; i++) {
                    vp[i] = mdata[i][j];

                }
                listPatron.add(vp);
            }
            
        }catch(Exception e){
            System.err.println(e);
        }
        return listPatron;
    }
    
    
    
    private void armarJason(){
        String ag="";
        for(double[] a:listDataGrap){
         if(listDataGrap.indexOf(a)==0){
             String ay="";            
            for(int i=0;i<a.length;i++){
                ay=ay+a[i]+",";
            }
           ejex=ay.substring(0,ay.length()-1);
         }else if(listDataGrap.indexOf(a)>3){
            String ay="";
            //System.err.println("[length] : "+a.length);
            for(int i=0;i<a.length;i++){
                ay=ay+a[i]+",";
            }
            ay=ay.substring(0,ay.length()-1);
            
           ag= ag+Cons.ARRAY_Y.replaceAll("arrayy", ay).replaceAll("arrayColor", colorLine(listDataGrap.indexOf(a)))+",";
        }
        }
        //ag=ag.substring(0,ag.length()-1);
        String par="{\"label\": \"datos\",\"showLine\":false,\"pointRadius\": 3,\"borderColor\": \"rgba(0,0,0,1)\",\"fill\": false,\"data\": [{x: 46,y: 2.5},{x: 49.5,y: 3.1},{x: 54,y: 4.1}]}";
        ag=ag+par;
        String strJ= Cons.ARRAY_GRAP.replaceAll("arraydataset", ag).replaceAll("arraylabel",ejex );
        jasonObj = new JSONObject(strJ);
    }
    
    
    public void loadGraphic(int sex,int edad, int ejex, int ejey){
        String url = sex+"_"+edad+"_"+ejex+"_"+ejey+".csv";
    }
    
    private String colorLine(int index){
        String color="";
        switch (index){
            case 4:
            case 10:
                color="255,0,0";
                break;
            case 5:
            case 9:
                color="255,160,0";
                break;
            case 6:
            case 8:
                color="255,255,0";
                break;            
            case 7:
                color="0,255,0";
                break;
        }
    return color;
    }
     

    public JSONObject getJasonObj() {
        return jasonObj;
    }

    public void setJasonObj(JSONObject jasonObj) {
        this.jasonObj = jasonObj;
    }
    
 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    

    
    
    
    public String iniciarSesion(){
        String redireccion = null;
        UsuarioDao dao;

            dao = new UsuarioDao();
            Usuario u = dao.leerID(usuario);
            if(u !=null){
                this.usuario = u;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                getIpMac();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "inicion sesion"));
               redireccion = "listaCampeonato?faces-redirect=true";
               
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
      
        return redireccion;
    }
    
    public String cerrarSesion(){
        String redireccion = "index?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.limpiar();
        return redireccion;
    }
    
    public void verificarSesion(){
        
        try {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us == null){
            
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml");
            //FacesContext.getCurrentInstance().getExternalContext().redirect("permisos?faces-redirect=true");
        }
        } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    this.usuario.setIdUsuario(0);
    this.usuario.setLoginUsuario("");
    this.usuario.setPasswordUsuario("");
    this.usuario.setRolUsuario("");
    }
    
    public void registrar() throws Exception {
    UsuarioDao dao;
    String direc = "true";
    try{
        
        dao = new UsuarioDao();
        Usuario u= dao.leerIDRegistro(usuario);
        if(u != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Usuario ya esta registrado"));
        }else{
            boolean reg = dao.registrar(usuario);
            if(reg == true){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro exitoso") );
                
                direc = "false";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
            }
        }
    }catch(Exception e){  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
        throw e;
    }   
    
    }
    
    public void modificar() throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.modificar(usuario);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void listar() throws Exception{
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        listaUsuario = dao.listar();
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Usuario usu) throws Exception{
            this.usuario = usu; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar(Usuario usu) throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    
    public String habilitarPermisos(int i){
        String bol=null;
       switch (i){
           case 1:
               //habilitar eliminar y editar
               if( "Administrador".equals(usuario.getRolUsuario())){
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
            case 3:
                
                break;
       
       }
        
    return bol;
    }
    
    private void getIpMac() {
        this.ip = Util.getIpAddress();
       // this.mac = Util.getMacAddress();
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUrlCsv() {
        return urlCsv;
    }

    public void setUrlCsv(String urlCsv) {
        this.urlCsv = urlCsv;
    }
    
    
        
}
