package Util;

/**
 * Clase encargada de la validación por uso de Regex del correo electrónico
 */

public class ValidadorEmail {
    private String validadorEmail;

    /**
     *
     * @param email correo electrónico
     */

    public ValidadorEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,3}$")){
            throw new IllegalArgumentException("Formato de Email invalido");
        }
        this.validadorEmail = email;
    }

    public String getNumber(){
        return validadorEmail;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString(){
        return " " + validadorEmail;
    }
}
