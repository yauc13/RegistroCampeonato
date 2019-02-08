/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.PagoPlanilla;
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
    

        
    public List<Equipo> listar(int idGroup){
            List<Equipo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idEquipo\", \"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idGrupoEquipo\" = ? ORDER BY \"nombreEquipo\"");
                st.setInt(1, idGroup);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
                    cam.setIdUsuario(rs.getInt("idUsuario"));
                    cam.setIdGrupoEquipo(rs.getInt("idGrupoEquipo"));
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
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_TEAM_CLASSIFIED);
                st.setInt(1, idChampionShip);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
                    Grupo gru = new Grupo();
                    gru.setNombreGrupo(rs.getString("nombreGrupo"));
                    cam.setGrupo(gru);
                    lista.add(cam);                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
     
    public List<Equipo> listTeamByPlayOffA(int idPlayOff){
            List<Equipo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_TEAM_A_BY_R_PLAY_OFF);
                st.setInt(1, idPlayOff);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));                   
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
    
    public List<Equipo> listarEquipoBPartido(int idGrupo, int idEquipoA) {
        List<Equipo> lista = null;
        ResultSet rs;
        String array = listarIdRivalesEnGrupo(idGrupo, idEquipoA);
        try {
            this.ConectionDataBase();

            String query = SqlAdminFutSal.SELECT_TEAM_NOT_PLAY.replaceAll("array", array);
            PreparedStatement st = this.getCn().prepareCall(query);
            st.setInt(1, idGrupo);

            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Equipo cam = new Equipo();
                cam.setIdEquipo(rs.getInt("idEquipo"));
                cam.setNombreEquipo(rs.getString("nombreEquipo"));
                
                lista.add(cam);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }

        return lista;
    }
    
    private String listarIdRivalesEnGrupo(int idGrupo, int idEquipo) {
        List<Integer> lista;
        String cadena = null;
        ResultSet rs;

        try {
            this.ConectionDataBase();
            lista = new ArrayList();
            lista.add(idEquipo);

            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_IDTEAM_A_PLAY);
            st.setInt(1, idGrupo);
            st.setInt(2, idEquipo);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("idEquipoA"));
            }

            st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_IDTEAM_B_PLAY);
            st.setInt(1, idGrupo);
            st.setInt(2, idEquipo);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("idEquipoB"));
            }
            rs.close();
            st.close();

            StringBuilder obj;
            obj = new StringBuilder();
            for (int i = 0; i < lista.size(); i++) {
                obj.append(lista.get(i));
                if (i != lista.size() - 1) {
                    obj.append(",");
                }
            }
            cadena = obj.toString();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }

        return cadena;
    }
    
    //no se necesita?
    public void listarIdRivalB(int idGrupo, int idEquipoA, List<Integer> lista) {

        ResultSet rs;

        try {
            this.ConectionDataBase();

            PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_TEAM_NOT_PLAY);
            st.setInt(1, idGrupo);
            st.setInt(2, idEquipoA);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getInt("idEquipoB"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            this.CloseConection();
        }
    }
    
    
    public List<Equipo> listarEquiposTotalPago(int idCampeonato){
            List<Equipo> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_LIST_TEAM_ALL_PAY);
                st.setInt(1, idCampeonato);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo cam = new Equipo();
                    cam.setIdEquipo(rs.getInt("idEquipo"));
                    cam.setNombreEquipo(rs.getString("nombreEquipo"));
                    cam.setTotalPagoEquipo(rs.getInt("totalPago"));
                    
                    lista.add(cam);                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    } 
    
        public boolean insertPayTeam(PagoPlanilla pago){
        boolean reg = false;
        try{
            this.ConectionDataBase();
            
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_TEAM_PAY);
            st.setInt(1, pago.getIdCampeonato());
            st.setInt(2, pago.getIdEquipo());
            st.setDate(3, new java.sql.Date((pago.getFechaPago()).getTime()));
            st.setInt(4, pago.getValorPago());
            int resp = st.executeUpdate();
            if(resp>0){
            reg = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
        
    public List<PagoPlanilla> listarPagosPorEquipo(int idEquipo){
            List<PagoPlanilla> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_LIST_PAY_BY_TEAM);
                st.setInt(1, idEquipo);
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    PagoPlanilla cam = new PagoPlanilla();
                    cam.setIdPagoEquipo(rs.getInt("idPagoEquipo"));
                    
                    cam.setFechaPago(rs.getDate("fechaPago"));
                    cam.setValorPago(rs.getInt("valorPago"));
                    
                    lista.add(cam);                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    } 
    
    
    
}
