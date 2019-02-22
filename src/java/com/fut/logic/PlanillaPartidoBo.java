/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.logic;

import com.fut.dao.ArbitroDao;
import com.fut.dto.PlanillaPartidoDTO;

/**
 *
 * @author YeisonUrrea
 */
public class PlanillaPartidoBo {
    
    private final ArbitroDao arbDao;

    public PlanillaPartidoBo() {
        arbDao = new ArbitroDao();
    }
    
    
    
    public void listarArbitros(PlanillaPartidoDTO dto){
        dto.setListaArbitros(arbDao.listarArbitros(dto.getCampeonato().getIdCampeonato()));       
    }
}
