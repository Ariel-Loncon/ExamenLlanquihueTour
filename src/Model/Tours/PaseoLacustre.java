package Model.Tours;

/**
 * Clase encargada de gestionar datos de Paseo Lacustre
 */

public class PaseoLacustre extends ServivioTuristico{
    private String tipoEmbarcacion;

    /**
     *
     * @param nombre Nombre del tour pase lacustre
     * @param duracionHoras Duración aproximada en horas
     * @param tipoEmbarcacion Tipo de la embarcación a subir
     */

    public PaseoLacustre(String nombre, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Nombre Tour: " + getNombre() + " | Embarcación: " + getTipoEmbarcacion());
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra, Nombre, Duración en horas
        System.out.println("Tipo de Embarcación: " + tipoEmbarcacion);
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de Paseo Lacustre
     * incluye la etiquetas de tipoEmbarcacion
     * @return
     */

    @Override
    public String toString() {
        return "---Paseo Lacustre---" + super.toString() +
                "|Tipo de embarcación:" + tipoEmbarcacion +
                '|';
    }
}
