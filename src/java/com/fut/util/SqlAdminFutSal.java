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
    public static final String SELECT_GOLEADORES = "SELECT jug.\"idJugador\", jug.\"nombreJugador\", equ.\"nombreEquipo\", count (*) FROM public.jugador jug INNER JOIN public.gol  gol ON gol.\"idJugador\" = jug.\"idJugador\" INNER JOIN public.equipo  equ ON equ.\"idEquipo\" = jug.\"idEquipoJugador\"  INNER JOIN public.grupo  gru ON equ.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" AND cam.\"idCampeonato\" = ? GROUP BY jug.\"idJugador\", equ.\"nombreEquipo\"  ORDER BY COUNT DESC";
    /*DAO PARTIDO*/
    public static final String INSERT_PARTIDO = "INSERT INTO public.partido (\"idEquipoA\",\"idEquipoB\",\"remplazo\",\"idUsuario\",\"estadoPartido\") values(?,?,?,?,?)";
    public static final String INSERT_PARTIDO_PLAYOFF = "INSERT INTO public.partido (\"idEquipoA\",\"idEquipoB\",\"idGrupo\",\"idUsuario\",\"estadoPartido\") values(?,?,?,?,?)";
    public static final String ADD_MATCH_TO_JORNADA = "UPDATE public.partido SET \"idJornada\" = ?, \"fechaPartido\" = ? WHERE \"idPartido\" = ?";
    public static final String REMOVE_MATCH_FIXTURE = "UPDATE public.partido SET \"idJornada\" = null WHERE \"idPartido\" = ?";
    public static final String FINISH_MATCH = "UPDATE public.partido SET \"estadoPartido\" = ? WHERE \"idPartido\" = ?";
    public static final String START_MATCH = "UPDATE public.partido SET \"estadoPartido\" = ? WHERE \"idPartido\" = ?";
    public static final String LIST_POSITION_TEAM = "SELECT \"idPartido\",\"idEquipoA\",\"idEquipoB\",\"golA\",\"golB\" FROM public.partido WHERE (\"golA\">-1 OR \"golA\">-1)AND( \"idEquipoA\"=? OR \"idEquipoB\"=?) AND \"idGrupo\"=?";
    public static final String LIST_POSITION_TEAM_GROUP = "SELECT equ.\"idEquipo\",equ.\"nombreEquipo\", count(par.\"idPartido\") as \"totPar\", coalesce(( SELECT count(parL.\"idEquipoB\") as \"totParL\" FROM public.equipo equL INNER JOIN public.partido parL ON (equL.\"idEquipo\" = parL.\"idEquipoA\" OR equL.\"idEquipo\" = parL.\"idEquipoB\") WHERE equ.\"idEquipo\"=equL.\"idEquipo\" AND equ.\"idEquipo\"=parL.\"idEquipoA\"  AND equ.\"idGrupoEquipo\"=\"idGru\" AND parL.\"idGrupo\"=\"idGru\" AND (parL.\"estadoPartido\"='Finalizado' OR  parL.\"estadoPartido\"='Jugando') GROUP BY equL.\"idEquipo\" ),0) as \"TPL\",( SELECT count(*) as \"PGL\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\"  AND (pa.\"estadoPartido\"='Finalizado' OR  pa.\"estadoPartido\"='Jugando') AND pa.\"idEquipoA\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0)> coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ),( SELECT count(*) as \"PEL\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\"   AND (pa.\"estadoPartido\"='Finalizado' OR  pa.\"estadoPartido\"='Jugando')  AND pa.\"idEquipoA\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) = coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ),( SELECT count(*) as \"PPL\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\"   AND (pa.\"estadoPartido\"='Finalizado' OR  pa.\"estadoPartido\"='Jugando')  AND pa.\"idEquipoA\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) < coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ), coalesce(( SELECT count(parL.\"idEquipoB\") as \"totParV\" FROM public.equipo equL INNER JOIN public.partido parL ON (equL.\"idEquipo\" = parL.\"idEquipoA\" OR equL.\"idEquipo\" = parL.\"idEquipoB\") WHERE equ.\"idEquipo\"=equL.\"idEquipo\" AND equ.\"idEquipo\"=parL.\"idEquipoB\"  AND equ.\"idGrupoEquipo\"=\"idGru\" AND parL.\"idGrupo\"=\"idGru\" AND (parL.\"estadoPartido\"='Finalizado' OR  parL.\"estadoPartido\"='Jugando') GROUP BY equL.\"idEquipo\" ),0) as \"TPV\", ( SELECT count(*) as \"PGV\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\"   AND (pa.\"estadoPartido\"='Finalizado' OR  pa.\"estadoPartido\"='Jugando')  AND pa.\"idEquipoB\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0)< coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ),( SELECT count(*) as \"PEV\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\"   AND (pa.\"estadoPartido\"='Finalizado' OR  pa.\"estadoPartido\"='Jugando')  AND   pa.\"idEquipoB\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) = coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ),( SELECT count(*) as \"PPV\" FROM (SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", count(gol.\"idGol\") as \"golPar\", coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) as \"golA\", coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) as \"golB\" FROM public.partido pa LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" where pa.\"idGrupo\"=\"idGru\" AND (pa.\"estadoPartido\"='Finalizado' OR pa.\"estadoPartido\"='Jugando') AND pa.\"idEquipoB\" = equ.\"idEquipo\" group by pa.\"idPartido\" HAVING coalesce(( SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\" ),0) > coalesce(( SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\" ),0) ORDER BY pa.\"idPartido\" ) as \"PGL\" ),( SELECT count (*) as \"GF\" FROM public.gol gol where gol.\"idEquipo\"= equ.\"idEquipo\" ),( SELECT count (*) as \"GC\" FROM public.gol gol where gol.\"idEquipoB\"= equ.\"idEquipo\" ) FROM public.equipo equ LEFT JOIN public.partido par ON (equ.\"idEquipo\" = par.\"idEquipoA\" OR equ.\"idEquipo\" = par.\"idEquipoB\") WHERE equ.\"idGrupoEquipo\"=\"idGru\"  AND par.\"idGrupo\"=\"idGru\" GROUP BY equ.\"idEquipo\"";
    public static final String SELECT_PARTIDOS_JORNADA = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", gr.\"nombreGrupo\", ea.\"nombreEquipo\" as \"nombreEquipoA\", eb.\"nombreEquipo\" as \"nombreEquipoB\", pa.\"estadoPartido\", pa.\"idJornada\", pa.\"fechaPartido\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")  JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\") JOIN public.grupo gr ON (pa.\"idGrupo\" = gr.\"idGrupo\") WHERE pa.\"idJornada\"=? ORDER BY  pa.\"fechaPartido\"  ASC";
    public static final String SELECT_PARTIDOS_GRUPO = "SELECT pa.\"idPartido\", pa.\"idEquipoA\",eqA.\"nombreEquipo\" as \"nombreEquipoA\", pa.\"idEquipoB\",eqB.\"nombreEquipo\" as \"nombreEquipoB\",pa.\"idGrupo\",gru.\"nombreGrupo\",pa.\"estadoPartido\",pa.\"idJornada\" ,count(gol.\"idGol\") as \"golPar\",(SELECT count(golA.\"idGol\") as \"golEqA\" FROM public.partido paA LEFT JOIN public.gol golA ON paA.\"idPartido\" = golA.\"idPartido\" where paA.\"idPartido\" = pa.\"idPartido\" AND paA.\"idEquipoA\" = golA.\"idEquipo\" group by paA.\"idPartido\" ORDER BY paA.\"idPartido\"),(SELECT count(golB.\"idGol\") as \"golEqB\" FROM public.partido paB	 LEFT JOIN public.gol golB ON paB.\"idPartido\" = golB.\"idPartido\" where paB.\"idPartido\" = pa.\"idPartido\" AND paB.\"idEquipoB\" = golB.\"idEquipo\" group by paB.\"idPartido\" ORDER BY paB.\"idPartido\") FROM public.partido pa INNER JOIN public.equipo eqA on pa.\"idEquipoA\" = eqA.\"idEquipo\" INNER JOIN public.equipo eqB on pa.\"idEquipoB\" = eqB.\"idEquipo\" INNER JOIN public.grupo gru on pa.\"idGrupo\" = gru.\"idGrupo\" LEFT JOIN public.gol gol ON pa.\"idPartido\" = gol.\"idPartido\" WHERE pa.\"idGrupo\" = ? group by pa.\"idPartido\",eqA.\"nombreEquipo\",eqB.\"nombreEquipo\", gru.\"nombreGrupo\" ORDER BY pa.\"idPartido\"";
    public static final String SELECT_PARTIDOS_PLAYOFF = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", ea.\"nombreEquipo\" as \"nombreEquipoA\", eb.\"nombreEquipo\" as \"nombreEquipoB\", pa.\"estadoPartido\", pa.\"idJornada\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idPlayOff\"=?) ORDER BY  pa.\"idPartido\" ASC";
    public static final String SELECT_PARTIDOS_GRUPO_JOR = "SELECT pa.\"idPartido\", pa.\"idEquipoA\", pa.\"idEquipoB\", pa.\"idGrupo\", ea.\"nombreEquipo\" as \"nombreEquipoA\" , eb.\"nombreEquipo\" as \"nombreEquipoB\" , pa.\"estadoPartido\" FROM  public.partido pa INNER JOIN public.equipo ea ON (pa.\"idEquipoA\" = ea.\"idEquipo\")JOIN public.equipo eb ON (pa.\"idEquipoB\" = eb.\"idEquipo\" AND pa.\"idGrupo\"=? AND pa.\"idJornada\" is null) ORDER BY  pa.\"idPartido\" ASC";
    
    /*DAO GRUPO*/
    public static final String INSERT_GRUP = "INSERT INTO public.grupo (\"nombreGrupo\",\"clasificadosGrupo\",\"idCampeonato\",\"idUsuario\") values(?,?,?,?)";
    public static final String UPDATE_GRUP = "UPDATE public.grupo SET \"nombreGrupo\" = ?, \"clasificadosGrupo\" = ? WHERE \"idGrupo\" = ?";
    public static final String SELECT_GROUP_BY_CHAMPIONSHIP = "SELECT \"idGrupo\", \"nombreGrupo\",\"clasificadosGrupo\",\"idUsuario\" FROM public.grupo WHERE \"idCampeonato\" = ? ORDER BY \"idGrupo\"";
    /*DAO JORNADA*/
    public static final String INSERT_FIXTURE = "INSERT INTO public.jornada (\"nombreJornada\",\"idCampeonato\",\"fechaJornada\") values(?,?,?)";
    public static final String SELECT_FIXTURE_CAMPEONATO = "SELECT * FROM public.jornada WHERE \"idCampeonato\" = ? order by \"fechaJornada\" ";
     
    /*DAO PLAYOFF*/
    public static final String INSERT_PLAYOFF = "INSERT INTO public.playoff(\"namePlayOff\", \"numPartidos\", \"idCampeonato\") VALUES (?, ?, ?);";
    public static final String UPDATE_PLAYOFF = "UPDATE public.playoff SET  \"namePlayOff\"=?, \"numPartidos\"=? WHERE \"idPlayOff\"=?;";        
    public static final String SELECT_PLAYOFF_BY_CHAMPIONSHIP =  "SELECT \"idPlayOff\", \"namePlayOff\",\"numPartidos\", \"idCampeonato\" FROM public.playoff WHERE \"idCampeonato\" = ? ORDER BY \"idPlayOff\"";;    
    public static final String SELECT_LATEST_PLAYOFF_BY_CHAMPIONSHIP = "SELECT \"idPlayOff\", \"namePlayOff\", \"numPartidos\", \"idCampeonato\" FROM public.playoff where  \"idCampeonato\" = ? order by \"idPlayOff\" desc limit 1";
    
    /*DAO EQUIPO*/
    public static final String  SELECT_TEAM_CLASSIFIED = "SELECT \"idEquipo\", \"nombreEquipo\", \"nombreGrupo\" FROM public.equipo equ INNER JOIN public.grupo  gru ON equ.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato cam ON gru.\"idCampeonato\" = cam.\"idCampeonato\" WHERE cam.\"idCampeonato\" = ? ORDER BY gru.\"nombreGrupo\" ASC";
    
    public static final String SELECT_LIST_TEAM_ALL_PAY = "SELECT e.\"idEquipo\", e.\"nombreEquipo\",SUM (\"valorPago\") AS \"totalPago\" FROM public.pago_planilla_equipo ppe RIGHT JOIN public.equipo e ON ppe.\"idEquipo\" = e.\"idEquipo\" INNER JOIN public.grupo g ON e.\"idGrupoEquipo\" = g.\"idGrupo\" INNER JOIN public.campeonato c ON c.\"idCampeonato\" = g.\"idCampeonato\" WHERE c.\"idCampeonato\" = ?	GROUP BY e.\"idEquipo\", e.\"nombreEquipo\" ORDER BY e.\"nombreEquipo\" asc";
    public static final String SELECT_TEAM_PAY = "SELECT ppe.\"idPagoEquipo\", e.\"nombreEquipo\", ppe.\"fechaPago\",ppe.\"valorPago\" FROM public.pago_planilla_equipo ppe INNER JOIN public.equipo e ON ppe.\"idEquipo\" = e.\"idEquipo\" WHERE ppe.\"idEquipo\" = ? ORDER BY ppe.\"fechaPago\" desc";
    public static final String INSERT_TEAM_PAY = "INSERT INTO public.pago_planilla_equipo(\"idCampeonato\", \"idEquipo\", \"fechaPago\", \"valorPago\") VALUES (?, ?, ?, ?);";
    public static final String SELECT_LIST_PAY_BY_TEAM = "SELECT ppe.\"idPagoEquipo\", e.\"nombreEquipo\", ppe.\"fechaPago\",ppe.\"valorPago\" FROM public.pago_planilla_equipo ppe  INNER JOIN public.equipo e ON ppe.\"idEquipo\" = e.\"idEquipo\" WHERE ppe.\"idEquipo\" = ? ORDER BY ppe.\"fechaPago\" desc";
    public static final String  SELECT_TEAM_NOT_PLAY = "SELECT \"idEquipo\", \"nombreEquipo\" FROM public.equipo where  \"idGrupoEquipo\" = ? AND \"idEquipo\" not in (array)";
    public static final String  SELECT_IDTEAM_A_PLAY = "SELECT p.\"idEquipoA\" FROM public.partido p inner join public.equipo e on (e.\"idEquipo\" = p.\"idEquipoB\") where p.\"idGrupo\" = ? AND e.\"idEquipo\" = ?";
    public static final String  SELECT_IDTEAM_B_PLAY = "SELECT p.\"idEquipoB\" FROM public.partido p inner join public.equipo e on (e.\"idEquipo\" = p.\"idEquipoA\") where p.\"idGrupo\" = ? AND e.\"idEquipo\" = ?";
    
    
    /*DAO GOL*/
    public static final String  INSERT_GOL = "INSERT INTO public.gol (\"idJugador\",\"idEquipo\",\"idPartido\",\"idEquipoB\") values(?,?,?,?)";
    
     /*DAO TARJETA*/
    public static final String  INSERT_CARD = "INSERT INTO public.tarjeta (\"idJugador\",\"idEquipo\",\"idPartido\",\"tipoTarjeta\", \"pagoTarjeta\", \"idEquipoB\") values(?,?,?,?,?,?)";
    public static final String  UPDATE_CARD = "UPDATE public.tarjeta  SET \"pagoTarjeta\"=? WHERE \"idTarjeta\"=?";
    public static final String  SELECT_CARD_CAN_PLAYER = "SELECT \"idTarjeta\", ju.\"nombreJugador\", eq.\"nombreEquipo\", \"tipoTarjeta\", eqB.\"nombreEquipo\" as\"nombreEquipoB\",  \"pagoTarjeta\", pa.\"fechaPartido\" FROM public.tarjeta ta INNER JOIN public.jugador ju on ta.\"idJugador\" = ju.\"idJugador\" INNER JOIN public.partido pa on ta.\"idPartido\" = pa.\"idPartido\" INNER JOIN public.equipo eq ON ta.\"idEquipo\" = eq.\"idEquipo\" INNER JOIN public.equipo eqB ON ta.\"idEquipoB\" = eqB.\"idEquipo\" INNER JOIN public.grupo  gru ON eq.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" WHERE  cam.\"idCampeonato\" = ? AND ta.\"pagoTarjeta\"=true ORDER BY ta.\"idTarjeta\" DESC";
    public static final String  SELECT_CARD_PAG_PLAYER = "SELECT \"idTarjeta\", ju.\"nombreJugador\", eq.\"nombreEquipo\", \"tipoTarjeta\", eqB.\"nombreEquipo\" as\"nombreEquipoB\",  \"pagoTarjeta\", pa.\"fechaPartido\" FROM public.tarjeta ta INNER JOIN public.jugador ju on ta.\"idJugador\" = ju.\"idJugador\" INNER JOIN public.partido pa on ta.\"idPartido\" = pa.\"idPartido\" INNER JOIN public.equipo eq ON ta.\"idEquipo\" = eq.\"idEquipo\" INNER JOIN public.equipo eqB ON ta.\"idEquipoB\" = eqB.\"idEquipo\" INNER JOIN public.grupo  gru ON eq.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" WHERE  cam.\"idCampeonato\" = ? AND ta.\"pagoTarjeta\"=false ORDER BY ta.\"idTarjeta\" DESC";
    public static final String  SELECT_CARD_ALL_PLAYER = "SELECT \"idTarjeta\", ju.\"nombreJugador\", eq.\"nombreEquipo\", \"tipoTarjeta\", eqB.\"nombreEquipo\" as\"nombreEquipoB\",  \"pagoTarjeta\", pa.\"fechaPartido\" FROM public.tarjeta ta INNER JOIN public.jugador ju on ta.\"idJugador\" = ju.\"idJugador\" INNER JOIN public.partido pa on ta.\"idPartido\" = pa.\"idPartido\" INNER JOIN public.equipo eq ON ta.\"idEquipo\" = eq.\"idEquipo\" INNER JOIN public.equipo eqB ON ta.\"idEquipoB\" = eqB.\"idEquipo\" INNER JOIN public.grupo  gru ON eq.\"idGrupoEquipo\" = gru.\"idGrupo\" INNER JOIN public.campeonato  cam ON gru.\"idCampeonato\"  = cam.\"idCampeonato\" WHERE  cam.\"idCampeonato\" = ? ORDER BY ta.\"idTarjeta\" DESC";
    
    
    
    
}
