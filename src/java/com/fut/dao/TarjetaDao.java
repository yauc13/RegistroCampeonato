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
      
         public List<Tarjeta> listAllCard() throws Exception{
            List<Tarjeta> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idPartido\" = ? AND \"idEquipo\" = ?");
                
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Tarjeta cam = new Tarjeta();
                    cam.setIdTarjeta(rs.getInt("idGol"));
                    
                    JugadorDao jugadorDao = new JugadorDao();
                    Jugador jugador = jugadorDao.leerID(rs.getInt("idJugador"));
                    cam.setJugador(jugador);
                    
                    EquipoDao equipoDao = new EquipoDao();
                    Equipo equipo = equipoDao.leerID(rs.getInt("idEquipo"));
                    cam.setEquipo(equipo);
                    
                    PartidoDao partidoDao = new PartidoDao();
                    Partido partido = partidoDao.leerID(rs.getInt("idPartido"));
                    cam.setPartido(partido);
                 
                    lista.add(cam);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;    
    }
}
