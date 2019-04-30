/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Arbitro;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author YEISON URREA
 */
public class ArbitroDao{

    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    public boolean registrarArbitro(Arbitro arb) {
        boolean reg = false;
        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_ARBITRO);
            stmt.setString(1, arb.getNombreArbitro());
            stmt.setInt(2, arb.getIdCampeonato());
            int res=stmt.executeUpdate();
            if(res>0){
            reg = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
     public boolean modificarArbitro(Arbitro arb) {
        boolean reg = false;
        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_ARBITRO);
            stmt.setString(1, arb.getNombreArbitro());
            stmt.setInt(2, arb.getIdArbitro());
            int res=stmt.executeUpdate();
            if(res>0){
            reg = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
     
     
    public boolean deleteReferee(int idArbitro){
        boolean resp= false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement("DELETE FROM public.arbitro  WHERE \"idArbitro\" = ?");
            stmt.setInt(1, idArbitro);          
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

    public List<Arbitro> listarArbitros(int idCampeonato) {
        List<Arbitro> lista = null;
        

        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_ARBITRO_BY_CAMPEONATO);
            stmt.setInt(1, idCampeonato);
            rs = stmt.executeQuery();
            
            lista = new ArrayList();
            while (rs.next()) {
                Arbitro cam = new Arbitro();
                cam.setIdArbitro(rs.getInt("idArbitro"));
                cam.setNombreArbitro(rs.getString("nombreArbitro"));
                cam.setIdCampeonato(rs.getInt("idCampeonato"));
                lista.add(cam);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }
        
        return lista;
    }

}
