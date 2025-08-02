package principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Conversion {
    private static final CurrencyConverter converter = new CurrencyConverter();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> historial = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        mostrarBienvenida();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = obtenerOpcionValida(1, 4);

            switch (opcion) {
                case 1 -> realizarConversion();
                case 2 -> mostrarHistorial();
                case 3 -> actualizarTasas();
                case 4 -> mostrarDespedida();
            }

            if (opcion != 4) pausaParaContinuar();
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarBienvenida() {
        System.out.println("\n***********************************");
        System.out.println("  BIENVENIDO AL CONVERSOR DE MONEDAS");
        System.out.println("***********************************");
        System.out.println("Tasas de cambio actualizadas al: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("***********************************\n");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("""
                MENÚ PRINCIPAL:
                1) Realizar conversión
                2) Ver historial de conversiones
                3) Actualizar tasas de cambio
                4) Salir
                """);
    }

    private static void realizarConversion() {
        mostrarMenuMonedas();
        int opcion = obtenerOpcionValida(1, 12);

        if (opcion == 12) return;

        String[] monedas = obtenerMonedasConversion(opcion);
        double cantidad = obtenerCantidad();
        double resultado = converter.convert(cantidad, monedas[0], monedas[1]);

        String registro = crearRegistroConversion(cantidad, monedas[0], resultado, monedas[1]);
        historial.add(registro);

        mostrarResultado(registro);
    }

    private static void mostrarMenuMonedas() {
        System.out.println("""
                ***********************************
                SELECCIONE EL TIPO DE CONVERSIÓN:
                
                1) USD => ARS (Dólar => Peso argentino)
                2) ARS => USD (Peso argentino => Dólar)
                3) USD => BRL (Dólar => Real brasileño)
                4) BRL => USD (Real brasileño => Dólar)
                5) USD => COP (Dólar => Peso colombiano)
                6) COP => USD (Peso colombiano => Dólar)
                7) USD => EUR (Dólar => Euro)
                8) EUR => USD (Euro => Dólar)
                9) USD => MXN (Dólar => Peso mexicano)
                10) MXN => USD (Peso mexicano => Dólar)
                11) USD => CLP (Dólar => Peso chileno)
                12) Volver al menú principal
                
                ***********************************""");
    }

    private static String[] obtenerMonedasConversion(int opcion) {
        return switch (opcion) {
            case 1 -> new String[]{"USD", "ARS"};
            case 2 -> new String[]{"ARS", "USD"};
            case 3 -> new String[]{"USD", "BRL"};
            case 4 -> new String[]{"BRL", "USD"};
            case 5 -> new String[]{"USD", "COP"};
            case 6 -> new String[]{"COP", "USD"};
            case 7 -> new String[]{"USD", "EUR"};
            case 8 -> new String[]{"EUR", "USD"};
            case 9 -> new String[]{"USD", "MXN"};
            case 10 -> new String[]{"MXN", "USD"};
            case 11 -> new String[]{"USD", "CLP"};
            default -> throw new IllegalArgumentException("Opción no válida");
        };
    }

    private static int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                System.out.print("Elija una opción (" + min + "-" + max + "): ");
                int opcion = scanner.nextInt();
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.println("Por favor, ingrese un número entre " + min + " y " + max);
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.next(); // Limpiar el buffer
            }
        }
    }

    private static double obtenerCantidad() {
        while (true) {
            try {
                System.out.print("\nIngrese la cantidad a convertir: ");
                double cantidad = scanner.nextDouble();
                if (cantidad > 0) {
                    return cantidad;
                }
                System.out.println("La cantidad debe ser mayor que cero.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next(); // Limpiar el buffer
            }
        }
    }

    private static String crearRegistroConversion(double cantidad, String from,
                                                  double resultado, String to) {
        String timestamp = LocalDateTime.now().format(formatter);
        return String.format("[%s] %.2f %s = %.2f %s",
                timestamp, cantidad, getNombreMoneda(from), resultado, getNombreMoneda(to));
    }

    private static void mostrarResultado(String registro) {
        System.out.println("\n***********************************");
        System.out.println("RESULTADO DE LA CONVERSIÓN:");
        System.out.println(registro);
        System.out.println("***********************************");
    }

    private static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("\nNo hay conversiones en el historial.\n");
            return;
        }

        System.out.println("\n***********************************");
        System.out.println("HISTORIAL DE CONVERSIONES (" + historial.size() + "):");
        System.out.println("***********************************");

        for (int i = 0; i < historial.size(); i++) {
            System.out.printf("%d. %s%n", i+1, historial.get(i));
        }
    }

    private static void actualizarTasas() {
        System.out.println("\nActualizando tasas de cambio...");
        try {
            converter.actualizarTasas();
            System.out.println("¡Tasas actualizadas correctamente!");
            System.out.println("Última actualización: " + LocalDateTime.now().format(formatter));
        } catch (Exception e) {
            System.out.println("Error al actualizar tasas: " + e.getMessage());
        }
    }

    private static void pausaParaContinuar() {
        System.out.print("\nPresione Enter para continuar...");
        try {
            System.in.read();
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            // No hacer nada si hay error
        }
    }

    private static void mostrarDespedida() {
        System.out.println("\n***********************************");
        System.out.println("  GRACIAS POR USAR EL CONVERSOR");
        System.out.println("  Total de conversiones realizadas: " + historial.size());
        System.out.println("***********************************");
    }

    private static String getNombreMoneda(String codigo) {
        return switch (codigo) {
            case "USD" -> "Dólares";
            case "ARS" -> "Pesos argentinos";
            case "BRL" -> "Reales brasileños";
            case "COP" -> "Pesos colombianos";
            case "EUR" -> "Euros";
            case "MXN" -> "Pesos mexicanos";
            case "CLP" -> "Pesos chilenos";
            default -> codigo;
        };
    }
}