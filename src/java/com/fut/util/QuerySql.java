/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.util;

/**
 *
 * @author YeisonUrrea
 */
public class QuerySql {
    /* DAO CAMPEONATO*/
    public static final String SELECT_CAMPEONATO = "SELECT \"idCampeonato\", \"nombreCampeonato\", \"idUsuario\" FROM public.campeonato";
    public static final String REGISTER_CAMPEONATO = "INSERT INTO public.campeonato (\"nombreCampeonato\", \"idUsuario\") values(?,?)";
    public static final String EDIT_CAMPEONATO = "";
    /* DAO JUGADOR*/
    public static final String SELECT_GOLEADORES = "SELECT jug.\"idJugador\", jug.\"nombreJugador\", jug.\"idEquipoJugador\", count (*) FROM public.jugador jug INNER JOIN public.gol  gol ON gol.\"idJugador\" = jug.\"idJugador\" INNER JOIN public.equipo  equ ON equ.\"idEquipo\" = jug.\"idEquipoJugador\"  INNER JOIN public.grupo  gru ON equ.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" AND cam.\"idCampeonato\" = ? GROUP BY jug.\"idJugador\" ORDER BY COUNT DESC";
    
}
