/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Yeison
 */
public class EquipoDao extends Dao {
    public boolean registrar(Equipo cam) throws Exception{
        boolean reg = false;
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO equipo (nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario) values(?,?,?,?,?,?,?,?)");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.equipo (\"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\") values(?,?,?,?,?,?,?,?)");
            st.setString(1, cam.getNombreEquipo());
            st.setInt(2, cam.getPgEquipo());
            st.setInt(3, cam.getPeEquipo());
            st.setInt(4, cam.getPpEquipo());
            st.setInt(5, cam.getGfEquipo());
            st.setInt(6, cam.getGcEquipo());
            st.setInt(7, cam.getIdGrupoEquipo());
            st.setInt(8, cam.getIdUsuario());
            
            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            throw e;
        }finally{
        this.CloseConection();
        }
        return reg;
    }
    

        
    public List<Equipo> listar(Grupo camp){
            List<Equipo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idEquipo\", \"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idGrupoEquipo\" = ? ORDER BY \"nombreEquipo\"");
                st.setInt(1, camp.getIdGrupo());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
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
    
        public List<Equipo> listarEquipoB(Grupo camp, int idEquipoA) throws Exception{
            List<Equipo> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idEquipo\", \"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idGrupoEquipo\" = ? AND \"idEquipo\" != ?");
                st.setInt(1, camp.getIdGrupo());
                st.setInt(2, idEquipoA);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    
                    
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                throw e;
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
        
        
     public List<Equipo> listarEquiposClasificados(int idChampionShip){
            List<Equipo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idEquipo\", \"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idGrupoEquipo\" = ? ORDER BY \"nombreEquipo\"");
                st.setInt(1, idChampionShip);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
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
    
    public Equipo leerID(int idEquipo) throws Exception{
        Equipo usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idEquipo, nombreEquipo,idUsuario FROM equipo WHERE idEquipo = ?");
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idEquipo\", \"nombreEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idEquipo\" = ?");
                st.setInt(1, idEquipo);
                
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Equipo();
                    usus.setIdEquipo(rs.getInt("idEquipo"));
                    usus.setNombreEquipo(rs.getString("nombreEquipo"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));                   
                }
                
            }catch(SQLException e){
                throw e;
            }finally{
                this.CloseConection();
            }   
            return usus;
    }
    
        
    
    public void modificar(Equipo cam) throws Exception{
        
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE equipo SET nombreEquipo = ? WHERE idEquipo = ?");
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.equipo SET \"nombreEquipo\" = ? WHERE \"idEquipo\" = ?");
            st.setString(1, cam.getNombreEquipo());                      
            st.setInt(2, cam.getIdEquipo());          
            st.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }
    
    public void eliminar(Equipo cam) throws Exception{
        
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM equipo  WHERE idEquipo = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.equipo  WHERE \"idEquipo\" = ?");
            st.setInt(1, cam.getIdEquipo());          
            st.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }
}
