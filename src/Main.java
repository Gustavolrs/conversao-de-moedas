import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ServicoDeApi apiService = new ServicoDeApi();
            CurrencyData currencyData = apiService.getExchangeRates();
            Map<String, Double> rates = currencyData.getConversionRates();
            CurrencyConverter converter = new CurrencyConverter(rates);

            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                imprimirMenu();
                option = scanner.nextInt();
                processarOpcao(option, converter, scanner);
            } while (option != 7);

            scanner.close();
            System.out.println("\nPrograma finalizado. Obrigado por usar o Conversor de Moedas!");
        } catch (Exception e) {
            System.out.println("Erro ao obter dados da API: " + e.getMessage());
        }
    }

    private static void imprimirMenu() {
        System.out.println("\n============================");
        System.out.println("       CONVERSOR DE MOEDAS       ");
        System.out.println("============================");
        System.out.println("Escolha uma opção de conversão:");
        System.out.println("1) Dólar (USD) => Peso Argentino (ARS)");
        System.out.println("2) Peso Argentino (ARS) => Dólar (USD)");
        System.out.println("3) Dólar (USD) => Euro (EUR)");
        System.out.println("4) Euro (EUR) => Dólar (USD)");
        System.out.println("5) Dólar (USD) => Iene Japonês (JPY)");
        System.out.println("6) Iene Japonês (JPY) => Dólar (USD)");
        System.out.println("7) Sair");
        System.out.println("============================");
        System.out.print("Digite a sua opção: ");
    }

    private static void processarOpcao(int option, CurrencyConverter converter, Scanner scanner) {
        switch (option) {
            case 1:
                realizarConversao(converter, "USD", "ARS", scanner);
                break;
            case 2:
                realizarConversao(converter, "ARS", "USD", scanner);
                break;
            case 3:
                realizarConversao(converter, "USD", "EUR", scanner);
                break;
            case 4:
                realizarConversao(converter, "EUR", "USD", scanner);
                break;
            case 5:
                realizarConversao(converter, "USD", "JPY", scanner);
                break;
            case 6:
                realizarConversao(converter, "JPY", "USD", scanner);
                break;
            case 7:
                System.out.println("\nSaindo do programa...");
                break;
            default:
                System.out.println("\nOpção inválida! Por favor, selecione uma das opções disponíveis.");
        }
    }

    private static void realizarConversao(CurrencyConverter converter, String fromCurrency, String toCurrency, Scanner scanner) {
        System.out.print("Digite o valor a ser convertido: ");
        double amount = scanner.nextDouble();
        double result = converter.convert(fromCurrency, toCurrency, amount);
        System.out.printf("Resultado: %.2f %s\n", result, toCurrency);
    }
}

