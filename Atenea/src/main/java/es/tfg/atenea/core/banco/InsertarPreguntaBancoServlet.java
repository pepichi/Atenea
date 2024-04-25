package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.constants.Errores;
import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.LogHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "InsertarPreguntaBancoServlet", urlPatterns = {"/Servlet/InsertarPreguntaBancoServlet"})
public class InsertarPreguntaBancoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jsonBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        try (Connection conexion = DataBaseHelper.getConexionTransacional()) {
            BigDecimal idPregunta = InsertarPreguntaBancoHelper.insertarEnunciado(conexion, new Pregunta((String) jsonObject.get(Parametros.ENUNCIADO), null, null));
            InsertarPreguntaBancoHelper.insertarCategorias(conexion, idPregunta, getIdsCategoriasSeleccionadas(jsonObject));
            InsertarPreguntaBancoHelper.insertarRespuestas(conexion, idPregunta, getRespuestas(jsonObject));
            conexion.commit();
        } catch (Exception ex) {
            LogHelper.anotarExcepcionLog("Error al parsear la llamada a guardar la pregunta con sus respuestas.", ex);
            ServletHelper.responseObjectSafe(Errores.ERROR_GENERICO.getMensajeUsuario(), response);
        }
    }

    private List<BigDecimal> getIdsCategoriasSeleccionadas(JSONObject jsonObject) {
        JSONArray jsonArrayCategorias = jsonObject.getJSONArray(Parametros.CATEGORIAS_SELECCIONADAS);
        List<BigDecimal> idCategoriasSeleccionadas = new ArrayList<>();
        jsonArrayCategorias.forEach(item -> {
            JSONObject jsonCategoria = (JSONObject) item;
            idCategoriasSeleccionadas.add(jsonCategoria.getBigDecimal("idCategoria"));
        });
        return idCategoriasSeleccionadas;
    }

    private List<Respuesta> getRespuestas(JSONObject jsonObject) {
        JSONArray jsonArrayCategorias = jsonObject.getJSONArray(Parametros.RESPUESTAS);
        List<Respuesta> respuestas = new ArrayList<>();
        jsonArrayCategorias.forEach(item -> {
            JSONObject jsonRespuesta = (JSONObject) item;
            respuestas.add(new Respuesta(jsonRespuesta.getString("respuesta"), jsonRespuesta.getString("feedback"), jsonRespuesta.getBoolean("esCorrecta")));
        });
        return respuestas;
    }
}
