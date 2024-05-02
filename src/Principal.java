import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaAPI consultaAPI = new ConsultaAPI();

        while (true) {
            System.out.println("****************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("1) Dólar >> Peso argentino");
            System.out.println("2) Peso argentino >> Dólar");
            System.out.println("3) Dólar >> Real brasileño");
            System.out.println("4) Real brasileño >> Dólar");
            System.out.println("5) Dólar >> Peso colombiano");
            System.out.println("6) Peso colombiano >> Dólar");
            System.out.println("7) Salir");
            System.out.println("****************************************");

            System.out.print("\nElija una opción válida: ");
            int opcion = teclado.nextInt();

            if (opcion == 7) {
                break; // Salir del bucle principal y terminar el programa
            }

            if (opcion < 1 || opcion > 7) {
                continue; // Volver al inicio del bucle si la opción no es válida
            }

            System.out.print("Ingrese el valor que deseas convertir: ");
            double valor = teclado.nextDouble();

            DatosMoneda datos = null;
            switch (opcion) {
                case 1:
                    datos = new DatosMoneda("USD", "ARS", valor);
                    break;
                case 2:
                    datos = new DatosMoneda("ARS", "USD", valor);
                    break;
                case 3:
                    datos = new DatosMoneda("USD", "BRL", valor);
                    break;
                case 4:
                    datos = new DatosMoneda("BRL", "USD", valor);
                    break;
                case 5:
                    datos = new DatosMoneda("USD", "COP", valor);
                    break;
                case 6:
                    datos = new DatosMoneda("COP", "USD", valor);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }

            datos = consultaAPI.consultaAPI(datos);
            System.out.println("El valor " + valor + " [" + datos.getMonedaOrigen() + "] corresponde al valor final de =>>> " + datos.getValor() + " [" + datos.getMonedaDestino() + "]");
        }
    }
}
