package Ui.SwingUi;
import Data.GestorArchivotxt.GestorCliente;
import Data.GestorArchivotxt.GestorEliminar;
import Data.GestorArchivotxt.GestorServicios;
import Model.Cliente;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.ServivioTuristico;
import Model.Tours.TourGastronomico;
import Util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaListaClientes extends JFrame {
    private JComboBox<String> comboFiltroCategoria;     // Combo 1: Categorías
    private JComboBox<String> comboNombresServicios;    // Combo 2: Nombres específicos (Nuevo)
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JButton btnFiltrar;
    private JButton btnBorrar;
    private JButton btnSalir;
    private ArrayList<ServivioTuristico> todosLosServicios; // Lista de tours guardados

    public VentanaListaClientes() {
        setTitle("Listado de Clientes por Servicio Específico");
        setSize(950, 420); // Ampliamos ligeramente el ancho para el nuevo JComboBox
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Cargar los servicios reales desde tu archivo de texto
        GestorServicios gs = new GestorServicios();
        todosLosServicios = gs.readServiciosText("resources/Servicios.txt");

        // Panel Superior de Filtros
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] categorias = {"Todos", "Paseo Lacustre", "Ruta Cultural", "Tour Gastronomico"};
        comboFiltroCategoria = new JComboBox<>(categorias);
        comboNombresServicios = new JComboBox<>();
        btnFiltrar = new JButton("Filtrar");

        panelSuperior.add(new JLabel("Categoría:"));
        panelSuperior.add(comboFiltroCategoria);
        panelSuperior.add(new JLabel("Tour Específico:"));
        panelSuperior.add(comboNombresServicios);
        panelSuperior.add(btnFiltrar);

        // Panel Central: Tabla de Datos (Solo Lectura)
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("N° Orden");
        modeloTabla.addColumn("RUT");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Tour Contratado");
        modeloTabla.addColumn("Forma de Pago");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);

        // Panel Inferior: Botones de Acción
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBorrar = new JButton("Borrar Seleccionado");
        btnSalir = new JButton("Salir");

        panelInferior.add(btnBorrar);
        panelInferior.add(btnSalir);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // --- Eventos y Listeners ---

        // Listener para actualizar el segundo combo cuando cambie el primero (igual que en VentanaReservaTour)
        comboFiltroCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboFiltroCategoria.getSelectedItem();
                actualizarComboNombres(seleccion);
            }
        });

        btnFiltrar.addActionListener(e -> cargarClientesFiltrados());
        btnBorrar.addActionListener(e -> eliminarClienteSeleccionado());
        btnSalir.addActionListener(e -> dispose());

        actualizarComboNombres((String) comboFiltroCategoria.getSelectedItem());
        // Carga inicial completa de la tabla
        cargarClientesFiltrados();
    }

    private void actualizarComboNombres(String categoria) {
        comboNombresServicios.removeAllItems();

        if (categoria.equals("Todos")) {
            comboNombresServicios.addItem("Todos los Tours");
            comboNombresServicios.setEnabled(false);
            return;
        }

        comboNombresServicios.setEnabled(true);
        int agregados = 0;

        for (ServivioTuristico servicio : todosLosServicios) {
            if (categoria.equals("Paseo Lacustre") && servicio instanceof PaseoLacustre) {
                comboNombresServicios.addItem(servicio.getNombre());
                agregados++;
            } else if (categoria.equals("Ruta Cultural") && servicio instanceof RutaCultural) {
                comboNombresServicios.addItem(servicio.getNombre());
                agregados++;
            } else if (categoria.equals("Tour Gastronomico") && servicio instanceof TourGastronomico) {
                comboNombresServicios.addItem(servicio.getNombre());
                agregados++;
            }
        }

        if (agregados == 0) {
            comboNombresServicios.addItem("No hay servicios registrados");
            comboNombresServicios.setEnabled(false);
        }
    }

    private void cargarClientesFiltrados() {
        modeloTabla.setRowCount(0);

        String categoriaSeleccionada = (String) comboFiltroCategoria.getSelectedItem();
        Object itemServicio = comboNombresServicios.getSelectedItem();
        String tourEspecificoSeleccionado = (itemServicio != null) ? itemServicio.toString() : "";

        GestorCliente gc = new GestorCliente();
        ArrayList<Cliente> listaClientes = gc.readClienteText("resources/Cliente.txt");

        for (Cliente c : listaClientes) {
            // 1. Si el primer combo es "Todos", pasan todos los clientes sin importar qué
            if (!categoriaSeleccionada.equals("Todos")) {
                // 2. Si se seleccionó una categoría pero el segundo combo dice que no hay servicios, no mostramos nada
                if (tourEspecificoSeleccionado.startsWith("No hay servicios")) {
                    continue;
                }
                // 3. FILTRO EXACTO POR NOMBRE: Si el servicio del cliente no coincide con el JComboBox específico, se salta
                if (c.getServicio() == null || !c.getServicio().equalsIgnoreCase(tourEspecificoSeleccionado)) {
                    continue;
                }
            }
            modeloTabla.addRow(new Object[]{
                    c.getOrdenCompra(),
                    c.getRut().getValidadorRut(),
                    c.getNombre(),
                    c.getEmail().getNumber(),
                    c.getNumeroTelefono().getValidadorTelefono(),
                    c.getServicio(),
                    c.getTipoDePago()
            });
        }
    }

    private void eliminarClienteSeleccionado() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un cliente.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ordenEliminar = tablaClientes.getValueAt(filaSeleccionada, 0).toString();
        String nombreEliminar = tablaClientes.getValueAt(filaSeleccionada, 2).toString();

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar al cliente " + nombreEliminar + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = GestorEliminar.eliminarCliente(ordenEliminar);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado.");
                cargarClientesFiltrados();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No fue posible eliminar el cliente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
