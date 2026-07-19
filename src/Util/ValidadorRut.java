package Util;

/**
 * Clase encargada de la validación del rut mediante regex
 */

public class ValidadorRut {
    private String validadorRut;

    /**
     *
     * @param numberRUT Rol único tributario de una empresa o persona
     */

    public ValidadorRut(String numberRUT) {
        if (!numberRUT.matches("^[0-9]{1,2}(\\.?[0-9]{3}){2}-[0-9kK]{1}$")) {
            throw new IllegalArgumentException("Formato de RUT invalido");
        }
        this.validadorRut = numberRUT;
    }

    public String getValidadorRut() {
        return validadorRut;
    }

    public void setValidadorRut(String validadorRut) {
        this.validadorRut = validadorRut;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        return " " + validadorRut;
    }
}