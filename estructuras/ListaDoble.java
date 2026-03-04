package estructuras;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza;

    public void agregar(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoDoble<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
            nuevo.anterior = aux;
        }
    }

    public NodoDoble<T> getCabeza() {
        return cabeza;
    }
}