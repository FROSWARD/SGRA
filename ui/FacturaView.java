package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Factura;
import service.SistemaAlmacen;

public class FacturaView extends JFrame {

    private final SistemaAlmacen sistema;
    private Factura facturaActual;

    public FacturaView(SistemaAlmacen sistema) {

        this.sistema = sistema;

        setTitle("Facturación - SGRA");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnCrearFactura = new JButton("Crear Factura");
        JButton btnAgregarProducto = new JButton("Agregar Producto");
        JButton btnGuardarFactura = new JButton("Guardar Factura");

        JTextArea areaFactura = new JTextArea();
        areaFactura.setEditable(false);

        btnCrearFactura.addActionListener(e -> {

            String cedula = JOptionPane.showInputDialog(this,"Cédula del cliente:");
            String medioPago = JOptionPane.showInputDialog(this,"Medio de pago:");

            facturaActual = sistema.crearFactura(cedula, medioPago);

            if(facturaActual == null){
                JOptionPane.showMessageDialog(this,"Cliente no encontrado");
            }else{
                areaFactura.setText("Factura creada para cliente: " + cedula);
            }

        });

        btnAgregarProducto.addActionListener(e -> {

            if(facturaActual == null){
                JOptionPane.showMessageDialog(this,"Primero cree una factura");
                return;
            }

            String codigo = JOptionPane.showInputDialog(this,"Código del producto:");
            String cantidadStr = JOptionPane.showInputDialog(this,"Cantidad:");

            try{

                int cantidad = Integer.parseInt(cantidadStr);

                boolean vendido = sistema.venderProducto(
                        facturaActual,
                        codigo,
                        cantidad
                );

                if(vendido){
                    areaFactura.setText(facturaActual.toString());
                }else{
                    JOptionPane.showMessageDialog(this,"No se pudo vender el producto");
                }

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"Cantidad inválida");
            }

        });

        btnGuardarFactura.addActionListener(e -> {

            if(facturaActual != null){

                sistema.guardarFactura(facturaActual);

                JOptionPane.showMessageDialog(this,"Factura guardada");

                areaFactura.setText("");
                facturaActual = null;

            }

        });

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnCrearFactura);
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnGuardarFactura);

        add(new JScrollPane(areaFactura), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}