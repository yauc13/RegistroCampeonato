/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Penal;
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
public class PenalDao extends Dao{
    public boolean insertPenal(Penal p){
        boolean reg = false;
        try{
            cn =this.ConectionDataBase();
            PreparedStatement st = this.cn.prepareStatement(SqlAdminFutSal.INSERT_PENAL);
            int count=1;
            st.setInt(count++, p.getIdJugador());
            st.setInt(count++, p.getIdEquipo());
            st.setInt(count++, p.getIdPartido());          
            st.setInt(count++, p.getIdEquipoB());
                     
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
    
     public List<Penal> listarPenalesPartidoEquipo(int idPar, int idEqu) {
            List<Penal> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.LIST_PENAL_BY_TEAM_MATH);
                st.setInt(1, idPar);
                st.setInt(2, idEqu);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Penal cam = new Penal();
                    cam.setIdPenal(rs.getInt("idPenal"));
                    lista.add(cam);                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;    
    }
}
