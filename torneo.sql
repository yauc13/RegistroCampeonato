-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.31-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para torneo
CREATE DATABASE IF NOT EXISTS `torneo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `torneo`;

-- Volcando estructura para tabla torneo.campeonato
CREATE TABLE IF NOT EXISTS `campeonato` (
  `idCampeonato` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCampeonato` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idCampeonato`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla torneo.equipo
CREATE TABLE IF NOT EXISTS `equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEquipo` varchar(30) COLLATE utf8_bin NOT NULL,
  `pgEquipo` int(11) NOT NULL,
  `peEquipo` int(11) NOT NULL,
  `ppEquipo` int(11) NOT NULL,
  `gfEquipo` int(11) NOT NULL,
  `gcEquipo` int(11) NOT NULL,
  `idGrupoEquipo` int(11) NOT NULL,
  PRIMARY KEY (`idEquipo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla torneo.grupo
CREATE TABLE IF NOT EXISTS `grupo` (
  `idGrupo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreGrupo` varchar(20) COLLATE utf8_bin NOT NULL,
  `idCampeonato` int(11) NOT NULL,
  PRIMARY KEY (`idGrupo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla torneo.jornada
CREATE TABLE IF NOT EXISTS `jornada` (
  `idJornada` int(11) NOT NULL AUTO_INCREMENT,
  `nombreJornada` varchar(30) COLLATE utf8_bin NOT NULL,
  `idPartidoJornada` int(11) NOT NULL,
  PRIMARY KEY (`idJornada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla torneo.jugador
CREATE TABLE IF NOT EXISTS `jugador` (
  `idJugador` int(11) NOT NULL AUTO_INCREMENT,
  `nombreJugador` varchar(30) COLLATE utf8_bin NOT NULL,
  `golJugador` int(11) NOT NULL,
  `idEquipoJugador` int(11) NOT NULL,
  PRIMARY KEY (`idJugador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla torneo.partido
CREATE TABLE IF NOT EXISTS `partido` (
  `idPartido` int(11) NOT NULL AUTO_INCREMENT,
  `idEquipoA` int(11) NOT NULL,
  `idEquipoB` int(11) NOT NULL,
  `golA` int(11) NOT NULL,
  `golB` int(11) NOT NULL,
  `idGrupo` int(11) NOT NULL,
  PRIMARY KEY (`idPartido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
