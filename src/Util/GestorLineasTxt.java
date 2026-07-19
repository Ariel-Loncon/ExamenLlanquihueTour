package Util;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase utilitaria para centralizar funciones transversales de soporte al sistema.
 */
public class GestorLineasTxt {

    /**
     * Revisa físicamente el final de un archivo de texto. Si no detecta un salto
     * de línea (\n o \r), lo inyecta de forma preventiva para evitar corrupción de datos.
     *
     * @param filePath Ruta del archivo a validar
     */
    public static void asegurarSaltoDeLineaFinal(String filePath) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            long length = raf.length();
            if (length > 0) {
                raf.seek(length - 1);
                byte ultimoByte = raf.readByte();
                if (ultimoByte != '\n' && ultimoByte != '\r') {
                    raf.write('\n');
                }
            }
        } catch (IOException e) {
            // Fallo silencioso: si el archivo está vacío, nuevo o bloqueado, no interrumpe el flujo
            System.out.println("Aviso preventivo en archivo: " + e.getMessage());
        }
    }
}
