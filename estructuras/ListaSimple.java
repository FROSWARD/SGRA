package estructuras;

public class ListaSimple<T> {
    private NodoSimple<T> cabeza;

    public void agregar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    public NodoSimple<T> getCabeza() {
        return cabeza;
    }
}