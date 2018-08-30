/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Jugador;
import com.fut.util.Util;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class JugadorDao extends Dao {
    public boolean registrar(Jugador cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO jugador (nombreJugador,fechaNacimiento,golJugador,idEquipoJugador,idUsuario) values(?,?,?,?,?)");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.jugador (\"nombreJugador\",\"fechaNacimiento\",\"golJugador\",\"idEquipoJugador\",\"idUsuario\",\"fotoJugador\") values(?,?,?,?,?,?)");
            st.setString(1, cam.getNombreJugador());
            st.setString(2, cam.getFechaNacimiento());
            st.setInt(3, cam.getGolJugador());
            st.setInt(4, cam.getIdEquipoJugador());
            st.setInt(5, cam.getIdUsuario());
            st.setString(6, cam.getFotoJugador());
            
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
    

        
    public List<Jugador> listar(Equipo camp) throws Exception{
            List<Jugador> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\", \"fotoJugador\" FROM public.jugador WHERE \"idEquipoJugador\" = ? ORDER BY \"idJugador\"");
                st.setInt(1, camp.getIdEquipo());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jugador cam = new Jugador();
                    cam.setIdJugador(rs.getInt("idJugador"));
                    cam.setNombreJugador(rs.getString("nombreJugador"));
                    cam.setFechaNacimiento(rs.getString("fechaNacimiento"));
                    cam.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    if(!"".equals(rs.getString("fotoJugador")) && rs.getString("fotoJugador")!=null){
                        cam.setFotoJugador(rs.getString("fotoJugador"));
                        //cam.setFotoJugador(Util.DEFAULTPHOTO);
                    }else{
                        cam.setFotoJugador(Util.DEFAULTPHOTO);
                    }
                    

                    lista.add(cam);
                
                }
            }catch(SQLException e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
        public List<Jugador> listarJugadoresEquipo(Equipo camp) throws Exception{
            List<Jugador> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\" FROM public.jugador WHERE \"idEquipoJugador\" = ?");
                st.setInt(1, camp.getIdEquipo());
                rs = st.executeQuery();
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
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
        public byte[] traerImageByte(String productId) throws Exception {
            ResultSet rs;
		
		
		byte[] productImage = null;
 
		try {
                    this.Conectar();
		PreparedStatement st = this.getCn().prepareCall("SELECT \"fotoJugador\" FROM public.jugador WHERE \"idJugador\"=?");
		st.setString(1, productId);
		rs = st.executeQuery();
 
		while (rs.next()) {
			productImage = rs.getBytes("fotoJugador");
		}
		} catch (SQLException e) {
			System.out.println(e);
			System.exit(0);
		}finally{
                this.Cerrar();
            }
		return productImage;
	}
    
    public Jugador leerID(int idJugador) throws Exception{
        Jugador usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idJugador, nombreJugador,idUsuario FROM jugador WHERE idJugador = ?");
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idJugador\", \"nombreJugador\",\"idUsuario\" FROM public.jugador WHERE \"idJugador\" = ?");
                st.setInt(1, idJugador);
                
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Jugador();
                    usus.setIdJugador(rs.getInt("idJugador"));
                    usus.setNombreJugador(rs.getString("nombreJugador"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                                       
                }
                
            }catch(SQLException e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Jugador cam) throws Exception{
        
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE jugador SET nombreJugador = ? WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.jugador SET \"nombreJugador\" = ?,\"fotoJugador\" = ? WHERE \"idJugador\" = ?");
            st.setString(1, cam.getNombreJugador());  
            st.setString(2, cam.getFotoJugador());
            st.setInt(3, cam.getIdJugador());          
            st.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Jugador cam) throws Exception{
        
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM jugador  WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.jugador  WHERE \"idJugador\" = ?");
            st.setInt(1, cam.getIdJugador());          
            st.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }    
}
