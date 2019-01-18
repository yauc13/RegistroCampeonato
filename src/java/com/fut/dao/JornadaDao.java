/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Jornada;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YeisonUrrea
 */
public class JornadaDao extends Dao {
    
    public boolean registrar(Jornada d) {
        boolean reg = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_FIXTURE);
            st.setString(1, d.getNombreJornada());
            st.setInt(2, d.getIdCampeonato());
            st.setDate(3, new java.sql.Date((d.getFechaJornada()).getTime()));
            int res = st.executeUpdate();
            if(res>0){
            reg = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
        
    public List<Jornada> listarJornadas(Campeonato camp) {
            List<Jornada> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_FIXTURE_CAMPEONATO);
                st.setInt(1, camp.getIdCampeonato());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jornada cam = new Jornada();
                    cam.setIdJornada(rs.getInt("idJornada"));
                    cam.setNombreJornada(rs.getString("nombreJornada"));
                    cam.setFechaJornada(rs.getDate("fechaJornada"));
                    lista.add(cam);               
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public Jornada leerID(Jornada cam) throws Exception{
        Jornada usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
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
                this.CloseConection();
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Jornada cam) {
        boolean resp = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.jornada SET \"nombreJornada\" = ? WHERE \"idJornada\" = ?");
            st.setString(1, cam.getNombreJornada());                      
            st.setInt(2, cam.getIdJornada());          
            int res = st.executeUpdate();
            if(res>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
    
    public boolean deleteJornada(Jornada cam){
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.jornada  WHERE \"idJornada\" = ?");
            st.setInt(1, cam.getIdJornada());          
            int res = st.executeUpdate();
            if(res>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{           
                this.CloseConection();     
        }
        return resp;
    }
}
