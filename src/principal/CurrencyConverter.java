package principal;
import com.google.gson.Gson;
import java.util.Map;

public class CurrencyConverter {
    private final APIClient apiClient;
    private final Gson gson;
    private Map<String, Double> exchangeRates;
    private String baseCurrency;

    public CurrencyConverter() {
        this.apiClient = new APIClient();
        this.gson = new Gson();
        this.baseCurrency = "USD";
        actualizarTasas();
    }

    public void actualizarTasas() {
        try {
            String jsonResponse = apiClient.getExchangeRates(baseCurrency);
            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

            if (!"success".equalsIgnoreCase(response.getResult())) {
                throw new RuntimeException("Error en la API: " + response.getResult());
            }

            this.exchangeRates = response.getConversionRates();

            // Verificar que tenemos las tasas necesarias
            String[] monedasRequeridas = {"ARS", "BRL", "COP", "EUR", "MXN", "GBP", "CLP"};
            for (String moneda : monedasRequeridas) {
                if (!exchangeRates.containsKey(moneda)) {
                    throw new RuntimeException("La API no devolvió la tasa para: " + moneda);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("No se pudieron obtener las tasas de cambio: " + e.getMessage());
        }
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }

        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Una de las monedas no está soportada");
        }

        try {
            double amountInUsd = fromCurrency.equals("USD")
                    ? amount
                    : amount / exchangeRates.get(fromCurrency);

            return toCurrency.equals("USD")
                    ? amountInUsd
                    : amountInUsd * exchangeRates.get(toCurrency);
        } catch (Exception e) {
            throw new RuntimeException("Error en la conversión: " + e.getMessage());
        }
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }
}