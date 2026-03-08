package ui;

import estructuras.NodoSimple;
import model.Cliente;
import service.SistemaAlmacen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientesView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private SistemaAlmacen sistema;

    public ClientesView(SistemaAlmacen sistema) {

        this.sistema = sistema;

        setTitle("Clientes - SGRA");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(
                new Object[]{"Cédula", "Nombre"}, 0
        );

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnActualizar = new JButton("Actualizar");

        btnAgregar.addActionListener(e -> agregarCliente());
        btnActualizar.addActionListener(e -> cargarClientes());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarClientes();
    }

    private void cargarClientes() {

        tableModel.setRowCount(0);

        NodoSimple<Cliente> actual = sistema.getClientes().getCabeza();

        while (actual != null) {

            Cliente c = actual.dato;

            tableModel.addRow(new Object[]{
                    c.getCedula(),
                    c.getNombre()
            });

            actual = actual.siguiente;
        }
    }

    private void agregarCliente() {

        String cedula = JOptionPane.showInputDialog(this, "Cédula del cliente:");
        String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente:");

        if (cedula == null || nombre == null) {
            return;
        }

        Cliente cliente = new Cliente(cedula, nombre);

        sistema.registrarCliente(cliente);

        cargarClientes();
    }
}