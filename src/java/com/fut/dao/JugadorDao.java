/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Equipo;
import com.fut.model.Jugador;
import com.fut.util.SqlAdminFutSal;
import com.fut.util.Util;
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
public class JugadorDao extends Dao {
    public boolean registrar(Jugador cam){
        boolean reg = false;
        Date birth = null;
        if(cam.getBirthday()!= null){
           birth = new java.sql.Date((cam.getBirthday()).getTime());
        }
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO jugador (nombreJugador,fechaNacimiento,golJugador,idEquipoJugador,idUsuario) values(?,?,?,?,?)");
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_PLAYER);
            st.setString(1, cam.getNombreJugador());
            st.setDate(2, birth);
            st.setInt(3, cam.getGolJugador());
            st.setInt(4, cam.getIdEquipoJugador());
            st.setInt(5, cam.getIdUsuario());
            st.setString(6, cam.getFotoJugador());
                       
            int resul =st.executeUpdate();
            if(resul>0){
            reg = true;
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
    

        
    public List<Jugador> listar(Equipo camp){
            List<Jugador> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
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
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public List<Jugador> listarGoleadores(Campeonato camp){
            List<Jugador> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_GOLEADORES);
                st.setInt(1, camp.getIdCampeonato());
                rs = st.executeQuery();
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
                this.CloseConection();
            }
        
        return lista;   
    }
    
        public List<Jugador> listarJugadoresEquipo(int idEquipo) {
            List<Jugador> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idJugador,nombreJugador,fechaNacimiento,idEquipoJugador,idUsuario FROM jugador WHERE idEquipoJugador = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idJugador\",\"nombreJugador\",\"fechaNacimiento\",\"idEquipoJugador\",\"idUsuario\" FROM public.jugador WHERE \"idEquipoJugador\" = ?");
                st.setInt(1, idEquipo);
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
                this.CloseConection();
            }
        
        return lista;   
    }
        public byte[] traerImageByte(String productId) {
            ResultSet rs;
		
		
		byte[] productImage = null;
 
		try {
                    this.ConectionDataBase();
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
                this.CloseConection();
            }
		return productImage;
	}
    
    public Jugador leerID(int idJugador){
        Jugador usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
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
                this.CloseConection();
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
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE jugador SET nombreJugador = ? WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.UPDATE_PLAYER);
            st.setString(1, cam.getNombreJugador());
            st.setDate(2, birth);
            st.setString(3, cam.getFotoJugador());
            st.setInt(4, cam.getIdJugador());          
            int resul =st.executeUpdate();
            if(resul>0){
            resp = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
    
    public void eliminar(Jugador cam) {
        
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM jugador  WHERE idJugador = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.jugador  WHERE \"idJugador\" = ?");
            st.setInt(1, cam.getIdJugador());          
            st.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }    
}
