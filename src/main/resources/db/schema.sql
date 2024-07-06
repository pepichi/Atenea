/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
/**
 * Author:  José Puerta Cardelles
 * Created: 5 jul 2024
 */
-- Crear una base de datos si no existe
CREATE DATABASE IF NOT EXISTS atenea;

-- Usar la base de datos
USE atenea;

-- atenea.categoria definition
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT COMMENT 'primary key de la categoría',
  `nombre` varchar(255) NOT NULL COMMENT 'Nombre de la categoría',
  `descripcion` varchar(4000) DEFAULT NULL COMMENT 'Descripción de la categoría',
  `fecha_insercion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se introdujo la categoría en el sistema',
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COMMENT='categoría de preguntas tipo test';

-- atenea.configuracion_examen definition
CREATE TABLE `configuracion_examen` (
  `id_configuracion_examen` int NOT NULL AUTO_INCREMENT COMMENT 'id de la configuracóin de examen',
  `nombre_configuracion` varchar(255) NOT NULL COMMENT 'Nombre de la configuración',
  `numero_preguntas` decimal(10,0) NOT NULL COMMENT 'número de preguntas en el examen',
  `respuesta_pregunta` decimal(10,0) NOT NULL COMMENT 'número de respuestas que se mostrarán por pregunta',
  `multirrespuesta` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'indica si se permiten preguntas multirrespuesta',
  `limite_tiempo` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'tiempo para realizar el examen, por defecto 0, que significa que es infinito. En otro caso los minutos restantes para realizar el examen.',
  `mostrar_tiempo` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'indica si hay que mostrar el tiempo restante del examen',
  `mostrar_feedback` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'mostrar feedback si hay error en la pregunta',
  `mostrar_inmediatamente` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'al marcar se va verificando si la respuesta es correcta',
  `usar_todas_categorias` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'todas las categorías con el mismo peso',
  `categorias_seleccionadas_mismo_peso` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'indica que todas las categorias seleccionadas tienen el mismo peso',
  PRIMARY KEY (`id_configuracion_examen`),
  UNIQUE KEY `configuracion_examen_unique` (`nombre_configuracion`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabla que almacena la configuración de exámenes';

-- atenea.configuracion_examen_categorias definition
CREATE TABLE `configuracion_examen_categorias` (
  `id_configuracion_examen_categorias` int NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla',
  `id_configuracion_examen` int NOT NULL COMMENT 'id de la configuración',
  `id_categoria` int NOT NULL COMMENT 'id de la categoría',
  `peso` double unsigned NOT NULL COMMENT 'peso de la categoría dentro del examen',
  PRIMARY KEY (`id_configuracion_examen_categorias`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- atenea.log definition
CREATE TABLE `log` (
  `id_log` mediumint NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(4000) DEFAULT NULL COMMENT 'mensje',
  `traza` varchar(4000) DEFAULT NULL COMMENT 'traza de la excepción',
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'fecha del evento',
  `id_usuario` int DEFAULT NULL COMMENT 'id del usuario',
  `id_sesion` int DEFAULT NULL COMMENT 'identificador de la sesión autenticada',
  `nivel` int DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB AUTO_INCREMENT=641 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tabla para loguear errores';

-- atenea.metodo_importacion definition
CREATE TABLE `metodo_importacion` (
  `id_metodo_importacion` int NOT NULL AUTO_INCREMENT COMMENT 'id de la tabla',
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Nombre a mostrar en el submenú de métodos de importación',
  `descripcion` varchar(500) DEFAULT NULL COMMENT 'descripción del método de importación',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'redirección a realizar cuando se pulsa sobre el elemento',
  `orden` decimal(10,0) NOT NULL COMMENT 'orden a mostrar en el menú',
  PRIMARY KEY (`id_metodo_importacion`),
  UNIQUE KEY `metodo_importacion_unique` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tabla con los métodos de importación del sistema';

-- atenea.nivel definition
CREATE TABLE `nivel` (
  `id_nivel` int NOT NULL AUTO_INCREMENT COMMENT 'id del nivel',
  `nivel` int NOT NULL COMMENT 'nivel',
  `inicio_nivel` decimal(10,0) NOT NULL COMMENT 'inicio del intervalo',
  `fin_nivel` decimal(10,0) NOT NULL COMMENT 'fin del intervalo',
  PRIMARY KEY (`id_nivel`),
  UNIQUE KEY `nivel_unique` (`nivel`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='nivel del usuario';

-- atenea.pregunta definition
CREATE TABLE `pregunta` (
  `id_pregunta` int NOT NULL AUTO_INCREMENT COMMENT 'primary key de la tabla',
  `enunciado` varchar(4000) NOT NULL COMMENT 'enunciado de la pregunta, que puede ser html',
  `fuente_pregunta` varchar(4000) DEFAULT NULL,
  `fecha_insercion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'fecha en la que se insertó la pregunta',
  `comentarios` varchar(4000) DEFAULT NULL,
  `activada` tinyint NOT NULL DEFAULT '1' COMMENT 'indica si la pregunta hay que tenerla en cuenta o está desactivada',
  PRIMARY KEY (`id_pregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb3;

-- atenea.pregunta_categoria definition
CREATE TABLE `pregunta_categoria` (
  `id_pregunta` int NOT NULL COMMENT 'identificador de la pregunta',
  `id_categoria` int NOT NULL COMMENT 'identificador de la categoría',
  PRIMARY KEY (`id_pregunta`,`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='asocia categorías a una pregunta';

-- atenea.procedimiento definition
CREATE TABLE `procedimiento` (
  `id_procedimiento` int NOT NULL AUTO_INCREMENT COMMENT 'pk de la tabla',
  `nombre` varchar(255) NOT NULL COMMENT 'nombre del procedimiento',
  `descripcion` varchar(4000) NOT NULL COMMENT 'descripción del procedimiento',
  `orden` int NOT NULL COMMENT 'orden en el que van a aparecer',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'destino de la llamada',
  PRIMARY KEY (`id_procedimiento`),
  UNIQUE KEY `orden_UNIQUE` (`orden`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- atenea.propiedades definition
CREATE TABLE `propiedades` (
  `clave` varchar(255) NOT NULL COMMENT 'clave de la propiedad',
  `valor` varchar(100) NOT NULL COMMENT 'valor de la propiedad',
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='propiedades de la aplicación';

-- atenea.respuesta definition
CREATE TABLE `respuesta` (
  `id_respuesta` int NOT NULL AUTO_INCREMENT COMMENT 'pk de la respuesta',
  `id_pregunta` int NOT NULL COMMENT 'pregunta asociada a la respuesta',
  `respuesta` varchar(4000) NOT NULL COMMENT 'respuesta a mostrar',
  `correcta` tinyint NOT NULL DEFAULT '0' COMMENT 'indica si la respuesta es correcta o incorrecta',
  `feedback` varchar(4000) DEFAULT NULL COMMENT 'en caso de fallar la pregunta, se le muestra este texto como orientación al usuario',
  `fecha_insercion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'fecha en la que se introdujo esta respuesta al sistema',
  PRIMARY KEY (`id_respuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=3866 DEFAULT CHARSET=utf8mb3 COMMENT='Tabla en la que se gestionan las respuestas de los tests';

-- atenea.simulacion_examen definition
CREATE TABLE `simulacion_examen` (
  `id_simulacion_examen` int NOT NULL AUTO_INCREMENT COMMENT 'primary key de la tabla',
  `id_configuracion_examen` int NOT NULL COMMENT 'id de la configuración de examen que tiene la simulación realizada',
  `tiempo_utilizado` double DEFAULT NULL COMMENT 'tiempo que se ha utilizado para realizar la simulación en segundos',
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'fecha de simulación',
  PRIMARY KEY (`id_simulacion_examen`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='se almacenan las simulaciones de examen realizadas';

-- atenea.simulacion_examen_detalle definition

CREATE TABLE `simulacion_examen_detalle` (
  `id_simulacion_examen_detalle` int NOT NULL AUTO_INCREMENT COMMENT 'se almacenan los detalles de la simulación de examen realizada',
  `id_simulacion_examen` int NOT NULL COMMENT 'foreign key a la tabla simulacion examen',
  `id_respuesta` int NOT NULL COMMENT 'foreign key a la tabla respuesta con las respuestas mostradas en la simulación',
  `marcada` tinyint(1) NOT NULL COMMENT 'indica si se marcó esta opción durante la simulación',
  PRIMARY KEY (`id_simulacion_examen_detalle`)
) ENGINE=InnoDB AUTO_INCREMENT=25433 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- atenea.trofeo definition
CREATE TABLE `trofeo` (
  `id_trofeo` int NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `nombre` varchar(255) NOT NULL COMMENT 'nombre del trofeo',
  `descripcion` varchar(4000) DEFAULT NULL COMMENT 'descripción del trofeo',
  `porcentaje` int DEFAULT NULL COMMENT 'porcentaje de aciertos en preguntas para obtener el premio',
  `imagen` binary(1) DEFAULT NULL COMMENT 'imagen del trofeo a mostrar',
  PRIMARY KEY (`id_trofeo`),
  KEY `Trofeos_id_trofeo_IDX` (`id_trofeo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='trofeos que se pueden conseguir';

-- atenea.usuario definition
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL,
  `nombre_usuario` varchar(45) NOT NULL COMMENT 'nombre asociado al usuario, debe ser único en el sistema',
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario_UNIQUE` (`nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='tabla de usuarios';

-- cambiamos el delimitador para crear funciones
delimiter //

-- función que devuelve los días que lleva el usuario sin realizar un test
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getDiasSinRealizarSimulacros`() RETURNS int
    DETERMINISTIC
begin
	declare dias_sin_simulacro int;
	select date(now()) - date(fecha) into dias_sin_simulacro from simulacion_examen se order by fecha desc limit 1;
	return dias_sin_simulacro;
end//

-- función que devuelve el nivel actual del usuario
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getNivelActual`() RETURNS int
    DETERMINISTIC
begin
	DECLARE RESPUESTAS_CORRECTAS INT;
	DECLARE NIVEL_ACTUAL INT;
	select count(*) into RESPUESTAS_CORRECTAS from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.marcada = true and r.correcta = true;

	select nivel into NIVEL_ACTUAL from nivel where nivel.inicio_nivel  <= RESPUESTAS_CORRECTAS and fin_nivel >=RESPUESTAS_CORRECTAS;
    RETURN NIVEL_ACTUAL;
end//

-- función que devuelve el porcentaje de aciertos de una simulación
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getPorcentajeAciertos`(idSimulacion int) RETURNS double
    DETERMINISTIC
begin
	declare porcentaje double;
	select 
    (
    select count(*) from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.id_simulacion_examen = idSimulacion and r.correcta = true and sed.marcada = true
	) 
/
	(select count(*) from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.id_simulacion_examen = idSimulacion and r.correcta = true) * 100 as porcentaje 
	into porcentaje;
	return porcentaje;
end//

-- función que devuelve el número de respuestas correctas de una simulación de examen
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getRespuestasCorrectas`(idSimulacion int) RETURNS int
    DETERMINISTIC
begin
	declare respuestas_correctas int;
	select count(*)  into respuestas_correctas from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.marcada = true and r.correcta = true and sed.id_simulacion_examen = idSimulacion;
	return respuestas_correctas;
end//

-- función que devuelve el número de respuestas falladas de una simulación de examen
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getRespuestasFalladas`(idSimulacion int) RETURNS int
    DETERMINISTIC
begin
	declare respuestas_falladas int;
	select count(*) into respuestas_falladas from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.marcada = true and r.correcta = false and sed.id_simulacion_examen = idSimulacion;
	return respuestas_falladas;
end//

-- función que devuelve el número de respuestas sin contestar de una simulación de examen
CREATE DEFINER=`Atenea`@`%` FUNCTION `atenea`.`getRespuestasSinContestar`(idSimulacion int) RETURNS int
    DETERMINISTIC
begin
	declare respuestas_sin_contestar int;
	select count(distinct r.id_pregunta) into respuestas_sin_contestar from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where r.id_pregunta not in (
select distinct r.id_pregunta  from simulacion_examen_detalle sed 
	inner join respuesta r 
	on sed.id_respuesta = r.id_respuesta 
	where  sed.marcada = true  and sed.id_simulacion_examen = idSimulacion
	)
	and sed.id_simulacion_examen = idSimulacion;
	return respuestas_sin_contestar;
end//

-- volvemos a poner el delimitador de nuevo
delimiter ;

