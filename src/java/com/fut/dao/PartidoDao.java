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
import com.fut.model.PlayOff;
import com.fut.model.TablaEquipos;
import com.fut.util.Cons;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class PartidoDao extends Dao{
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    
    public boolean registrarPartido(Partido par) {
        boolean reg = false;
        String query;
        String repla = "";
        int idRepla = 0;
        if(par.getIdGrupo()!= 0){
            repla = "idGrupo";
            idRepla = par.getIdGrupo();
        }else if(par.getIdPlayOff()!=0){
            repla = "idPlayOff";
            idRepla = par.getIdPlayOff();
        }
        try{
            cx = DaoUtil.ConectionDriveDB();
            query = SqlAdminFutSal.INSERT_PARTIDO.replaceAll("remplazo", repla);
            
            stmt = cx.prepareStatement(query);
            stmt.setInt(1, par.getIdEquipoA());
            stmt.setInt(2, par.getIdEquipoB());
            stmt.setInt(3,  idRepla);
            stmt.setInt(4, par.getIdUsuario());
            stmt.setString(5, "Por Jugar");

            stmt.executeUpdate();
            reg = true;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
        public boolean registrarPartidoPlayOff(Partido par) {
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.INSERT_PARTIDO_PLAYOFF);
            stmt.setInt(1, par.getIdEquipoA());
            stmt.setInt(2, par.getIdEquipoB());
            stmt.setInt(3, par.getIdPlayOff());
            stmt.setInt(4, par.getIdUsuario());
            stmt.setString(5, Cons.STATE_MATCH_POR);

            int res = stmt.executeUpdate();
            if(res>0){
            reg = true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
    
   public List<Partido> listarJoin(int idGrupo){
            List<Partido> lista = null;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                stmt = cx.prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_GRUPO);
                stmt.setInt(1, idGrupo);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido cam = new Partido();                    
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    cam.setGolA(rs.getInt("golEqA"));
                    cam.setGolB(rs.getInt("golEqB"));
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    cam.setEstadoPartido(rs.getString("estadoPartido"));
                    cam.setIdJornada(rs.getInt("idJornada"));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoA.setNombreEquipo(rs.getString("nombreEquipoA"));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoB.setNombreEquipo(rs.getString("nombreEquipoB"));                    
                    cam.setEquipoB(equipoB);
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(rs.getInt("idGrupo"));
                    grupo.setNombreGrupo(rs.getString("nombreGrupo"));
                    cam.setGrupo(grupo);
                    
                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
       
        return lista;   
    }
   
    public List<Partido> listarPartidosPlayOff(int idPlay){
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_PLAYOFF);
                stmt.setInt(1, idPlay);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                                
                while(rs.next()){
                    Partido cam = new Partido();
                 
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));                  
                    cam.setIdPlayOff(rs.getInt("idPlayOff"));
                    cam.setIdArbitro(rs.getInt("idArbitro"));
                    cam.setEstadoPartido(rs.getString("estadoPartido"));
                   
                    cam.setGolA(rs.getInt("golEqA"));
                    cam.setGolB(rs.getInt("golEqB"));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoA.setNombreEquipo(rs.getString("nombreEquipoA"));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt("idEquipoB"));
                    equipoB.setNombreEquipo(rs.getString("nombreEquipoB"));                    
                    cam.setEquipoB(equipoB);

                    lista.add(cam);
                
                }
            }catch(SQLException e){
                System.err.println(e);
            }finally{
                this.CloseConection();
            }
       
        return lista;   
    }
    
    public List<Partido> listarPartidosPlayOffJor(int idPlay){
        //lista los partidos del play off que no esten en una jornada
            List<Partido> lista = null;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_PLAYOFF_JOR);
                stmt.setInt(1, idPlay);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                                
                while(rs.next()){
                    Partido cam = new Partido();
                 
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    cam.setIdPlayOff(rs.getInt("idPlayOff"));
                    cam.setEstadoPartido(rs.getString("estadoPartido"));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoA.setNombreEquipo(rs.getString("nombreEquipoA"));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt("idEquipoB"));
                    equipoB.setNombreEquipo(rs.getString("nombreEquipoB"));                    
                    cam.setEquipoB(equipoB);
                    

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
                PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_GRUPO_JOR);
                stmt.setInt(1, idGrupo);
                rs = stmt.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido cam = new Partido();
                    //cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    cam.setIdGrupo(rs.getInt("idGrupo"));
                    cam.setEstadoPartido(rs.getString("estadoPartido"));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoA.setNombreEquipo(rs.getString("nombreEquipoA"));                   
                    cam.setEquipoA(equipoA);                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt("idEquipoB"));
                    equipoB.setNombreEquipo(rs.getString("nombreEquipoB"));                    
                    cam.setEquipoB(equipoB);
                    Grupo grupo = new Grupo();
                    grupo.setIdCampeonato(rs.getInt("idGrupo"));
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
                PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.SELECT_PARTIDOS_JORNADA);
                stmt.setInt(1, jor.getIdJornada());
                rs = stmt.executeQuery();
                lista = new ArrayList();
                
                
                while(rs.next()){
                    Partido par = new Partido();
                    
                    par.setIdPartido(rs.getInt("idPartido"));
                    par.setIdEquipoA(rs.getInt("idEquipoA"));
                    par.setIdEquipoB(rs.getInt("idEquipoB"));
                    par.setFechaPartido(rs.getTimestamp("fechaPartido"));                    
                    par.setIdGrupo(rs.getInt("idGrupo"));
                    par.setEstadoPartido(rs.getString("estadoPartido"));
                    par.setIdJornada(rs.getInt("idJornada"));
                    par.setGolA(rs.getInt("golEqA"));
                    par.setGolB(rs.getInt("golEqB"));
                    
                    Equipo equipoA = new Equipo();
                    equipoA.setIdEquipo(rs.getInt("idEquipoA"));
                    equipoA.setNombreEquipo(rs.getString("nombreEquipoA"));                   
                    par.setEquipoA(equipoA);
                    
                    Equipo equipoB = new Equipo();
                    equipoB.setIdEquipo(rs.getInt("idEquipoB"));
                    equipoB.setNombreEquipo(rs.getString("nombreEquipoB"));                    
                    par.setEquipoB(equipoB);
                    
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(rs.getInt("idGrupo"));
                    grupo.setNombreGrupo(rs.getString("nombreGrupo"));
                    par.setGrupo(grupo);
                    
                    PlayOff play = new PlayOff();
                    play.setIdPlayOff(rs.getInt("idPlayOff"));
                    play.setNamePlayOff(rs.getString("namePlayOff"));
                    par.setPlayOff(play);
                    
                    lista.add(par);
                
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
                PreparedStatement stmt = this.getCn().prepareCall("SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"idGrupo\" FROM public.partido WHERE \"idGrupo\" = ? AND \"idEquipoA\"=? OR \"idEquipoB\"=?");
                stmt.setInt(1, camp.getIdGrupo());
                stmt.setInt(2, idEquipo);
                stmt.setInt(3, idEquipo);
                
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Partido cam = new Partido();
                    cam.setIdPartido(rs.getInt("idPartido"));
                    cam.setIdEquipoA(rs.getInt("idEquipoA"));
                    cam.setIdEquipoB(rs.getInt("idEquipoB"));
                    
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
                PreparedStatement stmt = this.getCn().prepareStatement("SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\",\"idGrupo\" FROM public.partido WHERE \"idPartido\" = ?");
                stmt.setInt(1, idPartido);
               
                rs = stmt.executeQuery();
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
    
    public List<TablaEquipos> listarTablaPosiciones(int idGrupo) {
        List<TablaEquipos> lista = null;
        ResultSet rs;
        lista = new ArrayList();
        try {
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.LIST_POSITION_TEAM_GROUP.replaceAll("\"idGru\"", idGrupo+""));
            

            rs = stmt.executeQuery();
            while (rs.next()) {
                TablaEquipos tablaEqui = new TablaEquipos();
                int puntos = ((rs.getInt("PGL")+rs.getInt("PGV"))*3)+((rs.getInt("PEL")+rs.getInt("PEV"))*1)+((rs.getInt("PPL")+rs.getInt("PPV"))*0);
                int DG = rs.getInt("GF")-rs.getInt("GC");
                
                tablaEqui.setNombre(rs.getString("nombreEquipo"));
                tablaEqui.setIdEquipo(rs.getInt("idEquipo"));
                tablaEqui.setPuntos(puntos);
                tablaEqui.setDF(DG);
                tablaEqui.setGF(rs.getInt("GF"));
                tablaEqui.setGC(rs.getInt("GC"));
                tablaEqui.setPJ(rs.getInt("TPL")+rs.getInt("TPV"));
                tablaEqui.setPG(rs.getInt("PGL")+rs.getInt("PGV"));
                tablaEqui.setPE(rs.getInt("PEL")+rs.getInt("PEV"));
                tablaEqui.setPP(rs.getInt("PPL")+rs.getInt("PPV"));
                
                lista.add(tablaEqui);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            this.CloseConection();
        }
        TablaEquipos tablaE = new TablaEquipos();
        tablaE.ordenarTabla(lista);
        return lista;
    }
    
    public List<TablaEquipos> listarTablaPosicionesJava(Grupo grup) {
        List<TablaEquipos> lista = null;
            ResultSet rs;
        List<Equipo> listEquipo = null;
        EquipoDao equipoDao = new EquipoDao();
            listEquipo = equipoDao.listarEquiposPorGrupo(grup.getIdGrupo()); //se llena la lista de los equipos del grupo
            lista = new ArrayList();
            try{
                this.ConectionDataBase();
                //se recorre la lista de equipos del grupo
                for(Equipo e: listEquipo){
                PreparedStatement stmt = this.getCn().prepareCall(SqlAdminFutSal.LIST_POSITION_TEAM);
                //stmt.setInt(1, grup.getIdGrupo());
                stmt.setInt(1, e.getIdEquipo());
                stmt.setInt(2, e.getIdEquipo());
                stmt.setInt(3, e.getIdGrupoEquipo());
                rs = stmt.executeQuery();
                
                TablaEquipos tablaEqui= new TablaEquipos();
                
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
            PreparedStatement stmt = this.getCn().prepareStatement("UPDATE partido SET idEquipoA = ?,idEquipoB = ?,golA = ?,golB = ?,idGrupo = ? WHERE idPartido = ?");
            stmt.setInt(1, cam.getIdEquipoA());  
            stmt.setInt(2, cam.getIdEquipoB());
            stmt.setInt(3, cam.getGolA());  
            stmt.setInt(4, cam.getGolB());  
            stmt.setInt(5, cam.getIdGrupo());
            stmt.setInt(6, cam.getIdPartido());  
            stmt.executeUpdate();
            
                        
            List<Integer> idGolEA;
            idGolEA = new ArrayList<>();             
            for(int i=0; i<cam.getGolEA().length;i++){
                idGolEA.add(cam.getGolEA()[i].getIdGol());
            }
            stmt.setArray(7, (Array) idGolEA);
            
            List<Integer> idGolEB;
            idGolEB = new ArrayList<>();             
            for(int i=0; i<cam.getGolEB().length;i++){
                idGolEB.add(cam.getGolEB()[i].getIdGol());
            }
            stmt.setArray(8, (Array) idGolEB);

            
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
    
    public boolean agregarPartidoJornada(Jornada jornada, int idPartido, Date hora) {
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareStatement(SqlAdminFutSal.ADD_MATCH_TO_JORNADA);
            int count = 1;
            stmt.setInt(count++, jornada.getIdJornada());                                      
            stmt.setTimestamp(count++, new java.sql.Timestamp((hora).getTime()));
            //stmt.setDate(count++, new java.sql.Date(hora.getTime()));
            stmt.setInt(count++, idPartido); 
            int res=stmt.executeUpdate();
            if(res>0){
                resp=true;
            }

        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
    
        public boolean finalizarPartido(Partido cam){
         boolean resp=false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement(SqlAdminFutSal.FINISH_MATCH);           
            int count = 1;
            stmt.setString(count++, Cons.STATE_MATCH_FIN); 
            stmt.setString(count++, cam.getObsPartido()); 
            stmt.setInt(count++, cam.getIdPartido());
             
            int res = stmt.executeUpdate();
             if(res>0){
                 resp=true;
             } 
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return resp;
    }
        
         public boolean finalizarPenalties(Partido cam){
         boolean resp=false;
        try{
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareStatement(SqlAdminFutSal.FINISH_PENALTIES);           

            stmt.setBoolean(1, true); 
            stmt.setInt(2, cam.getIdPartido());
             
            int res = stmt.executeUpdate();
             if(res>0){
                 resp=true;
             } 
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
        
        public boolean iniciarPartido(Partido cam){
        boolean resp=false;
        try{
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareStatement(SqlAdminFutSal.START_MATCH);                        
            stmt.setString(1, Cons.STATE_MATCH_JUG); 
            stmt.setInt(2, cam.getIdPartido());             
            int res = stmt.executeUpdate();
             if(res>0){
                 resp=true;
             }                         
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
        
    public boolean quitarPartidoJornada(Partido par){
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareStatement(SqlAdminFutSal.REMOVE_MATCH_FIXTURE);
                                  
            stmt.setInt(1, par.getIdPartido());  
            stmt.executeUpdate();
            resp= true;
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
        return resp;
    }
    
     public boolean agregarArbitroPartido(int idArbitro,int idPartido) {
        boolean resp= false;
        try{
            this.ConectionDataBase();
            PreparedStatement stmt = this.getCn().prepareStatement(SqlAdminFutSal.ADD_REFEREE_TO_MATCH);
            int count = 1;
            if(idArbitro==0){
                stmt.setNull(count++, Types.INTEGER);
            }else{
                stmt.setInt(count++, idArbitro); 
            }
            stmt.setInt(count++, idPartido); 
            int res=stmt.executeUpdate();
            if(res>0){
                resp=true;
            }

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
            PreparedStatement stmt = this.getCn().prepareStatement("DELETE FROM partido  WHERE idPartido = ?");
            stmt.setInt(1, cam.getIdPartido());          
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        this.CloseConection();
        }
    }
}
