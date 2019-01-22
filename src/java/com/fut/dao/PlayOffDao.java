/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.PlayOff;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author YeisonUrrea
 */
public class PlayOffDao extends Dao{
    public boolean insertPlayOff(PlayOff play){
        boolean reg = false;
        ResultSet rs;
        try{
             this.cn = this.ConectionDataBase();
             this.cn.setAutoCommit(false);
            
            PreparedStatement st = this.cn.prepareStatement(SqlAdminFutSal.INSERT_PLAYOFF);
            st.setString(1, play.getNamePlayOff());
            st.setInt(2, play.getNumPartidos());
            st.setInt(3, play.getIdCampeonato());
            
            rs= st.executeQuery();
            int idPlayOff=0;
            while(rs.next()){                   
                idPlayOff = rs.getInt("idPlayOff");                                
                }
            int r=0;
            if(idPlayOff>0){
            for(Equipo e:play.getListTeam()){
                st = this.cn.prepareStatement(SqlAdminFutSal.INSERT_R_TEAM_PLAYOFF);
                 st.setInt(1, idPlayOff);
                st.setInt(2, e.getIdEquipo());
                int ins=st.executeUpdate();
                r= r+ins;
            }
            }
            if(r>0){
                reg=true;
            }
            this.cn.commit();
            
        }catch(SQLException e){
            try {
                this.cn.rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            System.out.println(e);
            
        }finally{
        this.CloseConection();
        }
        return reg;
    }
    
     public boolean updatePlayOff(PlayOff play){
        boolean resp=false;
        try{
            this.ConectionDataBase();
            
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.UPDATE_PLAYOFF);
            st.setString(1, play.getNamePlayOff());
            st.setInt(2, play.getNumPartidos());                     
            st.setInt(3, play.getIdPlayOff());  
            int exRes = st.executeUpdate();
            if(exRes>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
     
    public List<PlayOff> listPlayoffByChampionShip(int idChampionship){
            List<PlayOff> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idGrupo, nombreGrupo,idUsuario FROM grupo WHERE idCampeonato = ?");
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PLAYOFF_BY_CHAMPIONSHIP);
                st.setInt(1, idChampionship);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    PlayOff play = new PlayOff();
                    play.setIdPlayOff(rs.getInt("idPlayOff"));
                    play.setNamePlayOff(rs.getString("namePlayOff"));
                    play.setNumPartidos(rs.getInt("numPartidos"));
                    play.setIdCampeonato(rs.getInt("idCampeonato"));
                    
                    lista.add(play);               
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public PlayOff loadLatestMathPlayoff(int idChampionship){
    PlayOff play=null;
    ResultSet rs;           
            try{
                this.ConectionDataBase();                
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_LATEST_PLAYOFF_BY_CHAMPIONSHIP);
                st.setInt(1, idChampionship);
                rs = st.executeQuery();
               
                while(rs.next()){
                    play = new PlayOff();
                    play.setIdPlayOff(rs.getInt("idPlayOff"));
                    play.setNamePlayOff(rs.getString("namePlayOff"));
                    play.setNumPartidos(rs.getInt("numPartidos"));
                    play.setIdCampeonato(rs.getInt("idCampeonato"));         
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
    return play;
    }
    
    
}
