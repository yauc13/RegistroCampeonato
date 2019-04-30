/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Penal;
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
public class PenalDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    public boolean insertPenal(Penal p){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_PENAL);
            int count=1;
            stmt.setInt(count++, p.getIdJugador());
            stmt.setInt(count++, p.getIdEquipo());
            stmt.setInt(count++, p.getIdPartido());          
            stmt.setInt(count++, p.getIdEquipoB());
                     
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
    
     public List<Penal> listarPenalesPartidoEquipo(int idPar, int idEqu) {
            List<Penal> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall(SqlAdminFutSal.LIST_PENAL_BY_TEAM_MATH);
                stmt.setInt(1, idPar);
                stmt.setInt(2, idEqu);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Penal cam = new Penal();
                    cam.setIdPenal(rs.getInt("idPenal"));
                    lista.add(cam);                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;    
    }
}
