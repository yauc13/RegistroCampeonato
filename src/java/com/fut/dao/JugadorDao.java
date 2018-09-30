/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Jugador;
import com.fut.util.QuerySqlCampeonato;
import com.fut.util.Util;
import java.sql.Date;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;

/**
 *
 * @author Yeison
 */
public class JugadorDao extends Dao {
    public boolean registrar(Jugador cam){
        boolean reg = false;
        Date birth = null;
        if(cam.getBirthday()!= null){
           birth = new java.sql.Date((cam.getBirthday()).getTime());
        }
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO jugador (nombreJugador,fechaNacimiento,golJugador,idEquipoJugador,idUsuario) values(?,?,?,?,?)");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.jugador (\"nombreJugador\",birthday,\"golJugador\",\"idEquipoJugador\",\"idUsuario\",\"fotoJugador\") values(?,?,?,?,?,?)");
            st.setString(1, cam.getNombreJugador());
            //st.setDate(2, (Date) cam.getBirthday());
            st.setDate(2, birth);
            st.setInt(3, cam.getGolJugador());
            st.setInt(4, cam.getIdEquipoJugador());
            st.setInt(5, cam.getIdUsuario());
            st.setString(6, cam.getFotoJugador());
            
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        this.Cerrar();
        }
        return reg;
    }
    

        
    public List<Jugador> listar(Equipo camp){
            List<Jugador> lista = null;
            ResultSet rs;
            
            try{
                this.Conectar();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\", \"fotoJugador\" , birthday FROM public.jugador WHERE \"idEquipoJugador\" = ? ORDER BY \"idJugador\"");
                st.setInt(1, camp.getIdEquipo());
                rs = st.executeQuery();
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
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public List<Jugador> listarGoleadores(Campeonato camp){
            List<Jugador> lista = null;
            ResultSet rs;
            
            try{
                this.Conectar();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall(QuerySqlCampeonato.SELECT_GOLEADORES);
                st.setInt(1, camp.getIdCampeonato());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Jugador cam = new Jugador();
                    cam.setIdJugador(rs.getInt("idJugador"));
                    cam.setNombreJugador(rs.getString("nombreJugador"));                  
                    cam.setIdEquipoJugador(rs.getInt("idEquipoJugador"));
                    cam.setNumGol(rs.getInt("count"));
 
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
        public List<Jugador> listarJugadoresEquipo(Equipo camp) {
            List<Jugador> lista = null;
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
                System.err.println(e);
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
        public byte[] traerImageByte(String productId) {
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
    
    public Jugador leerID(int idJugador){
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
                System.err.println(e);
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Jugador cam){
        Date birth = null;
        if(cam.getBirthday()!= null){
           birth = new java.sql.Date((cam.getBirthday()).getTime());
        }
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE jugador SET nombreJugador = ? WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.jugador SET \"nombreJugador\" = ?,birthday=?, \"fotoJugador\" = ? WHERE \"idJugador\" = ?");
            st.setString(1, cam.getNombreJugador());
            st.setDate(2, birth);
            st.setString(3, cam.getFotoJugador());
            st.setInt(4, cam.getIdJugador());          
            st.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Jugador cam) {
        
        try{
            this.Conectar();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM jugador  WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.jugador  WHERE \"idJugador\" = ?");
            st.setInt(1, cam.getIdJugador());          
            st.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.Cerrar();
        }
    }    
}
