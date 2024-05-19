/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import jakarta.servlet.http.Part;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class FileHelper {

    private FileHelper() {

    }

    public static String getNombreFichero(Part part) {
        String disposicionContenido = part.getHeader("content-disposition");
        List<String> elementos = Arrays.asList(disposicionContenido.split(";"));
        String elementoNombreFichero = elementos.stream().filter(x -> x.trim().startsWith("filename")).findFirst().orElse(null);
        if(elementoNombreFichero == null){
            return null;
        }
        return elementoNombreFichero.substring(elementoNombreFichero.indexOf('=') + 1).trim().replace("\"", "");
    }
    


}
