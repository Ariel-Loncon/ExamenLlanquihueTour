package Model.Tours;

import Model.Proveedor;
import Model.Registrable;

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

    @Override
    public String toString() {
        return "ServivioTuristico{" +
                "nombre='" + nombre + '\'' +
                ", duracionHoras=" + duracionHoras +
                '}';
    }
}
