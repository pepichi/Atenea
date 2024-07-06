/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.jobs;

import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.EnvioCorreoHelper;
import es.tfg.atenea.core.helper.LogHelper;
import es.tfg.atenea.core.helper.PropiedadesHelper;
import java.sql.Connection;
import java.sql.SQLException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author José Puerta Cardelles
 */
public class Recordatorio implements Job{
    
    private static final String PROPIEDAD_DESTINATARIO = "configuracion.correo.destinatario";
    private static final String PROPIEDAD_DIAS = "configuracion.recordatorio.dias";
    private static final String ASUNTO_RECORDATORIO = "Continúa con tu preparación en Atenea";
    private static final String CUERPO_ASUNTO = "Llevas %s días sin realizar un test. Recuerda que la repetición es una parte muy importante de la preparación";
    private static final String DESTINATARIO = PropiedadesHelper.getPropiedad(PROPIEDAD_DESTINATARIO);
    private static final int DIAS = Integer.parseInt(PropiedadesHelper.getPropiedad(PROPIEDAD_DIAS));

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException{
        try(Connection conexion = DataBaseHelper.getConexionNoTransacional()){
            int diasSinSimulacros = RecordatorioHelper.getDiasSinSimulacros(conexion);
            if(diasSinSimulacros >= DIAS){
                EnvioCorreoHelper.enviarCorreo(DESTINATARIO, ASUNTO_RECORDATORIO, String.format(CUERPO_ASUNTO, diasSinSimulacros));
            }
        }catch(SQLException ex){
            LogHelper.anotarExcepcionLog(ex);
        }
    }  
}
