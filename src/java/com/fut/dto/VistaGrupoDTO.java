/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Campeonato;
import com.fut.model.Grupo;
import com.fut.model.TablaEquipos;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
public class VistaGrupoDTO {
    private Campeonato campeonato; //campeonato en sesion
    private Grupo grupo = new Grupo();
    
    private List<TablaEquipos> listaPosiciones;

    public VistaGrupoDTO() {
        campeonato = (Campeonato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campeonato");  
    }
    
    
    
}
