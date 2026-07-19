package Data.GestorArchivotxt;

import Model.*;
import Util.ValidadorTelefono;
import Util.ValidadorRut;
import Util.ValidadorEmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los datos de los proveedores guardada en el archivo de texto mediante Arraylist.
 */

public class GestorProveedor {
    public ArrayList<Proveedor> readProveedoresText(String filePath) {
        //lista donde se almacenara la información
        ArrayList<Proveedor> ProveedorList = new ArrayList<>();
        //Se abre el archivo para la lectura

        if (!GestorArchivo.Archivo(filePath)) {
            System.out.println("El archivo no existe o no se puede acceder: " + filePath);
            return ProveedorList;
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

                if (data.length != 6){
                    System.out.println("Error en línea " + lineNumber + ": Estructura incorrecta de Guias (Se esperaban 3 campos, se encontraron " + data.length + ").");
                    continue;
                }

                try {
                    //Se transforman los datos al tipo correspondiente
                    ValidadorRut proveedorRut = new ValidadorRut(data[0].trim());
                    String nombre = data[1].trim();
                    ValidadorEmail proveedorEmail = new ValidadorEmail(data[2].trim());
                    // Ejemplo si en data[3] viene "Calle, Numero, Comuna, Region"
                    String[] partesDireccion = data[3].trim().split(",");

                    Direccion proveedorDireccion;
                    if (partesDireccion.length == 4) {
                        proveedorDireccion = new Direccion(
                                partesDireccion[0].trim(),
                                partesDireccion[1].trim(),
                                partesDireccion[2].trim(),
                                partesDireccion[3].trim()
                        );
                    } else {
                        // Respaldo en caso de que falten campos
                        proveedorDireccion = new Direccion(data[3].trim(), "", "", "");
                    }

                    ValidadorTelefono proveedorTelefono = new ValidadorTelefono(data[4].trim());
                    String tipoServicio = data[5].trim();


                    Proveedor newProveedor = new Proveedor(proveedorRut,nombre,proveedorEmail,proveedorDireccion,proveedorTelefono,tipoServicio);
                    ProveedorList.add(newProveedor);

                } catch (IllegalArgumentException ex) {
                    System.out.println("Error de validación de Proveedores en linea: " + lineNumber + ": "+ ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de Proveedores: "+ e.getMessage());
        }
        return ProveedorList;
    }

    public boolean guardarProveedorText(Proveedor proveedor, String filePath) {
        if (!GestorArchivo.Archivo(filePath)) return false;
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(filePath, true))) {
            String direccionFormateada = String.format("%s,%s,%s,%s",
                    proveedor.getDireccion().getCalle(), proveedor.getDireccion().getNumero(),
                    proveedor.getDireccion().getComuna(), proveedor.getDireccion().getRegion());

            String linea = String.join(";",
                    proveedor.getRut().getValidadorRut(), proveedor.getNombre(), proveedor.getEmail().getNumber(),
                    direccionFormateada, proveedor.getNumeroTelefono().getValidadorTelefono(),
                    proveedor.getTipoServicio());
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir proveedor: " + e.getMessage());
            return false;
        }
    }
}
