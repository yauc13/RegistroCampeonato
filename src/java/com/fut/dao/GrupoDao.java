/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Grupo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class GrupoDao extends Dao {
    public boolean registrar(Grupo cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO grupo (nombreGrupo,idCampeonato) values(?,?)");
            st.setString(1, cam.getNombreGrupo());
            st.setInt(2, cam.getIdCampeonato());
            
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
        
    public List<Grupo> listar(Campeonato camp) throws Exception{
            List<Grupo> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idGrupo, nombreGrupo FROM grupo WHERE idCampeonato = ?");
                st.setInt(1, camp.getIdCampeonato());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Grupo cam = new Grupo();
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    cam.setNombreGrupo(rs.getString("nombreGrupo"));
                    
                    
                    
                    lista.add(cam);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Grupo leerID(Grupo cam) throws Exception{
        Grupo usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idGrupo, nombreGrupo FROM grupo WHERE idGrupo = ?");
                st.setInt(1, cam.getIdGrupo());
                st.setString(2, cam.getNombreGrupo());
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Grupo();
                    usus.setIdGrupo(rs.getInt("idGrupo"));
                    usus.setNombreGrupo(rs.getString("nombreGrupo"));
                                       
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Grupo cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE usuario SET passwordUsuario = ? WHERE idUsuario = ?");
            st.setString(1, cam.getNombreGrupo());                      
            st.setInt(2, cam.getIdGrupo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Grupo cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM usuario  WHERE idUsuario = ?");
            st.setInt(1, cam.getIdGrupo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
}
