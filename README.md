# Atenea
## _El lugar dónde tener todos tus test juntos y poder probar tu sabiduría_

Aplicación realizada como TFE para los estudios de "Curso de adaptación al grado en Ingeniería Informática (Plan 2014)" de la Universidad Internacional de la Rioja (UNIR).   
## Resumen
Aplicación para gestión de preguntas tipo tests y simulacros de examen  

## Configuración de Variables de Entorno
El proyecto requiere ciertas variables de entorno para funcionar correctamente. Añade las siguientes variables de entorno al sistema operativo dónde vayas a ejecutar esta aplicación:  
-`DB_JDBC_URL`: URL del host dónde está la base de datos  
-`DB_PASSWORD`: Contraseña de acceso a la base de datos  
-`DB_USERNAME`: Nombre del usuario de la base de datos  

Asegúrate de modificar los valores según tu entorno.  
## Base de datos
El proyecto necesita una base de datos MySql. Exiten dos scripts que son necesarios ejecutar antes de comenzar a utilizar la aplicación:  
-`Atenea/src/main/resources/db/schema.sql`: Este script contiene las sentencias DDL para crear los objetos necesarios para la aplicacíon.  
-`Atenea/src/main/resources/db/data.sql`: Este script tiene las sentencias DML para insertar los valores iniciales de uso de la aplicación. Conviene revisar ya que tiene algunos elementos configurables como el correo de notificación de usuario al que realizará los avisos.  

## Licencia
Copyright 2024 José Puerta Cardelles  
Permiso otorgado bajo la Licencia MIT  

