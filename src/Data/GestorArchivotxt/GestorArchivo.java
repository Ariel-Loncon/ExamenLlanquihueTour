package Data.GestorArchivotxt;
import java.io.File;

/**
 * Clase encargada de gestionar la validación del archivo txt.
 */

public class GestorArchivo {

    public static boolean Archivo(String filePath) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            // Crear carpetas si no existen
            if (parentDir != null && !parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("[INFO] Carpetas creadas: " + parentDir.getAbsolutePath());
                }
            }
            // Crear archivo si no existe
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("[INFO] Archivo creado: " + file.getAbsolutePath());
                }
            }
            return true;
            // Mensaje de error en caso de no cumplir los criterios anteriores.
        } catch (Exception e) {
            System.out.println("[ERROR] No se pudo preparar el archivo/directorio: " + e.getMessage());
            return false;
        }
    }

}
