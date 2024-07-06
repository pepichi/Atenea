/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author José Puerta Cardelles
 */
public class EnvioCorreoHelper {
    
    private EnvioCorreoHelper(){
        
    }

    private static final String PROPIEDAD_USUARIO = "configuracion.correo.usuario";
    private static final String PROPIEDAD_PASSWORD = "configuracion.correo.password";
    private static final String USUARIO = PropiedadesHelper.getPropiedad(PROPIEDAD_USUARIO);
    private static final String PASSWORD = PropiedadesHelper.getPropiedad(PROPIEDAD_PASSWORD);            
    
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USUARIO, PASSWORD);
            }
        });
        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(USUARIO));
            mensaje.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(destinatario)
            );
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport.send(mensaje);
        } catch (MessagingException e) {
            LogHelper.anotarExcepcionLog(e);
        }
    }
}
