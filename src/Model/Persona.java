package Model;
import Util.*;

public abstract class Persona implements Registrable{
    private ValidadorRut rut;
    private String nombre;
    private ValidadorEmail email;
    private Direccion direccion;
    private ValidadorTelefono numeroTelefono;

    /**
     *
     * @param rut Rol único tributario del cliente, guía y proveedor
     * @param nombre  Nombre de identificación del cliente guía y proveedor
     * @param email Correo electrónico del cliente, guía y proveedor
     * @param direccion Dirección del cliente, guía y proveedor
     * @param numeroTelefono Número de teléfono asociado al cliente, guía y proveedor
     */

    public Persona(ValidadorRut rut, String nombre, ValidadorEmail email, Direccion direccion, ValidadorTelefono numeroTelefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
    }

    public ValidadorRut getRut() {
        return rut;
    }

    public void setRut(ValidadorRut rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ValidadorEmail getEmail() {
        return email;
    }

    public void setEmail(ValidadorEmail email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public ValidadorTelefono getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(ValidadorTelefono numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Persona: " + nombre + rut + email + direccion + numeroTelefono);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "rut=" + rut +
                ", nombre='" + nombre + '\'' +
                ", email=" + email +
                ", direccion='" + direccion + '\'' +
                ", numero de Teléfono=" + numeroTelefono +
                '}';
    }
}
