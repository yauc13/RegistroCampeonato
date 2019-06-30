/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.logic;

import com.fut.dao.PartidoDao;
import com.fut.dto.VistaGrupoDTO;

/**
 *
 * @author YeisonUrrea
 */
public class VistaGrupoBo {
    
    public void viewMatchByTeam(VistaGrupoDTO dto){
        PartidoDao daoPar=new PartidoDao();
     dto.setListMatchByTeam(daoPar.listarPartidosPorEquipo(dto.getGrupo().getIdGrupo(), dto.getTeamTableSel().getIdEquipo()));
    }
}
