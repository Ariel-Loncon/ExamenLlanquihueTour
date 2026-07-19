package Ui.SwingUi;

import Data.GestorListas.GestorEntidades;
import Model.Guia;
import Model.Proveedor;
import Model.Registrable;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.TourGastronomico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Carga y filtra los datos de GestorEntidades en el modelo de la tabla
 * dependiendo de la entidad seleccionada.
 */

public class VerDatosJtable {

    public static void mostrarDatosEnTabla(String entidadSeleccionada, DefaultTableModel modeloTabla) {
        // 1. Limpiar filas y columnas anteriores para reconfigurar la tabla
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        // 2. Inicia el gestor y cargar los datos frescos desde los archivos txt
        GestorEntidades gestor = new GestorEntidades();
        gestor.cargarTodo();

        // 3. Configurar las columnas y rellenar según la opción del ComboBox
        switch (entidadSeleccionada) {
            case "Guia":
                modeloTabla.addColumn("RUT");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Email");
                modeloTabla.addColumn("Teléfono");
                modeloTabla.addColumn("Idioma");
                modeloTabla.addColumn("Especialidad");

                for (Registrable item : gestor.getBaseDeDatos()) {
                    if (item instanceof Guia) {
                        Guia g = (Guia) item;
                        modeloTabla.addRow(new Object[]{
                                g.getRut().getValidadorRut(),
                                g.getNombre(),
                                g.getEmail().getNumber(),
                                g.getNumeroTelefono().getValidadorTelefono(),
                                g.getIdioma(),
                                g.getEspecialidad()
                        });
                    }
                }
                break;

            case "Proveedor":
                modeloTabla.addColumn("RUT");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Email");
                modeloTabla.addColumn("Teléfono");
                modeloTabla.addColumn("Tipo Servicio");

                for (Registrable item : gestor.getBaseDeDatos()) {
                    if (item instanceof Proveedor) {
                        Proveedor p = (Proveedor) item;
                        modeloTabla.addRow(new Object[]{
                                p.getRut().getValidadorRut(),
                                p.getNombre(),
                                p.getEmail().getNumber(),
                                p.getNumeroTelefono().getValidadorTelefono(),
                                p.getTipoServicio()
                        });
                    }
                }
                break;

            case "Paseo Lacustre":
                modeloTabla.addColumn("Nombre del Tour");
                modeloTabla.addColumn("Duración (Hrs)");
                modeloTabla.addColumn("Tipo Embarcación");

                for (Registrable item : gestor.getBaseDeDatos()) {
                    if (item instanceof PaseoLacustre) {
                        PaseoLacustre pl = (PaseoLacustre) item;
                        modeloTabla.addRow(new Object[]{
                                pl.getNombre(),
                                pl.getDuracionHoras(),
                                pl.getTipoEmbarcacion()
                        });
                    }
                }
                break;
            case "Ruta Cultural":
                modeloTabla.addColumn("Nombre del Tour");
                modeloTabla.addColumn("Duración (Hrs)");
                modeloTabla.addColumn("Lugar Histórico");

                for (Registrable item : gestor.getBaseDeDatos()) {
                    if (item instanceof RutaCultural) {
                        RutaCultural rc = (RutaCultural) item;
                        modeloTabla.addRow(new Object[]{
                                rc.getNombre(),
                                rc.getDuracionHoras(),
                                rc.getLugarHistorico()
                        });
                    }
                }
                break;

            case "Tour Gastronomico":
                modeloTabla.addColumn("Nombre del Tour");
                modeloTabla.addColumn("Duración (Hrs)");
                modeloTabla.addColumn("N° de Paradas");

                for (Registrable item : gestor.getBaseDeDatos()) {
                    if (item instanceof TourGastronomico) {
                        TourGastronomico tg = (TourGastronomico) item;
                        modeloTabla.addRow(new Object[]{
                                tg.getNombre(),
                                tg.getDuracionHoras(),
                                tg.getNumeroParadas()
                        });
                    }
                }
                break;
        }
    }

    public static boolean eliminarDatosSegunTabla(String entidadSeleccionada, JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();

        // 1. Validar selección
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // 2. Obtener la clave primaria de la fila (RUT o Nombre)
        String claveEliminar = tabla.getValueAt(filaSeleccionada, 0).toString();

        // 3. Confirmación del usuario
        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar el registro '" + claveEliminar + "'?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (respuesta != JOptionPane.YES_OPTION) {
            return false;
        }

        boolean exitoArchivo = false;
        // 4. Delegar la eliminación al GestorEliminar según el tipo de entidad
        switch (entidadSeleccionada) {
            case "Guia":
                // Elimina del archivo Guias.txt usando el RUT
                exitoArchivo = Data.GestorArchivotxt.GestorEliminar.eliminarGuia(claveEliminar);
                break;
            case "Proveedor":
                // Elimina del archivo Proveedores.txt usando el RUT/Nombre
                exitoArchivo = Data.GestorArchivotxt.GestorEliminar.eliminarOperador(claveEliminar);
                break;
            case "Paseo Lacustre":
                // Elimina de Servicios.txt pasando el Tipo exacto y el Nombre
                exitoArchivo = Data.GestorArchivotxt.GestorEliminar.eliminarServicio("PaseoLacustre", claveEliminar);
                break;
            case "Ruta Cultural":
                exitoArchivo = Data.GestorArchivotxt.GestorEliminar.eliminarServicio("ExcursionCultural", claveEliminar);
                break;
            case "Tour Gastronomico":
                // Elimina de Servicios.txt pasando el Tipo exacto y el Nombre
                exitoArchivo = Data.GestorArchivotxt.GestorEliminar.eliminarServicio("TourGastronomico", claveEliminar);
                break;
        }
        // 5. Informar al usuario del resultado de la operación
        if (exitoArchivo) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente de los archivos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el archivo físico o el registro no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
