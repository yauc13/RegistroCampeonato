/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class CampeonatoDao extends Dao{
    public boolean registrar(Campeonato cam) {
        boolean reg = false;
        try{
            this.ConectionDataBase();            
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.REGISTER_CAMPEONATO);
            st.setString(1, cam.getNombreCampeonato());
            st.setInt(2, cam.getIdUsuario());
            
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
        
    public List<Campeonato> listar() {
            List<Campeonato> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_CAMPEONATO);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Campeonato cam = new Campeonato();
                    cam.setIdCampeonato(rs.getInt("idCampeonato"));
                    cam.setNombreCampeonato(rs.getString("nombreCampeonato"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    cam.setCostoPlanilla(rs.getInt("costoPlanilla"));
                    cam.setCostoAma(rs.getInt("costoTarAma"));
                    cam.setCostoAzu(rs.getInt("costoTarAzu"));
                    cam.setCostoRoj(rs.getInt("costoTarRoj"));
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public Campeonato leerID(Campeonato cam){
        Campeonato usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
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
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Campeonato cam) {
        boolean reg = false;
        
        try{
            this.ConectionDataBase();
            
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.EDIT_CAMPEONATO);
            int count = 1;
            st.setString(count++, cam.getNombreCampeonato());                                  
            st.setInt(count++, cam.getCostoPlanilla());
            st.setInt(count++, cam.getCostoAma());
            st.setInt(count++, cam.getCostoAzu());
            st.setInt(count++, cam.getCostoRoj());
            st.setInt(count++, cam.getIdCampeonato()); 
            int res=st.executeUpdate();
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
    
    public boolean deleteCampeonato(Campeonato cam){
        boolean reg=false;
        try{
            this.ConectionDataBase();            
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.DELETE_CAMPEONATO);
            st.setInt(1, cam.getIdCampeonato());          
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
}
