package Model.Tours;

import Model.Proveedor;

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

    @Override
    public String toString() {
        return "RutaCultural{" +
                "lugarHistorico='" + lugarHistorico + '\'' +
                '}';
    }
}
