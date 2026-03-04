package model;
import estructuras.ListaSimple;
import estructuras.NodoSimple;

public class Factura {
    private Cliente cliente;
    private ListaSimple<DetalleFactura> detalles;
    private String medioPago;

    public Factura(Cliente cliente, String medioPago) {
        this.cliente = cliente;
        this.medioPago = medioPago;
        this.detalles = new ListaSimple<>();
    }

    public void agregarDetalle(Producto producto, int cantidad) {
    DetalleFactura detalle = new DetalleFactura(producto, cantidad);
    detalles.agregar(detalle);
    }   

    public double calcularTotal() {

        double total = 0;
        NodoSimple<DetalleFactura> actual = detalles.getCabeza();

        while (actual != null) {
            total += actual.dato.getSubtotal();
            actual = actual.siguiente;
        }

        return total;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public String getMedioPago() {
        return medioPago;
    }

    @Override
    public String toString() {

        String texto = "Cliente: " + cliente.getNombre() + "\nMedio de pago: " + medioPago + "\nProductos:\n";

        NodoSimple<DetalleFactura> actual = detalles.getCabeza();

        while (actual != null) {
            texto += actual.dato.toString() + "\n";
            actual = actual.siguiente;
        }

        texto += "Total: $" + calcularTotal();

        return texto;
    }
}