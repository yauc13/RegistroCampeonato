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
public class Tarjeta {
    private int idTarjeta;
    private int idJugador;
    private int idEquipo;
    private int idPartido;
    private String typeCard;
    private boolean pagoTarjeta;
    private int idEquipoB;
    
    private String nombreJugador;
    private String nombreEquipo;
    private String nombreEquipoB;
    private Date fechaTarjeta;

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreEquipoB() {
        return nombreEquipoB;
    }

    public void setNombreEquipoB(String nombreEquipoB) {
        this.nombreEquipoB = nombreEquipoB;
    }

    public boolean isPagoTarjeta() {
        return pagoTarjeta;
    }

    public void setPagoTarjeta(boolean pagoTarjeta) {
        this.pagoTarjeta = pagoTarjeta;
    }

    public int getIdEquipoB() {
        return idEquipoB;
    }

    public void setIdEquipoB(int idEquipoB) {
        this.idEquipoB = idEquipoB;
    }

    public Date getFechaTarjeta() {
        return fechaTarjeta;
    }

    public void setFechaTarjeta(Date fechaTarjeta) {
        this.fechaTarjeta = fechaTarjeta;
    }
    
    
    
    
    
    
    
    
}
