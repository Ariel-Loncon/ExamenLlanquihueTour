package Model;

import Util.ValidadorEmail;
import Util.ValidadorRut;
import Util.ValidadorTelefono;

/**
 * Clase encargada de gestionar al Proveedor
 */
public class Proveedor extends Persona {
    private String tipoServicio;

    /**
     *
     * @param rut Rol único tributario del proveedor
     * @param nombre Nombre de la empresa del proveedor
     * @param email Correo electrónico de contacto del proveedor
     * @param direccion Dirección del proveedor
     * @param numeroTelefono Número de teléfono de contacto del proveedor
     * @param tipoServicio Servicio que ofrece el proveedor
     */

    public Proveedor(ValidadorRut rut, String nombre, ValidadorEmail email, Direccion direccion, ValidadorTelefono numeroTelefono, String tipoServicio) {
        super(rut, nombre, email, direccion, numeroTelefono);
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Proveedor: " + getNombre() + " | Servicio: " + tipoServicio);
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra RUT, Nombre, Email, Dirección, Teléfono
        System.out.println("Tipo de servicio: " + tipoServicio);
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de Proveedor
     * Incluye las etiquetas de tipoServicio
     * @return
     */

    @Override
    public String toString() {
        return "---Proveedor---" + super.toString() +
                "|Tipo de servicio: " + tipoServicio +
                '|';
    }
}
