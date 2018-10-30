/*muestra tabla con total par gan, empa y perdidos loc y vis*/
SELECT equ."idEquipo",equ."nombreEquipo", count(par."idPartido") as "totPar",
	coalesce((
		SELECT count(parL."idEquipoB") as "totParL"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoA"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),0) as "TPL",(
		SELECT count(*) as "PGL" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoA" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0)> coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	   

	),(
		SELECT count(*) as "PEL" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoA" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) = coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	),(
		SELECT count(*) as "PPL" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoA" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) < coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	),coalesce((
		SELECT count(parL."idEquipoB") as "totParV"
		FROM public.equipo equL
		INNER JOIN public.partido parL ON (equL."idEquipo" = parL."idEquipoA" OR equL."idEquipo" = parL."idEquipoB")
		WHERE equ."idEquipo"=equL."idEquipo" AND equ."idEquipo"=parL."idEquipoB"  AND equ."idGrupoEquipo"=16
		GROUP BY equL."idEquipo"
	),0) as "TPV",(
		SELECT count(*) as "PGV" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoB" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0)> coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	   
	),(
		SELECT count(*) as "PEV" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoB" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) = coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	   
	),(
		SELECT count(*) as "PPV" FROM (SELECT pa."idPartido", pa."idEquipoA", pa."idEquipoB",
		pa."idGrupo",
		count(gol."idGol") as "golPar",
		coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) as "golA", coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0) as "golB"
		FROM public.partido pa
		LEFT JOIN public.gol gol ON pa."idPartido" = gol."idPartido"
		where pa."idGrupo"=16  AND pa."idEquipoB" = equ."idEquipo"
		group by pa."idPartido"
		HAVING coalesce((
		SELECT count(golA."idGol") as "golEqA"
		FROM public.partido paA
		LEFT JOIN public.gol golA ON paA."idPartido" = golA."idPartido"
		where paA."idPartido" = pa."idPartido" AND paA."idEquipoA" = golA."idEquipo"
		group by paA."idPartido"	
		ORDER BY paA."idPartido"
		),0) < coalesce((
		SELECT count(golB."idGol") as "golEqB"
		FROM public.partido paB
		LEFT JOIN public.gol golB ON paB."idPartido" = golB."idPartido"
		where paB."idPartido" = pa."idPartido" AND paB."idEquipoB" = golB."idEquipo"
		group by paB."idPartido"	
		ORDER BY paB."idPartido"
		),0)
		ORDER BY pa."idPartido"
		) as "PGL"
	   
	)

FROM public.equipo equ
LEFT JOIN public.partido par ON (equ."idEquipo" = par."idEquipoA" OR equ."idEquipo" = par."idEquipoB")
WHERE equ."idGrupoEquipo"=16
GROUP BY equ."idEquipo"

