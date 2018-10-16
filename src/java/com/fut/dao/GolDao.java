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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIANA G
 */
public class GolDao extends Dao{
    
    public boolean registrar(Gol cam) throws Exception{
        boolean reg = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.gol (\"idJugador\",\"idEquipo\",\"idPartido\") values(?,?,?)");
            st.setInt(1, cam.getJugador().getIdJugador());
            st.setInt(2, cam.getEquipo().getIdEquipo());
            st.setInt(3, cam.getPartido().getIdPartido());
                     
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
        return reg;
    }
    

        
    public List<Gol> listarGolesJugador(Jugador camp) throws Exception{
            List<Gol> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idJugador\" = ?");
                st.setInt(1, camp.getIdJugador());
                rs = st.executeQuery();
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
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public List<Gol> listarGolesEquipo(Equipo equi) throws Exception{
            List<Gol> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idEquipo\" = ?");
                st.setInt(1, equi.getIdEquipo());
                rs = st.executeQuery();
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
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;    
    }
    
     public List<Gol> listarGolesPartidoEquipo(Partido par, Equipo equ) throws Exception{
            List<Gol> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idPartido\" = ? AND \"idEquipo\" = ?");
                st.setInt(1, par.getIdPartido());
                st.setInt(2, equ.getIdEquipo());
                rs = st.executeQuery();
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
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;    
    }
    
        public List<Gol> listarGolesPartidoEquipoJoin(Partido par, Equipo equ) throws Exception{
            List<Gol> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT gol.\"idGol\", gol.\"idJugador\", gol.\"idEquipo\", gol.\"idPartido\""
                                                                + ",equ.\"nombreEquipo\", jug.\"nombreJugador\"\n" +
                                                                "FROM public.gol gol\n" +
                                                                "INNER JOIN public.equipo equ\n" +
                                                                "ON(gol.\"idEquipo\" = equ.\"idEquipo\")\n" +
                                                                "INNER JOIN public.jugador jug\n" +
                                                                "ON(gol.\"idJugador\" = jug.\"idJugador\""
                                                                + " AND gol.\"idPartido\" = ? AND gol.\"idEquipo\"= ?)"
                                                                + "ORDER BY gol.\"idGol\" ASC");
                st.setInt(1, par.getIdPartido());
                st.setInt(2, equ.getIdEquipo());
                rs = st.executeQuery();
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
            }catch(Exception e){
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;    
    }
    
    public Gol leerID(int idGol) throws Exception{
        Gol cam = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idGol\",\"idJugador\",\"idEquipo\",\"idPartido\" FROM public.gol WHERE \"idGol\" = ?");
                st.setInt(1, idGol);
               
                rs = st.executeQuery();
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
                throw e;
            }finally{
                this.CloseConection();
            }   
            return cam;
    }
    
        
    
    public void modificar(Gol cam) throws Exception{
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.gol SET \"idJugador\"=?,\"idEquipo\"=?,\"idPartido\"=? WHERE \"idGol\" = ?");
            st.setInt(1, cam.getJugador().getIdJugador());  
            st.setInt(2, cam.getEquipo().getIdEquipo());
            st.setInt(3, cam.getPartido().getIdPartido());  

            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }
    
    public void eliminar(Gol cam) throws Exception{
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.gol  WHERE \"idGol\" = ?");
            st.setInt(1, cam.getIdGol());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }    
}
