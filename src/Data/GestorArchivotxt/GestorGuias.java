package Data.GestorArchivotxt;
import Model.*;
import Util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los datos de Guía guardada en el archivo de texto mediante Arraylist.
 */

public class GestorGuias {
    public ArrayList<Guia> readGuiasText(String filePath) {
        //lista donde se almacenara la información
        ArrayList<Guia> guiasList = new ArrayList<>();
        //Se abre el archivo para la lectura

        if (!GestorArchivo.Archivo(filePath)) {
            System.out.println("El archivo no existe o no se puede acceder: " + filePath);
            return guiasList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty())
                    continue;

                //Se separa la línea con ";"
                String[] data = line.split(";");

                if (data.length != 7){
                    System.out.println("Error en línea " + lineNumber + ": Estructura incorrecta de Guias (Se esperaban 3 campos, se encontraron " + data.length + ").");
                    continue;
                }

                try {
                    //Se transforman los datos al tipo correspondiente
                    ValidadorRut guiaRut = new ValidadorRut(data[0].trim());
                    String nombre = data[1].trim();
                    ValidadorEmail guiaEmail = new ValidadorEmail(data[2].trim());
                    // Ejemplo si en data[3] viene "Calle, Numero, Comuna, Region"
                    String[] partesDireccion = data[3].trim().split(",");

                    Direccion guiaDireccion;
                    if (partesDireccion.length == 4) {
                        guiaDireccion = new Direccion(
                                partesDireccion[0].trim(),
                                partesDireccion[1].trim(),
                                partesDireccion[2].trim(),
                                partesDireccion[3].trim()
                        );
                    } else {
                        // Respaldo en caso de que falten campos
                        guiaDireccion = new Direccion(data[3].trim(), "", "", "");
                    }

                    ValidadorTelefono guiaTelefono = new ValidadorTelefono(data[4].trim());
                    String idioma = data[5].trim();
                    String especialidad = data[6].trim();

                    Guia newGuia = new Guia(guiaRut,nombre,guiaEmail,guiaDireccion,guiaTelefono,idioma,especialidad);
                    guiasList.add(newGuia);

                } catch (IllegalArgumentException ex) {
                    System.out.println("Error de validación de Guias en linea: " + lineNumber + ": "+ ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de guías: "+ e.getMessage());
        }
        return guiasList;
    }

    public boolean guardarGuiaText(Guia guia, String filePath) {
        if (!GestorArchivo.Archivo(filePath)) return false;
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(filePath, true))) {
            String direccionFormateada = String.format("%s,%s,%s,%s",
                    guia.getDireccion().getCalle(), guia.getDireccion().getNumero(),
                    guia.getDireccion().getComuna(), guia.getDireccion().getRegion());

            String linea = String.join(";",
                    guia.getRut().getValidadorRut(), guia.getNombre(), guia.getEmail().getNumber(),
                    direccionFormateada, guia.getNumeroTelefono().getValidadorTelefono(),
                    guia.getIdioma(), guia.getEspecialidad());
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir guía: " + e.getMessage());
            return false;
        }
    }
}
