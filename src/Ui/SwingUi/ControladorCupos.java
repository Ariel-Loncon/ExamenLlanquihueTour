package Ui.SwingUi;

import Data.GestorArchivotxt.GestorCliente;
import Model.Cliente;
import java.util.ArrayList;

public class ControladorCupos {
    public static final int LIMITE_MAXIMO = 10;

    public static int getCupos(String tour) {
        GestorCliente gc = new GestorCliente();
        ArrayList<Cliente> listaClientes = gc.readClienteText("resources/Cliente.txt");

        int contador = 0;
        for (Cliente c : listaClientes) {
            // Comparamos directamente usando el nuevo Getter que creaste
            if (c.getServicio() != null && c.getServicio().equalsIgnoreCase(tour)) {
                contador++;
            }
        }
        return contador;
    }

    public static boolean registrarCupo(String tour) {
        return getCupos(tour) < LIMITE_MAXIMO;
    }
    //asigna un número de orden al cliente, evitando repetirlo
    public static String generarSiguienteOrden() {
        GestorCliente gc = new GestorCliente();
        ArrayList<Cliente> listaClientes = gc.readClienteText("resources/Cliente.txt");

        int maxNumeroOrden = 0; // Guardará el número secuencial más alto encontrado

        for (Cliente c : listaClientes) {
            String ordenStr = c.getOrdenCompra(); // Ej: "OC-5" o "OC-12"
            if (ordenStr != null && ordenStr.startsWith("OC-")) {
                try {
                    // Extraemos solo el número después del "OC-"
                    int numeroOrden = Integer.parseInt(ordenStr.substring(3).trim());

                    // Si este número es mayor que el máximo actual, lo actualizamos
                    if (numeroOrden > maxNumeroOrden) {
                        maxNumeroOrden = numeroOrden;
                    }
                } catch (NumberFormatException e) {
                    // Si por alguna razón la cadena no se puede parsear, se ignora esa línea
                    System.err.println("Error al parsear el número de orden: " + ordenStr);
                }
            }
        }
        // El siguiente número será el más alto del historial + 1
        int siguienteNumero = maxNumeroOrden + 1;
        return "OC-" + siguienteNumero;
    }
}
