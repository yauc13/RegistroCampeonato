/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeison
 */
public class CampeonatoDao extends Dao{
    public boolean registrar(Campeonato cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO campeonato (nombreCampeonato,idUsuario) values(?,?)");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.campeonato (\"nombreCampeonato\", \"idUsuario\") values(?,?)");
            st.setString(1, cam.getNombreCampeonato());
            st.setInt(2, cam.getIdUsuario());
            
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
                //PreparedStatement st = this.getCn().prepareCall("SELECT idCampeonato, nombreCampeonato,idUsuario FROM campeonato");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idCampeonato\", \"nombreCampeonato\", \"idUsuario\" FROM public.campeonato");
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Campeonato cam = new Campeonato();
                    cam.setIdCampeonato(rs.getInt("idCampeonato"));
                    cam.setNombreCampeonato(rs.getString("nombreCampeonato"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    
                    
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
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idCampeonato, nombreCampeonato,idUsuario FROM campeonato WHERE idCampeonato = ?");
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idCampeonato\", \"nombreCampeonato\", \"idUsuario\" FROM public.campeonato WHERE \"idCampeonato\" = ?");
                st.setInt(1, cam.getIdCampeonato());
                st.setString(2, cam.getNombreCampeonato());
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Campeonato();
                    usus.setIdCampeonato(rs.getInt("idCampeonato"));
                    usus.setNombreCampeonato(rs.getString("nombreCampeonato"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));                   
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Campeonato cam) throws Exception{
        boolean reg;
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE campeonato SET nombreCampeonato = ? WHERE idCampeonato = ?");
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.campeonato SET \"nombreCampeonato\" = ? WHERE \"idCampeonato\" = ?");
            st.setString(1, cam.getNombreCampeonato());                      
            st.setInt(2, cam.getIdCampeonato());          
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            reg =false;
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
    
    public boolean eliminar(Campeonato cam){
        boolean reg=false;
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM campeonato  WHERE idCampeonato = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.campeonato  WHERE \"idCampeonato\" = ?");
            st.setInt(1, cam.getIdCampeonato());          
            st.executeUpdate();           
            reg = true;
        }catch(SQLException e){            
            
        }finally{
            try {
                this.Cerrar();
            } catch (Exception ex) {
                Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reg;
    }
}
