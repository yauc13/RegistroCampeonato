/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.logic;

import com.fut.dao.ArbitroDao;
import com.fut.dao.PartidoDao;
import com.fut.dto.PlanillaPartidoDTO;
import com.fut.util.Cons;
import com.fut.util.Util;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Yeison Urrea
 */
public class PlanillaPartidoBo {
    
    private final ArbitroDao arbDao;
    private final PartidoDao daoPar;

    public PlanillaPartidoBo() {
        arbDao = new ArbitroDao();        
        daoPar = new PartidoDao();
    }
    
    public String finalizarPartido(PlanillaPartidoDTO dto) {
     String direccion = "";
        daoPar.finalizarPartido(dto.getPartido());
        if (dto.getPartido().getIdGrupo() != 0) {
            
            if (dto.getGrupo() != null) {
                direccion = "vistaGrupo?faces-redirect=true";
            }else if (dto.getJornada() != null){
                direccion = "adminChampionShip?faces-redirect=true";
            }
        } else if (dto.getPartido().getIdPlayOff() != 0) {
          if(dto.getListaGolesA().size()==dto.getListaGolesB().size()){
              enablePanelPenalties(dto);
          }else{
            if (dto.getPlayOff() != null) {
                direccion = "listaPartidoPlayOff?faces-redirect=true";
            }else if (dto.getJornada() != null){
                direccion = "adminChampionShip?faces-redirect=true";
            }
          }
          
        } 
        return direccion;
    }
    
    
    
    public void listarArbitros(PlanillaPartidoDTO dto){
        dto.setListaArbitros(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));       
    }
    
    public void asignarArbitroPartido(int idArbitro, int idPartido){
        if(daoPar.agregarArbitroPartido(idArbitro,idPartido)){
            Util.setMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Arbitro asignado al partido");
        }else{
            Util.setMessage(FacesMessage.SEVERITY_ERROR, "Error", "Arbitro  NO asignado al partido");
        }
    }
    
    private void enablePanelPenalties(PlanillaPartidoDTO dto) {
        dto.setEnaBtnTar(true);
        dto.setEnaBtngol(true);
        dto.setEnaBtnFin(true);
        dto.setEnaBtnPenal(false);
        dto.setRenPanelPenal(true);
    }
    
    public void enableButtonStart(PlanillaPartidoDTO dto){
        if(dto.getPartido().getEstadoPartido().equals(Cons.STATE_MATCH_POR)){
            dto.setEnaBtnIniciar(false);
            dto.setEnaBtnFin(true);
            dto.setEnaBtnTar(true);
            dto.setEnaBtngol(true);
            dto.setRenPanelPenal(false);
            dto.setRenPanelPenal(true); 
        }else if(dto.getPartido().getEstadoPartido().equals(Cons.STATE_MATCH_JUG)){
            dto.setEnaBtnIniciar(true);
            dto.setEnaBtnFin(false);
            dto.setEnaBtnTar(false);
            dto.setEnaBtngol(false);
            dto.setRenPanelPenal(false);
            dto.setRenPanelPenal(true); 
        }if(dto.getPartido().getEstadoPartido().equals(Cons.STATE_MATCH_FIN)){
            dto.setEnaBtnIniciar(true);
            dto.setEnaBtnFin(true);
            dto.setEnaBtnTar(true);
            dto.setEnaBtngol(true);
            dto.setRenPanelPenal(true);
            dto.setRenPanelPenal(true); 
        }
    }
}
