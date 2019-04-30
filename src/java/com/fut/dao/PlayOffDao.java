/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.PlayOff;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import java.sql.Connection;
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
public class PlayOffDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    public boolean insertPlayOff(PlayOff play){
        boolean reg = false;
        
        try{
             cx = DaoUtil.ConectionDriveDB();
             cx.setAutoCommit(false);
            
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_PLAYOFF);
            stmt.setString(1, play.getNamePlayOff());
            stmt.setInt(2, play.getNumPartidos());
            stmt.setInt(3, play.getIdCampeonato());
            
            rs= stmt.executeQuery();
            int idPlayOff=0;
            while(rs.next()){                   
                idPlayOff = rs.getInt("idPlayOff");                                
                }
            int r=0;
            if(idPlayOff>0){
            for(Equipo e:play.getListTeam()){
                stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_R_TEAM_PLAYOFF);
                 stmt.setInt(1, idPlayOff);
                stmt.setInt(2, e.getIdEquipo());
                int ins=stmt.executeUpdate();
                r= r+ins;
            }
            }
            if(r>0){
                reg=true;
            }
            cx.commit();
            
        }catch(SQLException e){
            try {
                cx.rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            System.out.println(e);
            
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
     public boolean updatePlayOff(PlayOff play){
        boolean resp=false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_PLAYOFF);
            stmt.setString(1, play.getNamePlayOff());
            stmt.setInt(2, play.getNumPartidos());                     
            stmt.setInt(3, play.getIdPlayOff());  
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
     
    public List<PlayOff> listPlayoffByChampionShip(int idChampionship){
            List<PlayOff> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idGrupo, nombreGrupo,idUsuario FROM grupo WHERE idCampeonato = ?");
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_PLAYOFF_BY_CHAMPIONSHIP);
                stmt.setInt(1, idChampionship);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public PlayOff loadLatestMathPlayoff(int idChampionship){
    PlayOff play=null;
               
            try{
                cx = DaoUtil.ConectionDriveDB();                
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_LATEST_PLAYOFF_BY_CHAMPIONSHIP);
                stmt.setInt(1, idChampionship);
                rs = stmt.executeQuery();
               
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
    return play;
    }
    
    
}
