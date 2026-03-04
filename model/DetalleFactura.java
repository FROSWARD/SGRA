package model;

public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return producto.getNombre() +
                " x" + cantidad +
                " = $" + subtotal;
    }
}