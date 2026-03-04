package service;

import estructuras.ListaDoble;
import estructuras.ListaSimple;
import estructuras.NodoDoble;
import estructuras.NodoSimple;
import model.Cliente;
import model.Factura;
import model.Producto;

public class SistemaAlmacen {

    private ListaDoble<Producto> inventario;
    private ListaSimple<Cliente> clientes;
    private ListaSimple<Factura> facturasDelDia;

    public SistemaAlmacen() {
        inventario = new ListaDoble<>();
        clientes = new ListaSimple<>();
        facturasDelDia = new ListaSimple<>();
    }

    public void agregarProducto(Producto producto) {
        inventario.agregar(producto);
    }

    public void registrarCliente(Cliente cliente) {
        clientes.agregar(cliente);
    }

    public Producto buscarProducto(String codigo) {

        NodoDoble<Producto> actual = inventario.getCabeza();

        while (actual != null) {
            if (actual.dato.getCodigo().equals(codigo)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public Cliente buscarCliente(String cedula) {

        NodoSimple<Cliente> actual = clientes.getCabeza();

        while (actual != null) {
            if (actual.dato.getCedula().equals(cedula)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public Factura crearFactura(String cedulaCliente, String medioPago) {
        Cliente cliente = buscarCliente(cedulaCliente);

        if (cliente != null) {
            return new Factura(cliente, medioPago);
        }

        return null;
    }

    public boolean venderProducto(Factura factura, String codigoProducto, int cantidad) {

        if (factura == null) {
            return false;
        }

        if (cantidad <= 0) {
            return false;
        }

        Producto producto = buscarProducto(codigoProducto);

        if (producto == null) {
            return false;
        }

        if (producto.getStock() < cantidad) {
            return false;
        }

        producto.setStock(producto.getStock() - cantidad);
        factura.agregarDetalle(producto, cantidad);

        return true;
    }

    public void guardarFactura(Factura factura) {
        facturasDelDia.agregar(factura);
    }

    public ListaDoble<Producto> getInventario() {
        return inventario;
    }

    public ListaSimple<Cliente> getClientes() {
        return clientes;
    }

    public ListaSimple<Factura> getFacturasDelDia() {
        return facturasDelDia;
    }
}