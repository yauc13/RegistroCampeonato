/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.PagoPlanilla;
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
 * @author Yeison
 */
public class EquipoDao {
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    
    public boolean registrarEquipo(Equipo equ) {
        boolean reg = false;
        try{
            cx = cx = DaoUtil.ConectionDriveDB();           
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_TEAM);
            stmt.setString(1, equ.getNombreEquipo());
            stmt.setInt(2, equ.getPgEquipo());
            stmt.setInt(3, equ.getPeEquipo());
            stmt.setInt(4, equ.getPpEquipo());
            stmt.setInt(5, equ.getGfEquipo());
            stmt.setInt(6, equ.getGcEquipo());
            stmt.setInt(7, equ.getIdGrupoEquipo());
            stmt.setInt(8, equ.getIdUsuario());
            
            stmt.executeUpdate();
            reg = true;
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
    public void modificarEquipo(Equipo cam){
        
        try{
            cx = DaoUtil.ConectionDriveDB();           
            stmt = cx.prepareStatement(SqlAdminFutSal.UPDATE_TEAM);
            stmt.setString(1, cam.getNombreEquipo());                      
            stmt.setInt(2, cam.getIdEquipo());          
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
            DaoUtil.closeConection(cx, stmt, rs);
        }
    }
        
    public List<Equipo> listarEquiposPorGrupo(int idGroup){
            List<Equipo> lista = null;                        
            try{
                cx = cx = DaoUtil.ConectionDriveDB();                
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_TEAM_BY_GROUP);
                stmt.setInt(1, idGroup);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
        public List<Equipo> listarEquipoB(Grupo camp, int idEquipoA) {
            List<Equipo> lista = null;                        
            try{
                cx = DaoUtil.ConectionDriveDB();               
               stmt = cx.prepareCall("SELECT \"idEquipo\", \"nombreEquipo\",\"pgEquipo\",\"peEquipo\",\"ppEquipo\",\"gfEquipo\",\"gcEquipo\",\"idGrupoEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idGrupoEquipo\" = ? AND \"idEquipo\" != ?");
                stmt.setInt(1, camp.getIdGrupo());
                stmt.setInt(2, idEquipoA);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
        
        
     public List<Equipo> listarEquiposClasificados(int idChampionShip){
            List<Equipo> lista = null;                        
            try{
                cx = DaoUtil.ConectionDriveDB();                
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_TEAM_CLASSIFIED);
                stmt.setInt(1, idChampionShip);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
     
    public List<Equipo> listTeamByPlayOffA(int idPlayOff){
            List<Equipo> lista = null;            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_TEAM_A_BY_R_PLAY_OFF);
                stmt.setInt(1, idPlayOff);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public Equipo leerID(int idEquipo) {
        Equipo usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareStatement("SELECT idEquipo, nombreEquipo,idUsuario FROM equipo WHERE idEquipo = ?");
                stmt = cx.prepareStatement("SELECT \"idEquipo\", \"nombreEquipo\",\"idUsuario\" FROM public.equipo WHERE \"idEquipo\" = ?");
                stmt.setInt(1, idEquipo);
                
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Equipo();
                    usus.setIdEquipo(rs.getInt("idEquipo"));
                    usus.setNombreEquipo(rs.getString("nombreEquipo"));
                    usus.setIdUsuario(rs.getInt("idUsuario"));                   
                }
                
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        
    
    
    
    public void eliminar(Equipo cam){
        
        try{
            cx = DaoUtil.ConectionDriveDB();            
            stmt = cx.prepareStatement("DELETE FROM public.equipo  WHERE \"idEquipo\" = ?");
            stmt.setInt(1, cam.getIdEquipo());          
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }
    
    public List<Equipo> listarEquipoBPartido(int idGrupo, int idEquipoA) {
        List<Equipo> lista = null;
        
        String array = listarIdRivalesEnGrupo(idGrupo, idEquipoA);
        try {
            cx = DaoUtil.ConectionDriveDB();

            String query = SqlAdminFutSal.SELECT_TEAM_NOT_PLAY.replaceAll("array", array);
            stmt = cx.prepareCall(query);
            stmt.setInt(1, idGrupo);

            rs = stmt.executeQuery();
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
            DaoUtil.closeConection(cx, stmt, rs);
        }

        return lista;
    }
    
    private String listarIdRivalesEnGrupo(int idGrupo, int idEquipo) {
        List<Integer> lista;
        String cadena = null;
        

        try {
            cx = DaoUtil.ConectionDriveDB();
            lista = new ArrayList();
            lista.add(idEquipo);

            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_IDTEAM_A_PLAY);
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idEquipo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("idEquipoA"));
            }

            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_IDTEAM_B_PLAY);
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idEquipo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("idEquipoB"));
            }
            rs.close();
            stmt.close();

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
            DaoUtil.closeConection(cx, stmt, rs);
        }

        return cadena;
    }
    
    //no se necesita?
    public void listarIdRivalB(int idGrupo, int idEquipoA, List<Integer> lista) {

        

        try {
            cx = DaoUtil.ConectionDriveDB() ;

            stmt = cx.prepareCall(SqlAdminFutSal.SELECT_TEAM_NOT_PLAY);
            stmt.setInt(1, idGrupo);
            stmt.setInt(2, idEquipoA);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getInt("idEquipoB"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DaoUtil.closeConection(cx, stmt, rs);
        }
    }
    
    
    public List<Equipo> listarEquiposTotalPago(int idCampeonato){
            List<Equipo> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                stmt = cx.prepareStatement(SqlAdminFutSal.SELECT_LIST_TEAM_ALL_PAY);
                stmt.setInt(1, idCampeonato);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    } 
    
        public boolean insertPayTeam(PagoPlanilla pago){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_TEAM_PAY);
            stmt.setInt(1, pago.getIdCampeonato());
            stmt.setInt(2, pago.getIdEquipo());
            stmt.setDate(3, new java.sql.Date((pago.getFechaPago()).getTime()));
            stmt.setInt(4, pago.getValorPago());
            int resp = stmt.executeUpdate();
            if(resp>0){
            reg = true;
            }
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
        
    public List<PagoPlanilla> listarPagosPorEquipo(int idEquipo){
            List<PagoPlanilla> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cx.prepareCall("SELECT idEquipo, nombreEquipo,pgEquipo,peEquipo,ppEquipo,gfEquipo,gcEquipo,idGrupoEquipo,idUsuario FROM equipo WHERE idGrupoEquipo = ?");
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_LIST_PAY_BY_TEAM);
                stmt.setInt(1, idEquipo);
                rs = stmt.executeQuery();
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
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    } 
    
    
    
}
