/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison Urrea
 */
public class CampeonatoDao {
    private Connection cx;
    private PreparedStatement stmt;
    private ResultSet rs;
    public boolean registrar(Campeonato cam) {
        boolean reg = false;
        
        try{
            cx = DaoUtil.ConectionDriveDB();            
            stmt = cx.prepareStatement(SqlAdminFutSal.REGISTER_CAMPEONATO);
            int count = 1;
            stmt.setString(count++, cam.getNombreCampeonato());
            stmt.setInt(count++, cam.getIdUsuario());
            stmt.setInt(count++, cam.getCostoPlanilla());
            stmt.setInt(count++, cam.getCostoAma());
            stmt.setInt(count++, cam.getCostoAzu());
            stmt.setInt(count++, cam.getCostoRoj());
            
            int res = stmt.executeUpdate();
            if(res>0){
            reg = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
            DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
        
    public List<Campeonato> listarCampeonato() {
            List<Campeonato> lista = null;
            
            try{
                cx = DaoUtil.ConectionDriveDB();  
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_CAMPEONATO);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public Campeonato leerID(Campeonato cam){
        Campeonato usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();  

                //PreparedStatement st = this.getCn().prepareStatement("SELECT idCampeonato, nombreCampeonato,idUsuario FROM campeonato WHERE idCampeonato = ?");
                stmt = cx.prepareStatement("SELECT \"idCampeonato\", \"nombreCampeonato\", \"idUsuario\" FROM public.campeonato WHERE \"idCampeonato\" = ?");
                stmt.setInt(1, cam.getIdCampeonato());
                stmt.setString(2, cam.getNombreCampeonato());
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Campeonato();
                    usus.setIdCampeonato(rs.getInt("idCampeonato"));
                    usus.setNombreCampeonato(rs.getString("nombreCampeonato"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));                   
                }
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Campeonato cam) {
        boolean reg = false;
        
        try{
            cx = DaoUtil.ConectionDriveDB();  
            
            stmt = cx.prepareStatement(SqlAdminFutSal.EDIT_CAMPEONATO);
            int count = 1;
            stmt.setString(count++, cam.getNombreCampeonato());                                  
            stmt.setInt(count++, cam.getCostoPlanilla());
            stmt.setInt(count++, cam.getCostoAma());
            stmt.setInt(count++, cam.getCostoAzu());
            stmt.setInt(count++, cam.getCostoRoj());
            stmt.setInt(count++, cam.getIdCampeonato()); 
            int res=stmt.executeUpdate();
            if(res>0){
            reg = true;
            }
            
        }catch(SQLException e){
            System.err.println(e); 
            
        }finally{
            DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
    public boolean deleteCampeonato(Campeonato cam){
        boolean reg=false;
        try{
            cx = DaoUtil.ConectionDriveDB();             
            stmt = cx.prepareStatement(SqlAdminFutSal.DELETE_CAMPEONATO);
            stmt.setInt(1, cam.getIdCampeonato());          
            int res = stmt.executeUpdate();  
            if(res>0){
            reg = true;
            }
        }catch(SQLException e){            
            System.err.println(e);
        }finally{          
           DaoUtil.closeConection(cx, stmt, rs);          
        }
        return reg;
    }
}
