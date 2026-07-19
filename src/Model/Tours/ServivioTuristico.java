package Model.Tours;
import Model.Registrable;

/**
 * SuperClase encargada de gestionar los datos de los servicios turísticos
 */

public abstract class ServivioTuristico implements Registrable {
    private String nombre;
    private int duracionHoras;

    /**
     *
     * @param nombre Nombre del servicio turístico
     * @param duracionHoras Duración aproximada del servicio
     */

    public ServivioTuristico(String nombre, int duracionHoras) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Servicio turistico: " + nombre + duracionHoras );
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de la super clase ServivioTuristico
     * Parte con las etiquetas nombre y duracionHoras
     * @return
     */

    @Override
    public String toString() {
        return "---Servicio Turístico---" +
                "|Nombre: " + nombre +
                "|Duración en horas: " + duracionHoras +
                '|';
    }
}
