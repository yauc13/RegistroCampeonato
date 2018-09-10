/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Grupo;
import com.fut.model.Jornada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YeisonUrrea
 */
public class JornadaDao extends Dao {
    public boolean registrar(Jornada d) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.jornada (\"nombreJornada\",\"idGrupo\",\"idUsuario\") values(?,?,?)");
            st.setString(1, d.getNombreJornada());
            st.setInt(2, d.getIdGrupo());
            st.setInt(3, d.getIdUsuario());
            
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
        
    public List<Jornada> listar(Grupo camp) throws Exception{
            List<Jornada> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idJornada, nombreJornada FROM jornada WHERE idGrupoJornada = ?");
                st.setInt(1, camp.getIdGrupo());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jornada cam = new Jornada();
                    cam.setIdJornada(rs.getInt("idJornada"));
                    cam.setNombreJornada(rs.getString("nombreJornada"));
                    
                    
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Jornada leerID(Jornada cam) throws Exception{
        Jornada usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idJornada, nombreJornada FROM jornada WHERE idJornada = ?");
                st.setInt(1, cam.getIdJornada());
                st.setString(2, cam.getNombreJornada());
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Jornada();
                    usus.setIdJornada(rs.getInt("idJornada"));
                    usus.setNombreJornada(rs.getString("nombreJornada"));
                                       
                }
                
            }catch(SQLException e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Jornada cam) throws Exception{
        boolean resp = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE usuario SET passwordUsuario = ? WHERE idUsuario = ?");
            st.setString(1, cam.getNombreJornada());                      
            st.setInt(2, cam.getIdJornada());          
            st.executeUpdate();
            resp = true;
        }catch(SQLException e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return resp;
    }
    
    public boolean eliminar(Jornada cam){
        boolean resp= false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM usuario  WHERE idUsuario = ?");
            st.setInt(1, cam.getIdJornada());          
            st.executeUpdate();
            resp = true;
        }catch(SQLException e){
            
        }finally{
            try {
                this.Cerrar();
            } catch (Exception ex) {
                Logger.getLogger(JornadaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }
}
