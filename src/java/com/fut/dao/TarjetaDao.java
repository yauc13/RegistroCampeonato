/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Tarjeta;
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
public class TarjetaDao{
     private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
      public boolean insertTarjeta(Tarjeta tar){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_CARD);
            stmt.setInt(1, tar.getIdJugador());
            stmt.setInt(2, tar.getIdEquipo());
            stmt.setInt(3, tar.getIdPartido());
            stmt.setString(4, tar.getTypeCard());
            stmt.setBoolean(5, false);
            stmt.setInt(6, tar.getIdEquipoB());
                     
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
      
      public boolean cancelarTarjeta(Tarjeta tar){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_CARD);
            stmt.setBoolean(1, tar.isPagoTarjeta());
            stmt.setInt(2, tar.getIdTarjeta());         
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
 
 
    public List<Tarjeta> listAllCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        

        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_CARD_ALL_PLAYER);
            stmt.setInt(1, idCampeonato);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta"));
                tar.setFechaTarjeta(rs.getDate("fechaPartido"));
                

                lista.add(tar);

            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }

        return lista;
    }
    
    
    public List<Tarjeta> listPagarCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        

        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_CARD_PAG_PLAYER);
            stmt.setInt(1, idCampeonato);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta")); 
                tar.setFechaTarjeta(rs.getDate("fechaPartido"));
                lista.add(tar);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }

        return lista;
    }
    
    public List<Tarjeta> listCanCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        
        try {
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_CARD_CAN_PLAYER);
            stmt.setInt(1, idCampeonato);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta"));  
                tar.setFechaTarjeta(rs.getDate("fechaPartido"));
                lista.add(tar);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }
        return lista;
    }
    
}
