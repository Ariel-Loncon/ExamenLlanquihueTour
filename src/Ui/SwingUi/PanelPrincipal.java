package Ui.SwingUi;
import Ui.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JFrame {
    private JButton btnReservarTour;
    private JButton btnListaTours;
    private JButton btnAgregarDatos;
    private JButton btnListaClientes; // <-- Nuevo Botón declarado
    private JButton btnSalir;

    public PanelPrincipal() {
        setTitle("Llanquihue Tour App");
        setSize(450, 300); // Incrementado ligeramente el tamaño para el nuevo botón
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Inicialización de botones
        btnReservarTour = new JButton("Reservar Tour");
        btnListaTours = new JButton("Lista Tours Disponible");
        btnAgregarDatos = new JButton("Agregar datos");
        btnListaClientes = new JButton("Lista de Clientes"); // <-- Inicializado
        btnSalir = new JButton("Salir");

        // Diseño en GridBagConstraints para ordenarlo limpiamente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0
        gbc.gridx = 0; gbc.gridy = 0;
        add(btnReservarTour, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(btnListaTours, gbc);

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 1;
        add(btnAgregarDatos, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(btnListaClientes, gbc); // <-- Añadido al lado de Agregar Datos

        // Fila 2: Botón Salir centrado o expandido en la parte inferior
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa las dos columnas
        add(btnSalir, gbc);

        // --- Eventos / Acciones ---

        btnAgregarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar ventanaAgregar = new Agregar();
                ventanaAgregar.setVisible(true);
            }
        });

        btnReservarTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaReservarTour().setVisible(true);
            }
        });

        btnListaTours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaToursDisponibles().setVisible(true);
            }
        });

        // Evento asignado a la nueva Ventana de Clientes
        btnListaClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaListaClientes().setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
