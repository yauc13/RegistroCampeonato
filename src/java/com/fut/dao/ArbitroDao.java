/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Arbitro;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YEISON URREA
 */
public class ArbitroDao extends Dao {

    public boolean registrarArbitro(Arbitro arb) {
        boolean reg = false;
        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_ARBITRO);
            st.setString(1, arb.getNombreArbitro());
            st.setInt(2, arb.getIdCampeonato());
            int res=st.executeUpdate();
            if(res>0){
            reg = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }
        return reg;
    }
    
     public boolean modificarArbitro(Arbitro arb) {
        boolean reg = false;
        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.UPDATE_ARBITRO);
            st.setString(1, arb.getNombreArbitro());
            st.setInt(2, arb.getIdArbitro());
            int res=st.executeUpdate();
            if(res>0){
            reg = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }
        return reg;
    }
     
     
    public boolean deleteReferee(int idArbitro){
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.arbitro  WHERE \"idArbitro\" = ?");
            st.setInt(1, idArbitro);          
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

    public List<Arbitro> listarArbitros(int idCampeonato) {
        List<Arbitro> lista = null;
        ResultSet rs;

        try {
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_ARBITRO_BY_CAMPEONATO);
            st.setInt(1, idCampeonato);
            rs = st.executeQuery();
            
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
            this.CloseConection();
        }

        return lista;
    }

}
