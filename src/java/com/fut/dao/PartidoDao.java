/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Partido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class PartidoDao extends Dao{
    public boolean registrar(Partido cam) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.partido (\"idEquipoA\",\"idEquipoB\",\"idGrupo\",\"golA\",\"golB\",\"idUsuario\") values(?,?,?,?,?,?)");
            st.setInt(1, cam.getIdEquipoA());
            st.setInt(2, cam.getIdEquipoB());
            st.setInt(3, cam.getIdGrupo());
            st.setInt(4, cam.getGolA());
            st.setInt(5, cam.getGolB());
            st.setInt(6, cam.getIdUsuario());
            
          
            
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
    

        
    public List<Partido> listar(Grupo camp) throws Exception{
            List<Partido> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\",\"idGrupo\" FROM public.partido WHERE \"idGrupo\" = ?");
                st.setInt(1, camp.getIdGrupo());
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
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public List<Partido> listarPartidosEquipo(Grupo camp, int idEquipo) throws Exception{
            List<Partido> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
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
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Partido leerID(int idPartido) throws Exception{
        Partido usus = null;
        ResultSet rs;
            try{
                this.Conectar();
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
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        
    
    public void modificar(Partido cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE partido SET idEquipoA = ?,idEquipoB = ?,golA = ?,golB = ?,idGrupo = ? WHERE idPartido = ?");
            st.setInt(1, cam.getIdEquipoA());  
            st.setInt(2, cam.getIdEquipoB());
            st.setInt(3, cam.getGolA());  
            st.setInt(4, cam.getGolB());  
            st.setInt(5, cam.getIdGrupo());
            st.setInt(3, cam.getIdPartido());  
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Partido cam) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM partido  WHERE idPartido = ?");
            st.setInt(1, cam.getIdPartido());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
}
