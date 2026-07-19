package Model;

/**
 * Clase encargada de gestionar las direcciones de Cliente, Guia y Proveedor
 */
public class Direccion {
    private String calle;
    private String numero;
    private String comuna;
    private String region;

    /**
     *
     * @param calle  Calle donde se ubica la dirección del cliente, guía y proveedor
     * @param numero Número del domicilio se ubica la dirección del cliente, guía y proveedor
     * @param comuna Comuna donde se ubica la dirección del cliente, guía y proveedor
     * @param region Region de donde se ubica la dirección del cliente, guía y proveedor
     */

    public Direccion(String calle, String numero, String comuna, String region) {
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.region = region;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Devuelve una representación de los datos en forma de texto de la información de Direccion
     * Posee las etiquetas de cale, numer, comuna y region
     * @return
     */

    @Override
    public String toString() {
        return "---Dirección---" + super.toString() +
                "|Calle: " + calle +
                "|Número: " + numero +
                "|Comuna: " + comuna +
                "|Region: " + region +
                '|';
    }
}
