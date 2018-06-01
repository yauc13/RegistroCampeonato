/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class CampeonatoDao extends Dao{
    public boolean registrar(Campeonato cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO campeonato (nombreCampeonato) values(?)");
            st.setString(1, cam.getNombreCampeonato());
            
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
        
    public List<Campeonato> listar() throws Exception{
            List<Campeonato> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idCampeonato, nombreCampeonato FROM campeonato");
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Campeonato cam = new Campeonato();
                    cam.setIdCampeonato(rs.getInt("idCampeonato"));
                    cam.setNombreCampeonato(rs.getString("nombreCampeonato"));
                    
                    
                    lista.add(cam);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Campeonato leerID(Campeonato cam) throws Exception{
        Campeonato usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idCampeonato, nombreCampeonato FROM campeonato WHERE idCampeonato = ?");
                st.setInt(1, cam.getIdCampeonato());
                st.setString(2, cam.getNombreCampeonato());
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Campeonato();
                    usus.setIdCampeonato(rs.getInt("idCampeonato"));
                    usus.setNombreCampeonato(rs.getString("nombreCampeonato"));
                                       
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Campeonato cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE campeonato SET nombreCampeonato = ? WHERE idCampeonato = ?");
            st.setString(1, cam.getNombreCampeonato());                      
            st.setInt(2, cam.getIdCampeonato());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Campeonato cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM campeonato  WHERE idCampeonato = ?");
            st.setInt(1, cam.getIdCampeonato());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
}
