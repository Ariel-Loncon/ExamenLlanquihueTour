package Model;

import Util.ValidadorEmail;
import Util.ValidadorRut;
import Util.ValidadorTelefono;

/**
 *Clase encargada de gestionar los datos de Guía
 */

public class Guia extends Persona{
    private String idioma;
    private String especialidad;

    /**
     *
     * @param rut Rol unico tributario del guía turístico
     * @param nombre Nombre del guía
     * @param email Dirección de correo electrónico
     * @param direccion Dirección de domicilio del guía
     * @param numeroTelefono Número de teléfono del guía
     * @param idioma Idioma en el cual se especializa el guía
     * @param especialidad Aréa de especialidad del guía
     */

    public Guia(ValidadorRut rut, String nombre, ValidadorEmail email, Direccion direccion, ValidadorTelefono numeroTelefono, String idioma, String especialidad) {
        super(rut, nombre, email, direccion, numeroTelefono);
        this.idioma = idioma;
        this.especialidad = especialidad;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Guia: " + getNombre() + " | RUT: " + getRut() + " | Especialidad: " + especialidad);
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra RUT, Nombre, Email, Dirección, Teléfono
        System.out.println("Idioma manejado: " + idioma + "Especialidad: " + especialidad);
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de Guía
     * Incluye las etiquetas de idioma y especialidad
     * @return
     */
    @Override
    public String toString() {
        return "---Guia---" + super.toString() +
                "|Idioma: " + idioma +
                "|Especialidad: " + especialidad +
                '|';
    }
}
