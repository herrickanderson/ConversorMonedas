import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {

    // Constructor
    public ConsultaAPI() {
    }

    // Método para realizar la consulta a la API
    public DatosMoneda consultaAPI(DatosMoneda datos) {
        // Construimos la dirección de la API
        String direccionAPI = "https://v6.exchangerate-api.com/v6/b1173b38c8c41bc6314236b8/latest/" + datos.getMonedaOrigen();

        // Creamos un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Creamos una solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccionAPI))
                .build();

        // Enviamos la solicitud y obtenemos la respuesta
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Procesamos la respuesta de la API
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
        double conversionRate = rates.get(datos.getMonedaDestino()).getAsDouble();

        // Actualizamos 'datos' con la información de conversión
        datos.setValor(conversionRate);

        return datos;
    }

}
