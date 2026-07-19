package Model;

import Util.ValidadorEmail;
import Util.ValidadorRut;
import Util.ValidadorTelefono;

public class Cliente extends Persona{

    private String ordenCompra;
    private String tipoDePago;
    private String servicio;

    /**
     *
     * @param rut Rol único tributario del cliente
     * @param nombre Nombre de identificación del cliente
     * @param email Dirección de correo electrónico del cliente
     * @param direccion Dirección de domicilio del cliente
     * @param numeroTelefono Número de teléfono de contacto del cliente
     * @param ordenCompra Número asignado a la reserva de la compra del cliente
     * @param tipoDePago Preferencia de pago del tour
     * @param servicio asignación del servicio turístico reservado
     */
    public Cliente(ValidadorRut rut, String nombre, ValidadorEmail email, Direccion direccion, ValidadorTelefono numeroTelefono, String ordenCompra, String tipoDePago, String servicio) {
        super(rut, nombre, email, direccion, numeroTelefono);
        this.ordenCompra = ordenCompra;
        this.tipoDePago = tipoDePago;
        this.servicio = servicio;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Cliente: " + getNombre() + " | RUT: " + getRut() + " | Servicio: " + servicio);
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra RUT, Nombre, Email, Dirección, Teléfono
        System.out.println("Tipo de Pago: " + tipoDePago + "Orden de compra: " + ordenCompra + "Servicio: " + servicio);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "ordenCompra='" + ordenCompra + '\'' +
                ", tipoDePago='" + tipoDePago + '\'' +
                '}';
    }
}
