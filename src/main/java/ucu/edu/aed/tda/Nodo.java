 package ucu.edu.aed.tda;



public class Nodo<T> {
    public T dato;
    public Nodo<T> siguiente;
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    //metodo equals que invoque el equals del dato tipo t.
    public T dato() {
       return this.dato;
    }
}