/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.logic;

import com.fut.dao.ArbitroDao;
import com.fut.dao.GrupoDao;
import com.fut.dao.JornadaDao;
import com.fut.dao.JugadorDao;
import com.fut.dao.PartidoDao;
import com.fut.dao.PlayOffDao;
import com.fut.dao.TarjetaDao;
import com.fut.dto.AdminChampionShipDTO;
import com.fut.model.Equipo;
import com.fut.model.Grupo;
import com.fut.model.Partido;
import com.fut.model.PlayOff;
import com.fut.model.TablaEquipos;
import com.fut.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
public class AdminChampionshipBo {
    
    private final JugadorDao jugDao = new JugadorDao();
    private final JornadaDao jorDao = new JornadaDao();
    private final PartidoDao parDao = new PartidoDao();
    private final GrupoDao gruDao = new GrupoDao();
    private final PlayOffDao plaDao = new PlayOffDao();
    private final TarjetaDao tarDao = new TarjetaDao();
    private final ArbitroDao arbDao = new ArbitroDao();
    
    
    
    /*metodos play off*/
    private List<Equipo> calculateTeamClassifiedsOfGroups(int idChampionship){
        List<Equipo> list = new ArrayList<>();
        //lsitar grupos por campeonato
        List<Grupo> listGroup = gruDao.listGroupByChampionShip(idChampionship);
        for(Grupo gru: listGroup){
        //con cada grupo se trae el num de clasificados y se toman de la tabla de posiciones
             List<TablaEquipos> listaPosiciones = parDao.listarTablaPosiciones(gru.getIdGrupo()); 
             for(TablaEquipos tE: listaPosiciones){
                 if(listaPosiciones.lastIndexOf(tE)<gru.getNumClasificados()){
                     Equipo equ = new Equipo();
                     equ.setIdEquipo(tE.getIdEquipo());
                     equ.setNombreEquipo(tE.getNombre());
                     equ.setGrupo(gru);
                     list.add(equ);
                 }
             }
        
        }
        
        return list;
    }
    
    public  List<Equipo> listDefineTeamsForPlayoff(int idChampionship){
     List<Equipo> list;
    PlayOff pla = plaDao.loadLatestMathPlayoff(idChampionship);
    if(pla==null){
        list=calculateTeamClassifiedsOfGroups(idChampionship);
    }else{
        list= calculateTeamClassifiedsOfPlayOff(idChampionship, pla.getIdPlayOff());
    }
    
    return list;
    }
    
    private List<Equipo> calculateTeamClassifiedsOfPlayOff(int idChampionship, int idPlayoff){
    List<Equipo> list = new ArrayList<>();
    List<Partido> listPar = parDao.listarPartidosPlayOff(idPlayoff);
    for(Partido par: listPar){
        //consultar los goles de cada equipo y el que tenga la diferencia gana
        if(par.getGolA()>par.getGolB()){
            list.add(par.getEquipoA());
        }else if(par.getGolA()<par.getGolB()){
            list.add(par.getEquipoB());
        }else if(par.getGolA()==par.getGolB()){
            if(par.getPenalA()>par.getPenalB()){
                list.add(par.getEquipoA());
            }else if(par.getPenalA()<par.getPenalB()){
                list.add(par.getEquipoB());
            }
            
        }
    }
    return list;
    }
    
    
    /*metodos arbitro*/
    
    public void registrarArbitro(AdminChampionShipDTO dto) {  
        dto.getArbitro().setIdCampeonato(dto.getCampeonato().getIdCampeonato());
        boolean reg = arbDao.registrarArbitro(dto.getArbitro());
        if(reg){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Arbitro creado");  
            dto.setListArbitro(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL,  "Error", "no se pudo crear Arbitro");   
        }
        
    }
    
    public void modificarArbitro(AdminChampionShipDTO dto) {  
        
        boolean reg = arbDao.modificarArbitro(dto.getArbitro());
        if(reg){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Arbitro editado");  
            dto.setListArbitro(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));
        }else{
            Util.setMessage(FacesMessage.SEVERITY_FATAL,  "Error", "no se pudo editar Arbitro");   
        }
        
    }
    
     public void deleteArbitro(AdminChampionShipDTO dto) {
        boolean reg = arbDao.deleteReferee(dto.getArbitro().getIdArbitro());
         if(reg){
             Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Arbitro Eliminado");          
        }else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:no se pudo Eliminar", "porque tiene partidos asociados");
        FacesContext.getCurrentInstance().addMessage(null, message);}
       dto.setListArbitro(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));
    }
    
   public void listarArbitros(AdminChampionShipDTO dto){
       dto.setListArbitro(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));
   }
    
    
    
}
