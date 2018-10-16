/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Jornada;
import com.fut.model.Partido;
import com.fut.model.TablaEquipos;
import com.fut.util.SqlAdminFutSal;
import com.fut.util.Util;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeison
 */
public class PartidoDao extends Dao{
    public boolean registrar(Partido cam) {
        boolean reg = false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.INSERT_PARTIDO);
            st.setInt(1, cam.getIdEquipoA());
            st.setInt(2, cam.getIdEquipoB());
            st.setInt(3, cam.getIdGrupo());
            st.setInt(4, cam.getIdUsuario());
            st.setString(5, "Por Jugar");

            st.executeUpdate();
            reg = true;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        this.CloseConection();
        }
        return reg;
    }
    
   public List<Partido> listarJoin(int idGrupo){
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_GRUPO);
                st.setInt(1, idGrupo);
                rs = st.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido cam = new Partido();
                    //cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdPartido(rs.getInt(1));
                    cam.setIdEquipoA(rs.getInt(2));
                    cam.setIdEquipoB(rs.getInt(3));
                    cam.setGolA(rs.getInt(4));
                    cam.setGolB(rs.getInt(5));
                    cam.setIdGrupo(rs.getInt(6));
                    cam.setEstadoPartido(rs.getString(9));
                    cam.setIdJornada(rs.getInt(10));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt(2));
                    equipoA.setNombreEquipo(rs.getString(7));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt(3));
                    equipoB.setNombreEquipo(rs.getString(8));                    
                    cam.setEquipoB(equipoB);
                    Grupo grupo = new Grupo();
                    grupo.setIdCampeonato(rs.getInt(6));
                    cam.setGrupo(grupo);
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
       
        return lista;   
    }
   
   public List<Partido> listarPartidosGrupoJor(int idGrupo){
       //lista los partidos seleccionando el grupo, para agregar el partido a una fecha. si el partido tiene idjornada null
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_GRUPO_JOR);
                st.setInt(1, idGrupo);
                rs = st.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido cam = new Partido();
                    //cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdPartido(rs.getInt(1));
                    cam.setIdEquipoA(rs.getInt(2));
                    cam.setIdEquipoB(rs.getInt(3));
                    cam.setGolA(rs.getInt(4));
                    cam.setGolB(rs.getInt(5));
                    cam.setIdGrupo(rs.getInt(6));
                    cam.setEstadoPartido(rs.getString(9));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt(2));
                    equipoA.setNombreEquipo(rs.getString(7));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt(3));
                    equipoB.setNombreEquipo(rs.getString(8));                    
                    cam.setEquipoB(equipoB);
                    Grupo grupo = new Grupo();
                    grupo.setIdCampeonato(rs.getInt(6));
                    cam.setGrupo(grupo);
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
       
        return lista;   
    }
   
   
   public List<Partido> listarPartidosJornada(Jornada jor){
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_JORNADA);
                st.setInt(1, jor.getIdJornada());
                rs = st.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido cam = new Partido();
                    //cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdPartido(rs.getInt(1));
                    cam.setIdEquipoA(rs.getInt(2));
                    cam.setIdEquipoB(rs.getInt(3));
                    cam.setGolA(rs.getInt(4));
                    cam.setGolB(rs.getInt(5));
                    cam.setIdGrupo(rs.getInt(6));
                    cam.setEstadoPartido(rs.getString(9));
                    cam.setIdJornada(rs.getInt(10));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt(2));
                    equipoA.setNombreEquipo(rs.getString(7));                   
                    cam.setEquipoA(equipoA);
                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt(3));
                    equipoB.setNombreEquipo(rs.getString(8));                    
                    cam.setEquipoB(equipoB);
                    
                    Grupo grupo = new Grupo();
                    grupo.setIdCampeonato(rs.getInt(6));
                    cam.setGrupo(grupo);
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        return lista;   
    }
        
    
    
    public List<Partido> listarPartidosEquipo(Grupo camp, int idEquipo){
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\",\"idGrupo\" FROM public.partido WHERE \"idGrupo\" = ? AND \"idEquipoA\"=? OR \"idEquipoB\"=?");
                st.setInt(1, camp.getIdGrupo());
                st.setInt(2, idEquipo);
                st.setInt(3, idEquipo);
                
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Partido cam = new Partido();
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    cam.setGolA(rs.getInt("golA"));
                    cam.setGolB(rs.getInt("golB"));
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    EquipoDao equipoDao = new EquipoDao();
                    Equipo equipo = equipoDao.leerID(rs.getInt("idEquipoA"));
                    cam.setEquipoA(equipo);
                    equipo = equipoDao.leerID(rs.getInt("idEquipoB"));
                    cam.setEquipoB(equipo);
                    GrupoDao grupoDao = new GrupoDao();
                    Grupo grupo = grupoDao.leerID(rs.getInt("idGrupo"));
                    cam.setGrupo(grupo);
                    
                    lista.add(cam);
                
                }
            }catch(Exception e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public Partido leerID(int idPartido) {
        Partido usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\",\"idGrupo\" FROM public.partido WHERE \"idPartido\" = ?");
                st.setInt(1, idPartido);
               
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Partido();
                    EquipoDao equipoDao = new EquipoDao();
                    
                    usus.setIdPartido(rs.getInt("idPartido"));
                    usus.setIdEquipoA(rs.getInt("idEquipoA"));
                    usus.setIdEquipoB(rs.getInt("idEquipoB"));
                    usus.setGolA(rs.getInt("golA"));
                    usus.setGolB(rs.getInt("golB"));
                    usus.setIdGrupo(rs.getInt("idGrupo"));  
                    Equipo equipo = equipoDao.leerID(rs.getInt("idEquipoA"));
                    usus.setEquipoA(equipo);
                    equipo = equipoDao.leerID(rs.getInt("idEquipoB"));
                    usus.setEquipoB(equipo);
                    GrupoDao grupoDao = new GrupoDao();
                    Grupo grupo = grupoDao.leerID(rs.getInt("idGrupo"));
                    usus.setGrupo(grupo);
                    
                }
                
                
                
            }catch(Exception e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }   
            return usus;
    }
    
    public List<TablaEquipos> listarTablaPosiciones(Grupo grup) {
        List<TablaEquipos> lista = null;
            ResultSet rs;
        List<Equipo> listEquipo = null;
        EquipoDao equipoDao = new EquipoDao();
        
        try {
            listEquipo = equipoDao.listar(grup); //se llena la lista de los equipos del grupo
        } catch (Exception ex) {
            Logger.getLogger(PartidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            lista = new ArrayList();
            try{
                this.ConectionDataBase();
                //se recorre la lista de equipos del grupo
                for(Equipo e: listEquipo){
                PreparedStatement st = this.getCn().prepareCall(SqlAdminFutSal.LIST_POSITION_TEAM);
                //st.setInt(1, grup.getIdGrupo());
                st.setInt(1, e.getIdEquipo());
                st.setInt(2, e.getIdEquipo());
                
                rs = st.executeQuery();
                
                TablaEquipos tablaEqui= new TablaEquipos();
                System.out.println(e.getNombreEquipo());
                while(rs.next()){
                    Partido cam = new Partido();
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    cam.setGolA(rs.getInt("golA"));
                    cam.setGolB(rs.getInt("golB"));
                    //cam.setIdGrupo(rs.getInt("idGrupo"));
                    TablaEquipos tab = new TablaEquipos();
                    
                    int [] vPuntosPart = tab.calcularPuntosPartido(cam, e.getIdEquipo());
                   
                   
                   tablaEqui.setPuntos(tablaEqui.getPuntos()+vPuntosPart[0]);
                   tablaEqui.setDF(tablaEqui.getDF()+vPuntosPart[1]);
                   tablaEqui.setGF(tablaEqui.getGF()+vPuntosPart[2]);
                   tablaEqui.setGC(tablaEqui.getGC()+vPuntosPart[3]);
                   tablaEqui.setPJ(tablaEqui.getPJ()+vPuntosPart[4]);
                   tablaEqui.setPG(tablaEqui.getPG()+vPuntosPart[5]);
                   tablaEqui.setPE(tablaEqui.getPE()+vPuntosPart[6]);
                   tablaEqui.setPP(tablaEqui.getPP()+vPuntosPart[7]); 
                   System.out.println("puntos partido: "+vPuntosPart[0]);
                   System.out.println(tablaEqui.getNombre()+lista.size());
                }
                
                //tablaEqui.setProm(tablaEqui.getPuntos()/tablaEqui.getPJ());
                tablaEqui.setNombre(e.getNombreEquipo());
                lista.add(tablaEqui);
                
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
        TablaEquipos tablaE= new TablaEquipos();
        tablaE.ordenarTabla(lista);
        return lista;     
    }
    
        
    
    public void modificar(Partido cam){
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE partido SET idEquipoA = ?,idEquipoB = ?,golA = ?,golB = ?,idGrupo = ? WHERE idPartido = ?");
            st.setInt(1, cam.getIdEquipoA());  
            st.setInt(2, cam.getIdEquipoB());
            st.setInt(3, cam.getGolA());  
            st.setInt(4, cam.getGolB());  
            st.setInt(5, cam.getIdGrupo());
            st.setInt(6, cam.getIdPartido());  
            st.executeUpdate();
            
                        
            List<Integer> idGolEA;
            idGolEA = new ArrayList<>();             
            for(int i=0; i<cam.getGolEA().length;i++){
                idGolEA.add(cam.getGolEA()[i].getIdGol());
            }
            st.setArray(7, (Array) idGolEA);
            
            List<Integer> idGolEB;
            idGolEB = new ArrayList<>();             
            for(int i=0; i<cam.getGolEB().length;i++){
                idGolEB.add(cam.getGolEB()[i].getIdGol());
            }
            st.setArray(8, (Array) idGolEB);

            
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
    
    public void agregarPartidoJornada(int idJornada, int idPartido) {
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.ADD_MATCH_TO_JORNADA);
            st.setInt(1, idJornada);                          
            st.setInt(2, idPartido);  
            st.executeUpdate();

        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
    
        public void finalizarPartido(Partido cam){
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.FINISH_MATCH);           
            st.setInt(1, cam.getGolA());  
            st.setInt(2, cam.getGolB()); 
            st.setString(3, "Finalizado"); 
            st.setInt(4, cam.getIdPartido());
             
            st.executeUpdate();
            
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
        
    public boolean quitarPartidoJornada(Partido par){
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.REMOVE_MATCH_FIXTURE);
                                  
            st.setInt(1, par.getIdPartido());  
            st.executeUpdate();
            resp= true;
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
    
    public void eliminar(Partido cam) {
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM partido  WHERE idPartido = ?");
            st.setInt(1, cam.getIdPartido());          
            st.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
}
