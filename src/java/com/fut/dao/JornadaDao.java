/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Jornada;
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
 * @author YeisonUrrea
 */
public class JornadaDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    public boolean registrar(Jornada d) {
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt= cx.prepareStatement(SqlAdminFutSal.INSERT_FIXTURE);
            stmt.setString(1, d.getNombreJornada());
            stmt.setInt(2, d.getIdCampeonato());
            stmt.setDate(3, new java.sql.Date((d.getFechaJornada()).getTime()));
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
        
    public List<Jornada> listarJornadas(Campeonato camp) {
            List<Jornada> lista = null;
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt= cx.prepareCall(SqlAdminFutSal.SELECT_FIXTURE_CAMPEONATO);
                stmt.setInt(1, camp.getIdCampeonato());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jornada jor = new Jornada();
                    jor.setIdJornada(rs.getInt("idJornada"));
                    jor.setNombreJornada(rs.getString("nombreJornada"));
                    jor.setFechaJornada(rs.getDate("fechaJornada"));
                    PartidoDao dao = new PartidoDao();
                    jor.setListMatch(dao.listarPartidosJornada(jor));
                    
                    lista.add(jor);               
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public Jornada leerID(Jornada cam) throws Exception{
        Jornada usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt= cx.prepareStatement("SELECT idJornada, nombreJornada FROM jornada WHERE idJornada = ?");
                stmt.setInt(1, cam.getIdJornada());
                stmt.setString(2, cam.getNombreJornada());
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Jornada();
                    usus.setIdJornada(rs.getInt("idJornada"));
                    usus.setNombreJornada(rs.getString("nombreJornada"));
                                       
                }
                
            }catch(SQLException e){
                throw e;
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Jornada cam) {
        boolean resp = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt= cx.prepareStatement("UPDATE public.jornada SET \"nombreJornada\" = ? WHERE \"idJornada\" = ?");
            stmt.setString(1, cam.getNombreJornada());                      
            stmt.setInt(2, cam.getIdJornada());          
            int res = stmt.executeUpdate();
            if(res>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return resp;
    }
    
    public boolean deleteJornada(Jornada cam){
        boolean resp= false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt= cx.prepareStatement("DELETE FROM public.jornada  WHERE \"idJornada\" = ?");
            stmt.setInt(1, cam.getIdJornada());          
            int res = stmt.executeUpdate();
            if(res>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{           
                DaoUtil.closeConection(cx, stmt, rs);     
        }
        return resp;
    }
}
