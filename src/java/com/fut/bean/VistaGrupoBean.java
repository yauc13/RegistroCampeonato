/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.bean;

import com.fut.dto.VistaGrupoDTO;
import com.fut.logic.VistaGrupoBo;
import com.fut.model.Grupo;
import com.fut.model.TablaEquipos;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
@ManagedBean
@ViewScoped
public class VistaGrupoBean implements Serializable{
    
    private final VistaGrupoDTO dto;
    private final VistaGrupoBo bo;

    public VistaGrupoBean() {
        dto = new VistaGrupoDTO();
        bo = new VistaGrupoBo();
        
        dto.setGrupo((Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupo"));
    }
    
    public void viewMatchByTeam(TablaEquipos tE){
        dto.setTeamTableSel(tE);
       bo.viewMatchByTeam(dto);
    }
    
    
    
    
    
    public VistaGrupoDTO getDto() {
        return dto;
    }
    
   
    
    
    
    
}
