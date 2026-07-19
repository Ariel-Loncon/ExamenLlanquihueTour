package Ui.SwingUi;

import Data.GestorArchivotxt.GestorCliente;
import Data.GestorArchivotxt.GestorServicios;
import Model.*;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.ServivioTuristico;
import Model.Tours.TourGastronomico;
import Util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaReservarTour extends JFrame {
    private JTextField txtRut, txtNombre, txtEmail, txtCalle, txtNumero, txtComuna, txtRegion, txtTelefono;
    private JComboBox<String> comboPago;
    private JComboBox<String> comboCategorias;      // Combo 1: Categorías principales
    private JComboBox<String> comboNombresServicios; // Combo 2: Nombres específicos dinámicos
    private JButton btnGuardar, btnCancelar;
    private ArrayList<ServivioTuristico> todosLosServicios;

    public VentanaReservarTour() {
        setTitle("Reservar Tour - Registrar Cliente");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cargar la lista de servicios desde el archivo de texto usando tu gestor
        GestorServicios gs = new GestorServicios();
        todosLosServicios = gs.readServiciosText("resources/Servicios.txt");

        // Inicialización de componentes
        txtRut = new JTextField(15);
        txtNombre = new JTextField(15);
        txtEmail = new JTextField(15);
        txtCalle = new JTextField(15);
        txtNumero = new JTextField(15);
        txtComuna = new JTextField(15);
        txtRegion = new JTextField(15);
        txtTelefono = new JTextField(15);

        comboPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia"});

        comboCategorias = new JComboBox<>(new String[]{"Paseo Lacustre", "Ruta Cultural", "Tour Gastronomico"});
        comboNombresServicios = new JComboBox<>();

        // Cargar por primera vez los nombres de la categoría inicial
        actualizarComboNombres((String) comboCategorias.getSelectedItem());

        btnGuardar = new JButton("Confirmar Reserva");
        btnCancelar = new JButton("Cancelar");

        // Posicionamiento en el Layout
        colocarComponente(0, 0, "RUT:", txtRut, gbc);
        colocarComponente(0, 1, "Nombre Completo:", txtNombre, gbc);
        colocarComponente(0, 2, "Email:", txtEmail, gbc);
        colocarComponente(0, 3, "Calle:", txtCalle, gbc);
        colocarComponente(0, 4, "Número Casa/Depto:", txtNumero, gbc);
        colocarComponente(0, 5, "Comuna:", txtComuna, gbc);
        colocarComponente(0, 6, "Región:", txtRegion, gbc);
        colocarComponente(0, 7, "Teléfono:", txtTelefono, gbc);

        // Fila 1 de Tours: Categoría
        gbc.gridx = 0; gbc.gridy = 8; add(new JLabel("Categoría Tour:"), gbc);
        gbc.gridx = 1; add(comboCategorias, gbc);

        // Fila 2 de Tours: Nombre del Servicio Dinámico
        gbc.gridx = 0; gbc.gridy = 9; add(new JLabel("Tour Específico:"), gbc);
        gbc.gridx = 1; add(comboNombresServicios, gbc);

        // Fila de Pago
        gbc.gridx = 0; gbc.gridy = 10; add(new JLabel("Tipo de Pago:"), gbc);
        gbc.gridx = 1; add(comboPago, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 11; add(btnCancelar, gbc);
        gbc.gridx = 1; add(btnGuardar, gbc);

        // --- Eventos ---

        // Listener para cambiar el segundo combo cuando cambie el primero
        comboCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboCategorias.getSelectedItem();
                actualizarComboNombres(seleccion);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> registrarReservaCliente());
    }

    private void colocarComponente(int x, int y, String etiqueta, JComponent comp, GridBagConstraints gbc) {
        gbc.gridx = x; gbc.gridy = y;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = x + 1;
        add(comp, gbc);
    }

    /**
     * Filtra la lista global de servicios mediante instanceof y llena el segundo combo
     */
    private void actualizarComboNombres(String categoria) {
        comboNombresServicios.removeAllItems();
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

        // Si no hay tours creados en esa categoría avisamos en el combo
        if (agregados == 0) {
            comboNombresServicios.addItem("No hay servicios en esta categoría");
        }
    }

    private void registrarReservaCliente() {
        if (txtRut.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (comboNombresServicios.getSelectedItem() == null ||
                comboNombresServicios.getSelectedItem().toString().startsWith("No hay servicios")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tour válido para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String servicioSeleccionado = comboNombresServicios.getSelectedItem().toString();

        // Registrar cupo en el controlador global usando el nombre específico del servicio
        if (!ControladorCupos.registrarCupo(servicioSeleccionado)) {
            JOptionPane.showMessageDialog(this, "No quedan cupos disponibles para: " + servicioSeleccionado, "Completo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ValidadorRut rut = new ValidadorRut(txtRut.getText().trim());
            ValidadorEmail email = new ValidadorEmail(txtEmail.getText().trim());
            ValidadorTelefono telefono = new ValidadorTelefono(txtTelefono.getText().trim());
            Direccion direccion = new Direccion(txtCalle.getText().trim(), txtNumero.getText().trim(), txtComuna.getText().trim(), txtRegion.getText().trim());

            String ordenAutomatica = ControladorCupos.generarSiguienteOrden();

            Cliente nuevoCliente = new Cliente(rut, txtNombre.getText().trim(), email, direccion, telefono, ordenAutomatica, comboPago.getSelectedItem().toString(), servicioSeleccionado);

            GestorCliente gestor = new GestorCliente();
            if (gestor.guardarClienteText(nuevoCliente, "resources/Cliente.txt")) {
                JOptionPane.showMessageDialog(this, "¡Reserva Exitosa!\nAsignada: " + ordenAutomatica + "\nTour: " + servicioSeleccionado);
                dispose();
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación Fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
}