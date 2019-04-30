/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Jugador;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import com.fut.util.Util;
import java.sql.Connection;
import java.sql.Date;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class JugadorDao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    public boolean registrar(Jugador cam){
        boolean reg = false;
        Date birth = null;
        if(cam.getBirthday()!= null){
           birth = new java.sql.Date((cam.getBirthday()).getTime());
        }
        try{
            cx = DaoUtil.ConectionDriveDB();            
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_PLAYER);
            stmt.setString(1, cam.getNombreJugador());
            stmt.setDate(2, birth);
            stmt.setInt(3, cam.getGolJugador());
            stmt.setInt(4, cam.getIdEquipoJugador());
            stmt.setInt(5, cam.getIdUsuario());
            stmt.setString(6, cam.getFotoJugador());
                       
            int resul =stmt.executeUpdate();
            if(resul>0){
            reg = true;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    

        
    public List<Jugador> listar(Equipo camp){
            List<Jugador> lista = null;

            try{
                cx = DaoUtil.ConectionDriveDB();                
                stmt = cx.prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\", \"fotoJugador\" , birthday FROM public.jugador WHERE \"idEquipoJugador\" = ? ORDER BY \"idJugador\"");
                stmt.setInt(1, camp.getIdEquipo());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jugador cam = new Jugador();
                    cam.setIdJugador(rs.getInt("idJugador"));
                    cam.setNombreJugador(rs.getString("nombreJugador"));
                    cam.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    cam.setBirthday(rs.getDate("birthday"));
                    cam.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    if(!"".equals(rs.getString("fotoJugador")) && rs.getString("fotoJugador")!=null){
                        cam.setFotoJugador(rs.getString("fotoJugador"));                        
                    }else{
                        cam.setFotoJugador(Util.DEFAULTPHOTO);
                    }


                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public List<Jugador> listarGoleadores(Campeonato camp){
            List<Jugador> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_GOLEADORES);
                stmt.setInt(1, camp.getIdCampeonato());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jugador cam = new Jugador();
                    cam.setIdJugador(rs.getInt("idJugador"));
                    cam.setNombreJugador(rs.getString("nombreJugador"));                  
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));   
                    cam.setNumGol(rs.getInt("count"));
 
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
        public List<Jugador> listarJugadoresEquipo(int idEquipo) {
            List<Jugador> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                stmt = cx.prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\" FROM public.jugador WHERE \"idEquipoJugador\" = ?");
                stmt.setInt(1, idEquipo);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jugador cam = new Jugador();
                    cam.setIdJugador(rs.getInt("idJugador"));
                    cam.setNombreJugador(rs.getString("nombreJugador"));
                    cam.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    cam.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));

                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
        public byte[] traerImageByte(String productId) {
            
		
		
		byte[] productImage = null;
 
		try {
                    cx = DaoUtil.ConectionDriveDB();
		stmt = cx.prepareCall("SELECT \"fotoJugador\" FROM public.jugador WHERE \"idJugador\"=?");
		stmt.setString(1, productId);
		rs = stmt.executeQuery();
 
		while (rs.next()) {
			productImage = rs.getBytes("fotoJugador");
		}
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
		return productImage;
	}
    
    public Jugador leerID(int idJugador){
        Jugador usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareStatement("SELECT idJugador, nombreJugador,idUsuario FROM jugador WHERE idJugador = ?");
                stmt = cx.prepareStatement("SELECT \"idJugador\", \"nombreJugador\",\"idUsuario\" FROM public.jugador WHERE \"idJugador\" = ?");
                stmt.setInt(1, idJugador);
                
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Jugador();
                    usus.setIdJugador(rs.getInt("idJugador"));
                    usus.setNombreJugador(rs.getString("nombreJugador"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                                       
                }
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        
    
    public boolean modificar(Jugador cam){
        boolean resp = false;
        Date birth = null;
        if(cam.getBirthday()!= null){
           birth = new java.sql.Date((cam.getBirthday()).getTime());
        }
        try{
            cx = DaoUtil.ConectionDriveDB();
            //stmt = cx.prepareStatement("UPDATE jugador SET nombreJugador = ? WHERE idJugador = ?");
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_PLAYER);
            stmt.setString(1, cam.getNombreJugador());
            stmt.setDate(2, birth);
            stmt.setString(3, cam.getFotoJugador());
            stmt.setInt(4, cam.getIdJugador());          
            int resul =stmt.executeUpdate();
            if(resul>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return resp;
    }
    
    public void eliminar(Jugador cam) {
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            //stmt = cx.prepareStatement("DELETE FROM jugador  WHERE idJugador = ?");
            stmt = cx.prepareStatement("DELETE FROM public.jugador  WHERE \"idJugador\" = ?");
            stmt.setInt(1, cam.getIdJugador());          
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }    
}
