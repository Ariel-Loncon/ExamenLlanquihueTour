package Data.GestorArchivotxt;
import Model.*;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.ServivioTuristico;
import Model.Tours.TourGastronomico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los datos de Servicios Turísticos guardados en el archivo de texto.
 */
public class GestorServicios {

    public ArrayList<ServivioTuristico> readServiciosText(String filePath) {
        // Lista donde se almacenará la información
        ArrayList<ServivioTuristico> listaServicios = new ArrayList<>();

        // Validación y creación del archivo si no existe usando la clase GestorArchivo
        if (!GestorArchivo.Archivo(filePath)) {
            System.out.println("El archivo no existe o no se puede acceder: " + filePath);
            return listaServicios;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                // Validación de cantidad de campos (ajustar según el constructor, aquí se asume 4 campos mínimo)
                if (data.length < 4) {
                    System.out.println("Error en línea " + lineNumber + ": Estructura incorrecta de Servicios (Se esperaban 4 campos, se encontraron " + data.length + ").");
                    continue;
                }

                String tipo = data[0].trim();

                try {
                    switch (tipo) {
                        case "TourGastronomico":
                            listaServicios.add(new TourGastronomico(data[1].trim(), Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim())));
                            break;
                        case "PaseoLacustre":
                            listaServicios.add(new PaseoLacustre(data[1].trim(), Integer.parseInt(data[2].trim()), data[3].trim()));
                            break;
                        case "ExcursionCultural":
                            listaServicios.add(new RutaCultural(data[1].trim(), Integer.parseInt(data[2].trim()), data[3].trim()));
                            break;
                        default:
                            System.out.println("Línea " + lineNumber + ": Tipo de servicio desconocido: " + tipo);
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Error de formato numérico en línea " + lineNumber + ": " + nfe.getMessage());
                } catch (Exception e) {
                    System.out.println("Error procesando los datos en línea " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de servicios: " + e.getMessage());
        }

        return listaServicios;
    }

    public boolean guardarServicioText(String tipo, String nombre, int duracion, String campoExtra, String filePath) {
        if (!GestorArchivo.Archivo(filePath)) return false;
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(filePath, true))) {

            String linea = String.join(",", tipo, nombre, String.valueOf(duracion), campoExtra);
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir servicio: " + e.getMessage());
            return false;
        }
    }
}
