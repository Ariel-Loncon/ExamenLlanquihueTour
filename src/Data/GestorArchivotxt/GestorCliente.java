package Data.GestorArchivotxt;
import Model.*;
import Util.ValidadorEmail;
import Util.ValidadorRut;
import Util.ValidadorTelefono;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los datos del cliente guardada en el archivo de texto mediante Arraylist.
 */

public class GestorCliente {
    public ArrayList<Cliente> readClienteText(String filePath) {
        ArrayList<Cliente> clienteList = new ArrayList<>();
        // Se verifica que el archivo esté creado, si no está sé, regrersa a la lista vacia
        if (!GestorArchivo.Archivo(filePath)) {
            System.out.println("El archivo no existe o no se puede acceder: " + filePath);
            return clienteList;
        }
        // Abre el archivo para leerlo linea a linea
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            //Lee linea a linea, lee una, la procesa y continua con la siguiente
            while ((line = br.readLine()) != null) {
                lineNumber++; //contador de líneas, util para mensajes de errores en líneas y su localización
                //ignora las líneas vacías, si encuentra una continua
                if (line.trim().isEmpty())
                    continue;

                //Se separa la línea con ";" para convertirlas en un arreglo
                String[] data = line.split(";");
                // verifica cantidad de campos en el arreglo
                if (data.length != 8){
                    System.out.println("Error en línea " + lineNumber + ": Estructura incorrecta de cliente (Se esperaban 8 campos, se encontraron " + data.length + ").");
                    continue;
                }

                try {
                    //Se transforman y validan los datos al tipo correspondiente
                    ValidadorRut clienteRut     = new ValidadorRut(data[0].trim());
                    String nombre               = data[1].trim();
                    ValidadorEmail clienteEmail = new ValidadorEmail(data[2].trim());

                    // Ejemplo si en data[3] viene "Calle, Número, Comuna, Region"
                    String[] partesDireccion    = data[3].trim().split(",");
                    Direccion clienteDireccion;
                    if (partesDireccion.length == 4) {
                        clienteDireccion = new Direccion(
                                partesDireccion[0].trim(),
                                partesDireccion[1].trim(),
                                partesDireccion[2].trim(),
                                partesDireccion[3].trim()
                        );
                    } else {
                        // Respaldo en caso de que falten campos, los rellena con campos vacíos
                        clienteDireccion = new Direccion(data[3].trim(), "", "", "");
                    }

                    ValidadorTelefono clienteTelefono = new ValidadorTelefono(data[4].trim());
                    String ordenCompra = data[5].trim();
                    String tipoPago    = data[6].trim();
                    String servicio    = data[7].trim();
                    //Se crea al objeto Cliente
                    Cliente newCliente = new Cliente(clienteRut,nombre,clienteEmail,clienteDireccion,clienteTelefono,ordenCompra,tipoPago,servicio);
                    //Se agrega a la lista, cada cliente leido se agrega a la ArrayList
                    clienteList.add(newCliente);

                } catch (IllegalArgumentException ex) {
                    System.out.println("Error de validación de cliente en linea: " + lineNumber + ": "+ ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de clientes: "+ e.getMessage());
        }
        return clienteList;
    }
    //Escribe en el archivo de texto
    public boolean guardarClienteText(Cliente cliente, String filePath) {
        //Verifica que exista el archivo
        if (!GestorArchivo.Archivo(filePath)) {
            return false;
        }
        //abre el archivo de texto y agrega información sin sobreescribir
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

            String rut = cliente.getRut().getValidadorRut();
            String nombre = cliente.getNombre();
            String email = cliente.getEmail().getNumber();

            Direccion dir = cliente.getDireccion();
            String direccionFormateada = String.format("%s,%s,%s,%s",
                    dir.getCalle(), dir.getNumero(), dir.getComuna(), dir.getRegion());

            String telefono = cliente.getNumeroTelefono().getValidadorTelefono();
            String ordenCompra = cliente.getOrdenCompra();
            String tipoPago = cliente.getTipoDePago();
            String servicio = cliente.getServicio();

            String linea = String.join(";", rut, nombre, email, direccionFormateada, telefono, ordenCompra, tipoPago, servicio);

            bw.write(linea);
            bw.newLine();
            return true;

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de clientes: " + e.getMessage());
            return false;
        }
    }
}
