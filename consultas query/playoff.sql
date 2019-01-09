SELECT "idPlayOff", "namePlayOff", "numPartidos", "idCampeonato"
	FROM public.playoff;
	
/*seleccionar el ultimo registro de playoff*/
SELECT "idPlayOff", "namePlayOff", "numPartidos", "idCampeonato"
FROM public.playoff
WHERE "idCampeonato" = 30
ORDER BY "idPlayOff" DESC
LIMIT 1