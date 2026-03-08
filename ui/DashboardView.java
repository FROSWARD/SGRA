package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import service.SistemaAlmacen;

public class DashboardView extends JFrame {

    private final SistemaAlmacen sistema;

    public DashboardView(SistemaAlmacen sistema) {

        this.sistema = sistema;

        setTitle("SGRA - Sistema de Gestión de Almacén");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Sistema SGRA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnClientes = new JButton("Clientes");
        JButton btnInventario = new JButton("Inventario");
        JButton btnFacturas = new JButton("Facturación");

        btnClientes.addActionListener(e ->
                new ClientesView(sistema).setVisible(true)
        );

        btnInventario.addActionListener(e ->
                new InventarioView(sistema).setVisible(true)
        );

        btnFacturas.addActionListener(e ->
                new FacturaView(sistema).setVisible(true)
        );

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnClientes);
        panelBotones.add(btnInventario);
        panelBotones.add(btnFacturas);

        setLayout(new BorderLayout());

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }
}