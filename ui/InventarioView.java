package ui;

import estructuras.NodoDoble;
import model.Producto;
import service.SistemaAlmacen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private SistemaAlmacen sistema;

    public InventarioView(SistemaAlmacen sistema) {

        this.sistema = sistema;

        setTitle("Inventario - SGRA");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Precio", "Stock"}, 0
        );

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnActualizar = new JButton("Actualizar");

        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> cargarProductos());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarProductos();
    }

    private void cargarProductos() {

        tableModel.setRowCount(0);

        NodoDoble<Producto> actual = sistema.getInventario().getCabeza();

        while (actual != null) {

            Producto p = actual.dato;

            tableModel.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock()
            });

            actual = actual.siguiente;
        }
    }

    private void agregarProducto() {

        String codigo = JOptionPane.showInputDialog(this, "Código del producto:");
        String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
        String precioStr = JOptionPane.showInputDialog(this, "Precio:");
        String stockStr = JOptionPane.showInputDialog(this, "Stock:");

        if (codigo == null || nombre == null || precioStr == null || stockStr == null) {
            return;
        }

        try {

            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            Producto producto = new Producto(codigo, nombre, precio, stock);

            sistema.agregarProducto(producto);

            cargarProductos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Precio o stock inválido.");
        }
    }
}