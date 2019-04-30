/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Gol;
import com.fut.model.Jugador;
import com.fut.model.Partido;
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
 * @author DIANA G
 */
public class GolDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    public boolean insertGol(Gol cam){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_GOL);
            stmt.setInt(1, cam.getIdJugador());
            stmt.setInt(2, cam.getIdEquipo());
            stmt.setInt(3, cam.getIdPartido());
          
            stmt.setInt(4, cam.getIdEquipoB());
                     
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
    

        
    public List<Gol> listarGolesJugador(Jugador camp) {
            List<Gol> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idJugador\" = ?");
                stmt.setInt(1, camp.getIdJugador());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Gol cam = new Gol();
                    cam.setIdGol(rs.getInt("idGol"));
                    
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
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public List<Gol> listarGolesEquipo(Equipo equi) {
            List<Gol> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idEquipo\" = ?");
                stmt.setInt(1, equi.getIdEquipo());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Gol cam = new Gol();
                    cam.setIdGol(rs.getInt("idGol"));
                    
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
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;    
    }
    
     public List<Gol> listarGolesPartidoEquipo(Partido par, Equipo equ) {
            List<Gol> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idPartido\" = ? AND \"idEquipo\" = ?");
                stmt.setInt(1, par.getIdPartido());
                stmt.setInt(2, equ.getIdEquipo());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Gol cam = new Gol();
                    cam.setIdGol(rs.getInt("idGol"));
                    
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
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;    
    }
    
        public List<Gol> listarGolesPartidoEquipoJoin(int idPar, int idEqu) {
            List<Gol> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall("SELECT gol.\"idGol\", gol.\"idJugador\", gol.\"idEquipo\", gol.\"idPartido\""
                                                                + ",equ.\"nombreEquipo\", jug.\"nombreJugador\"\n" +
                                                                "FROM public.gol gol\n" +
                                                                "INNER JOIN public.equipo equ\n" +
                                                                "ON(gol.\"idEquipo\" = equ.\"idEquipo\")\n" +
                                                                "INNER JOIN public.jugador jug\n" +
                                                                "ON(gol.\"idJugador\" = jug.\"idJugador\""
                                                                + " AND gol.\"idPartido\" = ? AND gol.\"idEquipo\"= ?)"
                                                                + "ORDER BY gol.\"idGol\" ASC");
                stmt.setInt(1, idPar);
                stmt.setInt(2, idEqu);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Gol cam = new Gol();
                    cam.setIdGol(rs.getInt("idGol"));
                    
                    Jugador jugador = new Jugador();
                    jugador.setNombreJugador(rs.getString(6));
                    cam.setJugador(jugador);
                    
                    Equipo equipo = new Equipo(); 
                    equipo.setNombreEquipo(rs.getString(5));
                    cam.setEquipo(equipo);

                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;    
    }
    
    public Gol leerID(int idGol) {
        Gol cam = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareStatement("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idGol\" = ?");
                stmt.setInt(1, idGol);
               
                rs = stmt.executeQuery();
                while(rs.next()){
                   
                    cam.setIdGol(rs.getInt("idGol"));
                    
                    JugadorDao jugadorDao = new JugadorDao();
                    Jugador jugador = jugadorDao.leerID(rs.getInt("idJugador"));
                    cam.setJugador(jugador);
                    
                    EquipoDao equipoDao = new EquipoDao();
                    Equipo equipo = equipoDao.leerID(rs.getInt("idEquipo"));
                    cam.setEquipo(equipo);
                    
                    PartidoDao partidoDao = new PartidoDao();
                    Partido partido = partidoDao.leerID(rs.getInt("idPartido"));
                    cam.setPartido(partido);
              
                }
    
            }catch(Exception e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return cam;
    }
    
        
    
    public void modificar(Gol cam) {
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement("UPDATE public.gol SET \"idJugador\"=?,\"idEquipo\"=?,\"idPartido\"=? WHERE \"idGol\" = ?");
            stmt.setInt(1, cam.getJugador().getIdJugador());  
            stmt.setInt(2, cam.getEquipo().getIdEquipo());
            stmt.setInt(3, cam.getPartido().getIdPartido());  

            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }
    
    public void eliminar(Gol cam) {
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement("DELETE FROM public.gol  WHERE \"idGol\" = ?");
            stmt.setInt(1, cam.getIdGol());          
            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }    
}
