package Util;

public class ValidadorTelefono {
    private String validadorTelefono;

    public ValidadorTelefono(String number) {
        if (!number.matches("^(?:\\+?56)?\\s*(?:9)?\\s*[2-9]\\s*(?:\\d\\s*){7}$")){
            throw new IllegalArgumentException("Formato de telefono invalido");
        }
        this.validadorTelefono = number;

    }

    public String getValidadorTelefono(){
        return validadorTelefono;
    }
}

