/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dto;

import com.fut.model.Campeonato;
import com.fut.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YEISON URREA
 */
public class CampeonatoDTO {

    private Campeonato campeonato = new Campeonato();
    private Usuario usuario = new Usuario();
    private List<Campeonato> listaCampeonato;
    private String loginUsuario;
    private String accion;

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Campeonato getCampeonato() {
        if (campeonato == null) {
            campeonato = new Campeonato();
        }
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public List<Campeonato> getListaCampeonato() {
        if (listaCampeonato == null) {
            listaCampeonato = new ArrayList<>();
        }
        return listaCampeonato;
    }

    public void setListaCampeonato(List<Campeonato> listaCampeonato) {
        this.listaCampeonato = listaCampeonato;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
