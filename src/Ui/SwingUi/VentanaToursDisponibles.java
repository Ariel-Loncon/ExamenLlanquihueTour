package Ui.SwingUi;

import Data.GestorArchivotxt.GestorServicios;
import Model.Tours.PaseoLacustre;
import Model.Tours.RutaCultural;
import Model.Tours.ServivioTuristico;
import Model.Tours.TourGastronomico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VentanaToursDisponibles extends JFrame {
    private JComboBox<String> comboCategorias;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JButton btnVer;

    public VentanaToursDisponibles() {
        setTitle("Lista Tours Disponibles");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] opciones = {"Paseo Lacustre", "Ruta Cultural", "Tour Gastronomico"};
        comboCategorias = new JComboBox<>(opciones);
        btnVer = new JButton("ver");
        superior.add(comboCategorias);
        superior.add(btnVer);

        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Servicio");
        modelo.addColumn("Duración / Detalle Específico");
        modelo.addColumn("Cupos Vendidos / Límite");

        tabla = new JTable(modelo);

        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalir = new JButton("Salir");
        inferior.add(btnSalir);
        btnSalir.addActionListener(e -> dispose());

        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(inferior, BorderLayout.SOUTH);

        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSeleccionada = comboCategorias.getSelectedItem().toString();
                modelo.setRowCount(0); // Limpiar tabla

                // Leer los servicios vigentes en tiempo real
                GestorServicios gs = new GestorServicios();
                ArrayList<ServivioTuristico> lista = gs.readServiciosText("resources/Servicios.txt");

                for (ServivioTuristico servicio : lista) {
                    String detalle = "Duración: " + servicio.getDuracionHoras() + " Hrs";
                    int vendidos = ControladorCupos.getCupos(servicio.getNombre());
                    String indicadorCupos = vendidos + " / " + ControladorCupos.LIMITE_MAXIMO;

                    // Filtrar y extraer atributos únicos usando polimorfismo/instanceof
                    if (categoriaSeleccionada.equals("Paseo Lacustre") && servicio instanceof PaseoLacustre) {
                        PaseoLacustre pl = (PaseoLacustre) servicio;
                        modelo.addRow(new Object[]{pl.getNombre(), detalle + " | Embarcación: " + pl.getTipoEmbarcacion(), indicadorCupos});
                    }
                    else if (categoriaSeleccionada.equals("Ruta Cultural") && servicio instanceof RutaCultural) {
                        RutaCultural rc = (RutaCultural) servicio;
                        modelo.addRow(new Object[]{rc.getNombre(), detalle + " | Lugar: " + rc.getLugarHistorico(), indicadorCupos});
                    }
                    else if (categoriaSeleccionada.equals("Tour Gastronomico") && servicio instanceof TourGastronomico) {
                        TourGastronomico tg = (TourGastronomico) servicio;
                        modelo.addRow(new Object[]{tg.getNombre(), detalle + " | Paradas: " + tg.getNumeroParadas(), indicadorCupos});
                    }
                }

                if (modelo.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(VentanaToursDisponibles.this, "No se encontraron servicios registrados en esta categoría.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
