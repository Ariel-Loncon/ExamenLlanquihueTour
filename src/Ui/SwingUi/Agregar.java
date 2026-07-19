package Ui.SwingUi;

import Data.GestorArchivotxt.GestorGuias;
import Data.GestorArchivotxt.GestorProveedor;
import Data.GestorArchivotxt.GestorServicios;
import Model.*;
import Util.*;
import Ui.SwingUi.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agregar extends JFrame {
    private JComboBox<String> comboEntidades;
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private JButton btnVer;
    private JButton btnAgregar;
    private JButton btnBorrar;
    private JButton btnSalir;

    public Agregar() {
        setTitle("Agregar datos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] opciones = {"Guia", "Proveedor", "Paseo Lacustre", "Ruta Cultural", "Tour Gastronomico"};
        comboEntidades = new JComboBox<>(opciones);
        panelSuperior.add(new JLabel("Seleccionar Entidad:"));
        panelSuperior.add(comboEntidades);

        // Panel Central
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Campo 1");
        modeloTabla.addColumn("Campo 2");
        modeloTabla.addColumn("Campo 3");
        modeloTabla.addColumn("Campo 4");

        tablaDatos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaDatos);

        // Panel Lateral Derecho
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        btnVer = new JButton("ver");
        btnAgregar = new JButton("Agregar");
        btnBorrar = new JButton("Borrar");

        btnVer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBorrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelLateral.add(Box.createVerticalStrut(20));
        panelLateral.add(btnVer);
        panelLateral.add(Box.createVerticalStrut(20));
        panelLateral.add(btnAgregar);
        panelLateral.add(Box.createVerticalStrut(20));
        panelLateral.add(btnBorrar);

        // Panel Inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalir = new JButton("salir");
        panelInferior.add(btnSalir);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

        // --- Acciones de los Botones ---

        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura el elemento seleccionado ("Guia", "Proveedor", etc.)
                String seleccion = (String) comboEntidades.getSelectedItem();

                // Llama al controlador externo pasando la selección y el modelo de la tabla
                VerDatosJtable.mostrarDatosEnTabla(seleccion, modeloTabla);
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboEntidades.getSelectedItem();
                abrirFormularioInmmediato(seleccion);
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener qué filtro está seleccionado actualmente en tu JComboBox de la ventana Agregar
                String filtroActual = comboEntidades.getSelectedItem().toString();

                // Ejecutar la lógica de borrado pasando el filtro y la JTable de tu ventana
                boolean exito = VerDatosJtable.eliminarDatosSegunTabla(filtroActual, tablaDatos);

                // Si se borró correctamente, refrescamos la tabla inmediatamente para mostrar los cambios
                if (exito) {
                    VerDatosJtable.mostrarDatosEnTabla(filtroActual, (DefaultTableModel) tablaDatos.getModel());
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Identifica la opción del ComboBox y despliega el JDialog correspondiente.
     */
    private void abrirFormularioInmmediato(String seleccion) {
        JDialog ventanaForm = new JDialog(this, "Agregar " + seleccion, true);
        ventanaForm.setSize(400, 450);
        ventanaForm.setLocationRelativeTo(this);
        ventanaForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (seleccion.equals("Guia") || seleccion.equals("Proveedor")) {
            JTextField tRut = new JTextField(12), tNom = new JTextField(12), tMail = new JTextField(12);
            JTextField tCalle = new JTextField(10), tNum = new JTextField(5), tCom = new JTextField(10), tReg = new JTextField(10);
            JTextField tTel = new JTextField(12);

            JLabel lblEspecifico = new JLabel(seleccion.equals("Guia") ? "Idioma:" : "Tipo de Servicio:");
            JTextField tEspecifico = new JTextField(12);

            JLabel lblExtra = new JLabel("Especialidad:");
            JTextField tExtra = new JTextField(12);

            int y = 0;
            agregarCampoForm(ventanaForm, "RUT:", tRut, gbc, y++);
            agregarFormRow(ventanaForm, "Nombre:", tNom, gbc, y++);
            agregarFormRow(ventanaForm, "Email:", tMail, gbc, y++);
            agregarFormRow(ventanaForm, "Calle:", tCalle, gbc, y++);
            agregarFormRow(ventanaForm, "Número:", tNum, gbc, y++);
            agregarFormRow(ventanaForm, "Comuna:", tCom, gbc, y++);
            agregarFormRow(ventanaForm, "Región:", tReg, gbc, y++);
            agregarFormRow(ventanaForm, "Teléfono:", tTel, gbc, y++);

            gbc.gridx = 0; gbc.gridy = y; ventanaForm.add(lblEspecifico, gbc);
            gbc.gridx = 1; ventanaForm.add(tEspecifico, gbc);
            y++;

            if (seleccion.equals("Guia")) {
                gbc.gridx = 0; gbc.gridy = y; ventanaForm.add(lblExtra, gbc);
                gbc.gridx = 1; ventanaForm.add(tExtra, gbc);
                y++;
            }

            JButton btnGuardar = new JButton("Guardar Registro");
            gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
            ventanaForm.add(btnGuardar, gbc);

            btnGuardar.addActionListener(ev -> {
                try {
                    ValidadorRut vrut = new ValidadorRut(tRut.getText().trim());
                    ValidadorEmail vmail = new ValidadorEmail(tMail.getText().trim());
                    ValidadorTelefono vtel = new ValidadorTelefono(tTel.getText().trim());
                    Direccion dir = new Direccion(tCalle.getText().trim(), tNum.getText().trim(), tCom.getText().trim(), tReg.getText().trim());

                    if (seleccion.equals("Guia")) {
                        Guia g = new Guia(vrut, tNom.getText().trim(), vmail, dir, vtel, tEspecifico.getText().trim(), tExtra.getText().trim());
                        if (new GestorGuias().guardarGuiaText(g, "resources/Guias.txt")) {
                            JOptionPane.showMessageDialog(ventanaForm, "Guía agregado con éxito.");
                            ventanaForm.dispose();
                        }
                    } else {
                        Proveedor p = new Proveedor(vrut, tNom.getText().trim(), vmail, dir, vtel, tEspecifico.getText().trim());
                        if (new GestorProveedor().guardarProveedorText(p, "resources/Proveedores.txt")) {
                            JOptionPane.showMessageDialog(ventanaForm, "Proveedor agregado con éxito.");
                            ventanaForm.dispose();
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ventanaForm, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

        } else {
            JTextField tNomServ = new JTextField(15);
            JTextField tDuracion = new JTextField(15);

            JLabel lblDinamico = new JLabel();
            JTextField tDinamico = new JTextField(15);

            if (seleccion.equals("Tour Gastronomico")) {
                lblDinamico.setText("Número de Paradas:");
            } else if (seleccion.equals("Paseo Lacustre")) {
                lblDinamico.setText("Tipo Embarcación:");
            } else {
                lblDinamico.setText("Lugar Histórico:");
            }

            int y = 0;
            agregarFormRow(ventanaForm, "Nombre del Tour:", tNomServ, gbc, y++);
            agregarFormRow(ventanaForm, "Duración (Horas):", tDuracion, gbc, y++);

            gbc.gridx = 0; gbc.gridy = y; ventanaForm.add(lblDinamico, gbc);
            gbc.gridx = 1; ventanaForm.add(tDinamico, gbc);
            y++;

            JButton btnGuardarServ = new JButton("Guardar Servicio");
            gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
            ventanaForm.add(btnGuardarServ, gbc);

            btnGuardarServ.addActionListener(ev -> {
                try {
                    String nombre = tNomServ.getText().trim();
                    int duracion = Integer.parseInt(tDuracion.getText().trim());
                    String campoExtra = tDinamico.getText().trim();

                    String identificadorTipo = seleccion.equals("Ruta Cultural") ? "ExcursionCultural" : seleccion.replace(" ", "");

                    if (nombre.isEmpty() || campoExtra.isEmpty()) {
                        JOptionPane.showMessageDialog(ventanaForm, "Complete todos los campos.");
                        return;
                    }

                    boolean ok = new GestorServicios().guardarServicioText(identificadorTipo, nombre, duracion, campoExtra, "resources/Servicios.txt");
                    if (ok) {
                        JOptionPane.showMessageDialog(ventanaForm, "Servicio registrado correctamente.");
                        ventanaForm.dispose();
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(ventanaForm, "La duración debe ser un número entero válido.");
                }
            });
        }

        ventanaForm.setVisible(true);
    }

    private void agregarFormRow(Container c, String label, JComponent comp, GridBagConstraints gbc, int row) {
        agregarCampoForm(c, label, comp, gbc, row);
    }

    private void agregarCampoForm(Container c, String label, JComponent comp, GridBagConstraints gbc, int row) {
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = row;
        c.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        c.add(comp, gbc);
    }
}