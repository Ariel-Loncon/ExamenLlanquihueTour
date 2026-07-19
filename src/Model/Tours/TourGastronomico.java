package Model.Tours;

import Model.Proveedor;

public class TourGastronomico extends ServivioTuristico{
    private int numeroParadas;

    /**
     *
     * @param nombre Nombre del tour gastronómico
     * @param duracionHoras Duración aproximada de tour
     * @param numeroParadas Numero de lugares a visitar
     */

    public TourGastronomico(String nombre, int duracionHoras, int numeroParadas) {
        super(nombre, duracionHoras);
        this.numeroParadas = numeroParadas;
    }

    public int getNumeroParadas() {
        return numeroParadas;
    }

    public void setNumeroParadas(int numeroParadas) {
        this.numeroParadas = numeroParadas;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Nombre Tour: " + getNombre() + " | Duración (hrs): " + getDuracionHoras());
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // Muestra, Nombre, Duracion en horas,
        System.out.println("Número de paradas: " + numeroParadas);
    }

    @Override
    public String toString() {
        return "TourGastronomico{" +
                "numeroParadas='" + numeroParadas + '\'' +
                '}';
    }
}
