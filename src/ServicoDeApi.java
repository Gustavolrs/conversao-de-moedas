import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ServicoDeApi {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/b4894db8127b127f00d015da/latest/USD";
    private HttpClient client;

    public ServicoDeApi() {
        this.client = HttpClient.newHttpClient();
    }

    public CurrencyData getExchangeRates() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        Gson gson = new Gson();
        return gson.fromJson(responseBody, CurrencyData.class);
    }
}
