/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Grupo;
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
 * @author Yeison
 */
public class GrupoDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    public boolean insertGroup(Grupo group){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
           
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_GRUP);
            stmt.setString(1, group.getNombreGrupo());
            stmt.setInt(2, group.getNumClasificados());
            stmt.setInt(3, group.getIdCampeonato());
            stmt.setInt(4, group.getIdUsuario());
            
            int exRes = stmt.executeUpdate();
            if(exRes>0){
            reg = true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
        
    public List<Grupo> listGroupByChampionShip(int idChampionship){
            List<Grupo> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idGrupo, nombreGrupo,idUsuario FROM grupo WHERE idCampeonato = ?");
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_GROUP_BY_CHAMPIONSHIP);
                stmt.setInt(1, idChampionship);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Grupo cam = new Grupo();
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    cam.setNombreGrupo(rs.getString("nombreGrupo"));
                    cam.setNumClasificados(rs.getInt("clasificadosGrupo"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    lista.add(cam);               
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public Grupo leerID(int idGrupo) {
        Grupo usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareStatement("SELECT idGrupo, nombreGrupo, idUsuario FROM grupo WHERE idGrupo = ?");
                stmt = cx.prepareStatement("SELECT \"idGrupo\", \"nombreGrupo\", \"idUsuario\" FROM public.grupo WHERE \"idGrupo\" = ?");
                stmt.setInt(1, idGrupo);
                
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Grupo();
                    usus.setIdGrupo(rs.getInt("idGrupo"));
                    usus.setNombreGrupo(rs.getString("nombreGrupo"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                                       
                }
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        
    
    public boolean updateGroup(Grupo gru){
        boolean resp=false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            //stmt = cx.prepareStatement("UPDATE grupo SET nombreGrupo = ? WHERE idGrupo = ?");
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_GRUP);
            stmt.setString(1, gru.getNombreGrupo());                      
            stmt.setInt(2, gru.getNumClasificados());          
            stmt.setInt(3, gru.getIdGrupo());  
            int exRes = stmt.executeUpdate();
            if(exRes>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return resp;
    }
    
    public void deleteGroup(Grupo grupo){
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            //stmt = cx.prepareStatement("DELETE FROM grupo  WHERE idGrupo = ?");
            stmt = cx.prepareStatement("DELETE FROM public.grupo  WHERE \"idGrupo\" = ?");
            stmt.setInt(1, grupo.getIdGrupo());          
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }
}
