/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.util.Date;

/**
 *
 * @author YeisonUrrea
 */
public class PagoPlanilla {
    
   private int idPagoEquipo;
   private int idCampeonato;
   private int idEquipo;
   private Date fechaPago;
   private int valorPago;

    public int getIdPagoEquipo() {
        return idPagoEquipo;
    }

    public void setIdPagoEquipo(int idPagoEquipo) {
        this.idPagoEquipo = idPagoEquipo;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }
   
   
    
}
