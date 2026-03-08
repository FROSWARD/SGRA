package ui;

import javax.swing.SwingUtilities;

import service.SistemaAlmacen;

public class MainApp {

    public static void main(String[] args) {

        SistemaAlmacen sistema = new SistemaAlmacen();

        SwingUtilities.invokeLater(() -> {
            DashboardView dashboard = new DashboardView(sistema);
            dashboard.setVisible(true);
        });

    }
}