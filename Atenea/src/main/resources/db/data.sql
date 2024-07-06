/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
/**
 * Author:  José Puerta Cardelles
 * Created: 5 jul 2024
 */
-- Métodos de importación disponibles
INSERT INTO metodo_importacion (nombre, descripcion, url, orden) VALUES('AIKEN', 'Formato de importación Aiken', '/Atenea/jsp/importacion/aiken.jsp', 1);
INSERT INTO metodo_importacion (nombre, descripcion, url, orden) VALUES('PDF', 'Fichero PDF con preguntas', '/Atenea/jsp/importacion/pdf.jsp', 2);
INSERT INTO metodo_importacion (nombre, descripcion, url, orden) VALUES('XML', 'Fichero XML con preguntas', '/Atenea/jsp/importacion/xml.jsp', 3);

-- Niveles disponibles
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(1, 0, 100);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(2, 101, 200);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(3, 201, 300);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(4, 301, 500);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(5, 501, 800);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(6, 801, 1300);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(7, 1301, 2100);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(8, 2101, 3400);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(9, 3401, 5500);
INSERT INTO nivel (nivel, inicio_nivel, fin_nivel) VALUES(10, 5501, 8900);

-- Entrada de menú disponibles
INSERT INTO procedimiento (nombre, descripcion, orden, url) VALUES('Realizar test', 'Lugar dónde puedes hacer una simulación de exámen', 1, '/Atenea/jsp/examen/simulacionExamen.jsp');
INSERT INTO procedimiento (nombre, descripcion, orden, url) VALUES('Gestión banco de pruebas', 'Lugar dónde puedes añadir, modificar, eliminar, categorizar tests', 2, '/Atenea/jsp/bancoPreguntas/gestionBancoPreguntas.jsp');
INSERT INTO procedimiento (nombre, descripcion, orden, url) VALUES('Gestión categorías', 'Lugar dónde puedes añadir, modificar, eliminar categoriás', 3, '/Atenea/jsp/categorias/gestionCategorias.jsp');
INSERT INTO procedimiento (nombre, descripcion, orden, url) VALUES('Configuración exámenes', 'Lugar dónde se puede añadir, modificar, eliminar configuración de un examen.', 4, '/Atenea/jsp/examen/gestionConfiguracionExamen.jsp');
INSERT INTO procedimiento (nombre, descripcion, orden, url) VALUES('Importación de preguntas', 'Lugar dónde puedes importar las preguntas en diferentes formatos', 5, '/Atenea/jsp/importacion/metodoImportacion.jsp');

-- Propiedades del sistema
INSERT INTO propiedades (clave, valor) VALUES('configuracion.correo.destinatario', 'correoUsuarioAplicacion@gmail.com');
INSERT INTO propiedades (clave, valor) VALUES('configuracion.correo.password', 'wvsq znsa ycrq avhc');
INSERT INTO propiedades (clave, valor) VALUES('configuracion.correo.usuario', 'atenea.unir@gmail.com');
INSERT INTO propiedades (clave, valor) VALUES('configuracion.recordatorio.dias', '0');
INSERT INTO propiedades (clave, valor) VALUES('gamificacion.nivel', '5');
INSERT INTO propiedades (clave, valor) VALUES('gamificacion.puntos', '0');
INSERT INTO propiedades (clave, valor) VALUES('gamificacion.trofeo', '2');

-- Trofeos disponibles
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Novato', 'Más de un 50% de aciertos', 50, NULL);
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Principiante', 'Más de un 60% de aciertos', 60, NULL);
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Competente', 'Más de un 70% de aciertos', 70, NULL);
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Avanzado', 'Más de un 80% de aciertos', 80, NULL);
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Experto', 'Más de un 90% de aciertos', 90, NULL);
INSERT INTO trofeo (nombre, descripcion, porcentaje, imagen) VALUES('Maestro', 'Más de un 95% de aciertos', 95, NULL);
