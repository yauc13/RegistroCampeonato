/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Tarjeta;
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
public class TarjetaDao extends Dao{
    
      public boolean insertTarjeta(Tarjeta tar){
        boolean reg = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_CARD);
            st.setInt(1, tar.getIdJugador());
            st.setInt(2, tar.getIdEquipo());
            st.setInt(3, tar.getIdPartido());
            st.setString(4, tar.getTypeCard());
            st.setBoolean(5, false);
            st.setInt(6, tar.getIdEquipoB());
                     
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
      
      public boolean cancelarTarjeta(Tarjeta tar){
        boolean reg = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.UPDATE_CARD);
            st.setBoolean(1, tar.isPagoTarjeta());
            st.setInt(2, tar.getIdTarjeta());         
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
 
 
    public List<Tarjeta> listAllCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        ResultSet rs;

        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_CARD_ALL_PLAYER);
            st.setInt(1, idCampeonato);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta"));
                

                lista.add(tar);

            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }

        return lista;
    }
    
    
    public List<Tarjeta> listPagarCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        ResultSet rs;

        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_CARD_PAG_PLAYER);
            st.setInt(1, idCampeonato);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta"));                
                lista.add(tar);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }

        return lista;
    }
    
    public List<Tarjeta> listCanCard(int idCampeonato) {
        List<Tarjeta> lista = null;
        ResultSet rs;

        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_CARD_CAN_PLAYER);
            st.setInt(1, idCampeonato);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Tarjeta tar = new Tarjeta();
                tar.setIdTarjeta(rs.getInt("idTarjeta"));
                tar.setNombreJugador(rs.getString("nombreJugador"));
                tar.setNombreEquipo(rs.getString("nombreEquipo"));
                tar.setNombreEquipoB(rs.getString("nombreEquipoB"));
                tar.setTypeCard(rs.getString("tipoTarjeta"));
                tar.setPagoTarjeta(rs.getBoolean("pagoTarjeta"));                
                lista.add(tar);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }
        return lista;
    }
    
}
