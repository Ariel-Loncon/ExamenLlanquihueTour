package Ui;

import Ui.SwingUi.PanelPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ejecutamos la UI en el Event Dispatch Thread (EDT) de Swing para evitar problemas de concurrencia
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PanelPrincipal app = new PanelPrincipal();
                app.setVisible(true);
            }
        });
    }
}