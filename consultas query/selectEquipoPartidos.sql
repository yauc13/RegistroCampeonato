/*muestra tabla con total par loc y vis*/
SELECT equ."idEquipo",equ."nombreEquipo", count(par."idPartido") as "totPar",par."idPartido",par."idEquipoA",par."idEquipoB",
(
	SELECT count(golA."idGol") as "golEqA"
	FROM public.partido paA
	LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
	where paA."idPartido" = par."idPartido" AND paA."idEquipoA" = golA."idEquipo"
	group by paA."idPartido"	
	ORDER BY paA."idPartido"
	)
	,( 
	SELECT  count(golB."idGol")  as "golEqB"
	FROM public.partido paB
	LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
	where paB."idPartido" = par."idPartido" AND paB."idEquipoB" = golB."idEquipo"
	group by paB."idPartido"	
	ORDER BY paB."idPartido"
	),
	(
		SELECT count(parL."idEquipoA") as "totParL"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoA"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PGL"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PEL"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PPL"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "totParV"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PGV"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PEV"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),(
		SELECT count(parL."idEquipoB") as "PPV"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	)

FROM public.equipo equ
LEFT JOIN public.partido par ON (equ."idEquipo" = par."idEquipoA" OR equ."idEquipo" = par."idEquipoB")
LEFT JOIN public.gol gol ON (par."idPartido" = gol."idPartido" )
WHERE equ."idGrupoEquipo"=16 AND par."idGrupo"=16 
GROUP BY par."idPartido",equ."idEquipo", equ."nombreEquipo"
order by equ."nombreEquipo"
