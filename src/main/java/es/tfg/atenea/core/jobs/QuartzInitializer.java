/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.jobs;

/**
 *
 * @author José Puerta Cardelles
 */

import es.tfg.atenea.core.helper.LogHelper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.InputStream;
import java.util.Properties;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class QuartzInitializer implements ServletContextListener {

    private Scheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Inicializar el scheduler desde las propiedades
            StdSchedulerFactory factory = new StdSchedulerFactory();
            factory.initialize(getClass().getClassLoader().getResourceAsStream("quartz.properties"));
            scheduler = factory.getScheduler();

            // Cargar la configuración de jobs
            InputStream input = getClass().getClassLoader().getResourceAsStream("quartz_jobs.properties");
            Properties jobProperties = new Properties();
            jobProperties.load(input);

            // Leer la configuración del job Recordatorio
            String jobClassName = jobProperties.getProperty("job.recordatorio.class");
            String jobName = jobProperties.getProperty("job.recordatorio.name");
            String jobGroup = jobProperties.getProperty("job.recordatorio.group");
            String cronExpression = jobProperties.getProperty("job.recordatorio.cronExpression");

            // Crear el trabajo
            Class<?> jobClass = Class.forName(jobClassName);
            JobDetail job = JobBuilder.newJob((Class<? extends Job>) jobClass)
                    .withIdentity(jobName, jobGroup)
                    .build();

            // Crear el trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName + "Trigger", jobGroup)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();

            // Programar el trabajo
            scheduler.scheduleJob(job, trigger);

            // Iniciar el scheduler
            scheduler.start();

        } catch (Exception e) {
            LogHelper.anotarExcepcionLog(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (scheduler != null) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            LogHelper.anotarExcepcionLog(e);
        }
    }
}

   
