/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Campeonato;
import com.fut.model.Grupo;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class GrupoDao extends Dao {
    public boolean insertGroup(Grupo group){
        boolean reg = false;
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO grupo (nombreGrupo,idCampeonato,idUsuario) values(?,?,?)");
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_GRUP);
            st.setString(1, group.getNombreGrupo());
            st.setInt(2, group.getIdCampeonato());
            st.setInt(3, group.getIdUsuario());
            
            int exRes = st.executeUpdate();
            if(exRes>0){
            reg = true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
        
    public List<Grupo> listar(Campeonato camp){
            List<Grupo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idGrupo, nombreGrupo,idUsuario FROM grupo WHERE idCampeonato = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idGrupo\", \"nombreGrupo\",\"idUsuario\" FROM public.grupo WHERE \"idCampeonato\" = ?");
                st.setInt(1, camp.getIdCampeonato());
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Grupo cam = new Grupo();
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    cam.setNombreGrupo(rs.getString("nombreGrupo"));
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
    
    public Grupo leerID(int idGrupo) {
        Grupo usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idGrupo, nombreGrupo, idUsuario FROM grupo WHERE idGrupo = ?");
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idGrupo\", \"nombreGrupo\", \"idUsuario\" FROM public.grupo WHERE \"idGrupo\" = ?");
                st.setInt(1, idGrupo);
                
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Grupo();
                    usus.setIdGrupo(rs.getInt("idGrupo"));
                    usus.setNombreGrupo(rs.getString("nombreGrupo"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                                       
                }
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }   
            return usus;
    }
    
        
    
    public boolean updateGroup(Grupo cam){
        boolean resp=false;
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("UPDATE grupo SET nombreGrupo = ? WHERE idGrupo = ?");
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.UPDATE_GRUP);
            st.setString(1, cam.getNombreGrupo());                      
            st.setInt(2, cam.getIdGrupo());          
            
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
    
    public void deleteGroup(Grupo grupo){
        
        try{
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("DELETE FROM grupo  WHERE idGrupo = ?");
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.grupo  WHERE \"idGrupo\" = ?");
            st.setInt(1, grupo.getIdGrupo());          
            st.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
}
