package Data.GestorArchivotxt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar la eliminación de líneas de texto de los distintos archivos de texto vinculado
 */

public class GestorEliminar {
    //para archivo de Guias
    public static boolean eliminarGuia(String rutAEliminar) {
        String ruta = "resources/Guias.txt";
        return eliminarLinea(ruta, ";", (campos) -> {
            // El RUT del Guía está en la posición 0
            String rutActual = campos[0].trim();
            return rutActual.equalsIgnoreCase(rutAEliminar.trim());
        });
    }
    //Para archivo de Proveedores
    public static boolean eliminarOperador(String nombreAEliminar) {
        String ruta = "resources/Proveedores.txt";
        return eliminarLinea(ruta, ";", (campos) -> {
            // El Nombre del Operador está en la posición 0
            String nombreActual = campos[0].trim();
            return nombreActual.equalsIgnoreCase(nombreAEliminar.trim());
        });
    }
    //Para archivo de Clientes
    public static boolean eliminarCliente(String ordenEliminar) {
        String ruta = "resources/Cliente.txt";

        return eliminarLinea(ruta, ";", (campos) -> {
            if (campos.length == 0) return false;

            String numeroOrdenA = campos[5].trim();

            return numeroOrdenA.equalsIgnoreCase(ordenEliminar.trim());
        });
    }
    //Para archivo de Servicios
    public static boolean eliminarServicio(String tipo, String nombreServicioAEliminar) {
        String ruta = "resources/Servicios.txt";

        final String tipoCorregido = tipo.replace("Excurcion", "Excursion");

        return eliminarLinea(ruta, ",", (campos) -> {
            if (campos.length < 2) return false;
            String tipoActual = campos[0].trim();
            String nombreActual = campos[1].trim();

            return tipoActual.equalsIgnoreCase(tipoCorregido) &&
                    nombreActual.equalsIgnoreCase(nombreServicioAEliminar.trim());
        });
    }

    private static boolean eliminarLinea(String filePath, String separador, CriterioBusqueda criterio) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        List<String> lineasAConservar = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] campos = line.split(separador);
                if (criterio.debeEliminar(campos)) {
                    encontrado = true;
                } else {
                    lineasAConservar.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo para eliminar: " + e.getMessage());
            return false;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
                for (String linea : lineasAConservar) {
                    bw.write(linea);
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                System.err.println("Error al escribir archivo tras eliminar: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    @FunctionalInterface
    private interface CriterioBusqueda {
        boolean debeEliminar(String[] campos);
    }
}