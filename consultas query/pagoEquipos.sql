SELECT "idCampeonato", "idEquipo", "fechaPago", "valorPago", "idPagoEquipo"
	FROM public.r_pago_planilla_equipo;

/*total pagado por un equipo*/
SELECT  e."nombreEquipo",SUM ("valorPago") AS "totalPago" 
FROM public.r_pago_planilla_equipo ppe 
RIGHT JOIN public.equipo e ON ppe."idEquipo" = e."idEquipo"
INNER JOIN public.grupo g ON e."idGrupoEquipo" = g."idGrupo"
INNER JOIN public.campeonato c ON c."idCampeonato" = g."idCampeonato"
WHERE c."idCampeonato" = 30	
GROUP BY e."nombreEquipo"
ORDER BY e."nombreEquipo" asc

/*pagos de un equipo*/
SELECT ppe."idPagoEquipo", e."nombreEquipo", ppe."fechaPago",ppe."valorPago"
FROM public.r_pago_planilla_equipo ppe 
INNER JOIN public.equipo e ON ppe."idEquipo" = e."idEquipo"
WHERE ppe."idEquipo" = 42
ORDER BY ppe."fechaPago" desc