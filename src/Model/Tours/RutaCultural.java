package Model.Tours;

/**
 * Clase encargada de gestionar los datos de RutaCultural
 */

public class RutaCultural extends ServivioTuristico{
    private String lugarHistorico;

    /**
     *
     * @param nombre Nombre del tour paseo lacustre
     * @param duracionHoras Duración aproximada en horas
     * @param lugarHistorico Lugar histórico a visitar del lugar
     */

    public RutaCultural(String nombre, int duracionHoras, String lugarHistorico) {
        super(nombre, duracionHoras);
        this.lugarHistorico = lugarHistorico;
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Nombre Tour: " + getNombre() + " | Lugar Histórico: " + getLugarHistorico());
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra, Nombre, Duración en horas
        System.out.println("Lugar histórico: " + lugarHistorico);
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de Ruta Cultural
     * Incluye la etiqueta de lugarHistorico
     * @return
     */
    @Override
    public String toString() {
        return "---Ruta Cultural---" + super.toString() +
                "|Lugar histórico:" + lugarHistorico +
                '|';
    }
}
