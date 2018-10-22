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
public class SqlAdminFutSal {
    /* DAO CAMPEONATO*/
    public static final String SELECT_CAMPEONATO = "SELECT \"idCampeonato\", \"nombreCampeonato\", \"idUsuario\" FROM public.campeonato";
    public static final String REGISTER_CAMPEONATO = "INSERT INTO public.campeonato (\"nombreCampeonato\", \"idUsuario\") values(?,?)";
    public static final String EDIT_CAMPEONATO = "";
    /* DAO JUGADOR*/
    public static final String INSERT_PLAYER = "INSERT INTO public.jugador (\"nombreJugador\",birthday,\"golJugador\",\"idEquipoJugador\",\"idUsuario\",\"fotoJugador\") values(?,?,?,?,?,?)";
    public static final String UPDATE_PLAYER = "UPDATE public.jugador SET \"nombreJugador\" = ?,birthday=?, \"fotoJugador\" = ? WHERE \"idJugador\" = ?";
    public static final String SELECT_GOLEADORES = "SELECT jug.\"idJugador\", jug.\"nombreJugador\", jug.\"idEquipoJugador\", count (*) FROM public.jugador jug INNER JOIN public.gol  gol ON gol.\"idJugador\" = jug.\"idJugador\" INNER JOIN public.equipo  equ ON equ.\"idEquipo\" = jug.\"idEquipoJugador\"  INNER JOIN public.grupo  gru ON equ.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" AND cam.\"idCampeonato\" = ? GROUP BY jug.\"idJugador\" ORDER BY COUNT DESC";
    /*DAO PARTIDO*/
    public static final String INSERT_PARTIDO = "INSERT INTO public.partido (\"idEquipoA\",\"idEquipoB\",\"idGrupo\",\"idUsuario\",\"estadoPartido\") values(?,?,?,?,?)";
    public static final String INSERT_PARTIDO_PLAYOFF = "INSERT INTO public.partido (\"idEquipoA\",\"idEquipoB\",\"idGrupo\",\"idUsuario\",\"estadoPartido\") values(?,?,?,?,?)";
    public static final String ADD_MATCH_TO_JORNADA = "UPDATE public.partido SET \"idJornada\" = ? WHERE \"idPartido\" = ?";
    public static final String REMOVE_MATCH_FIXTURE = "UPDATE public.partido SET \"idJornada\" = null WHERE \"idPartido\" = ?";
    public static final String FINISH_MATCH = "UPDATE public.partido SET \"golA\" = ?,\"golB\" = ?,\"estadoPartido\" = ? WHERE \"idPartido\" = ?";
    public static final String LIST_POSITION_TEAM = "SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\" FROM public.partido WHERE (\"golA\">-1 OR \"golA\">-1)AND( \"idEquipoA\"=? OR \"idEquipoB\"=?)";
    public static final String SELECT_PARTIDOS_JORNADA = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"golA\", pa.\"golB\", pa.\"idGrupo\", ea.\"nombreEquipo\", eb.\"nombreEquipo\", pa.\"estadoPartido\", pa.\"idJornada\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\") JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idJornada\"=?) ORDER BY  pa.\"idPartido\" ASC";
    public static final String SELECT_PARTIDOS_GRUPO = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"golA\", pa.\"golB\", pa.\"idGrupo\", ea.\"nombreEquipo\", eb.\"nombreEquipo\", pa.\"estadoPartido\", pa.\"idJornada\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idGrupo\"=?) ORDER BY  pa.\"idPartido\" ASC";
    public static final String SELECT_PARTIDOS_PLAYOFF = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"golA\", pa.\"golB\", pa.\"idGrupo\", ea.\"nombreEquipo\", eb.\"nombreEquipo\", pa.\"estadoPartido\", pa.\"idJornada\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idPlayOff\"=?) ORDER BY  pa.\"idPartido\" ASC";
    public static final String SELECT_PARTIDOS_GRUPO_JOR = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"golA\", pa.\"golB\", pa.\"idGrupo\", ea.\"nombreEquipo\", eb.\"nombreEquipo\", pa.\"estadoPartido\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idGrupo\"=? AND pa.\"idJornada\" is null) ORDER BY  pa.\"idPartido\" ASC";
    
    /*DAO GRUPO*/
    public static final String INSERT_GRUP = "INSERT INTO public.grupo (\"nombreGrupo\",\"clasificadosGrupo\",\"idCampeonato\",\"idUsuario\") values(?,?,?,?)";
    public static final String UPDATE_GRUP = "UPDATE public.grupo SET \"nombreGrupo\" = ?, \"clasificadosGrupo\" = ? WHERE \"idGrupo\" = ?";
    public static final String SELECT_GROUP_BY_CHAMPIONSHIP = "SELECT \"idGrupo\", \"nombreGrupo\",\"clasificadosGrupo\",\"idUsuario\" FROM public.grupo WHERE \"idCampeonato\" = ? ORDER BY \"idGrupo\"";
    /*DAO JORNADA*/
    public static final String INSERT_FIXTURE = "INSERT INTO public.jornada (\"nombreJornada\",\"idCampeonato\") values(?,?)";
    public static final String SELECT_FIXTURE_CAMPEONATO = "SELECT \"idJornada\", \"nombreJornada\" FROM public.jornada WHERE \"idCampeonato\" = ?";
     
    /*DAO PLAYOFF*/
    public static final String INSERT_PLAYOFF = "INSERT INTO public.playoff(\"namePlayOff\", \"numPartidos\", \"idCampeonato\") VALUES (?, ?, ?);";
    public static final String UPDATE_PLAYOFF = "UPDATE public.playoff SET  \"namePlayOff\"=?, \"numPartidos\"=? WHERE \"idPlayOff\"=?;";        
    public static final String  SELECT_PLAYOFF_BY_CHAMPIONSHIP =  "SELECT \"idPlayOff\", \"namePlayOff\",\"numPartidos\", \"idCampeonato\" FROM public.playoff WHERE \"idCampeonato\" = ? ORDER BY \"idPlayOff\"";;    
}
