/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Yeison
 */
public class EquipoDao extends Dao {
    public boolean registrar(Equipo cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO equipo (nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo) values(?,?,?,?,?,?,?)");
            st.setString(1, cam.getNombreEquipo());
            st.setInt(2, cam.getPgEquipo());
            st.setInt(3, cam.getPeEquipo());
            st.setInt(4, cam.getPpEquipo());
            st.setInt(5, cam.getGfEquipo());
            st.setInt(6, cam.getGcEquipo());
            st.setInt(7, cam.getIdGrupoEquipo());
            
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
    

        
    public List<Equipo> listar(Grupo camp) throws Exception{
            List<Equipo> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo FROM equipo WHERE idGrupoEquipo = ?");
                st.setInt(1, camp.getIdGrupo());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
                    
                    
                    
                    lista.add(cam);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Equipo leerID(Equipo cam) throws Exception{
        Equipo usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idEquipo, nombreEquipo FROM equipo WHERE idEquipo = ?");
                st.setInt(1, cam.getIdEquipo());
                
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Equipo();
                    usus.setIdEquipo(rs.getInt("idEquipo"));
                    usus.setNombreEquipo(rs.getString("nombreEquipo"));
                                       
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Equipo cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE equipo SET nombreEquipo = ? WHERE idEquipo = ?");
            st.setString(1, cam.getNombreEquipo());                      
            st.setInt(2, cam.getIdEquipo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Equipo cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM equipo  WHERE idEquipo = ?");
            st.setInt(1, cam.getIdEquipo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
}
