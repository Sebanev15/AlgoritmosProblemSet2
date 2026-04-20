package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T>{
    @Override
    public T buscar(Comparable<T> predicate) {
        if (esVacio()){
            return null;
        }
        TDAElemento<T> actual = raiz.buscar(predicate);
        if (actual == null){
            return null;
        }
        else{
            return actual.getDato();
        }
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return null;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        return false;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        return false;
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {

    }

    @Override
    public void preOrder(Consumer<T> consumidor) {

    }

    @Override
    public void postOrder(Consumer<T> consumidor) {

    }

    @Override
    public boolean esVacio() {
        if (this.raiz == null){
            return true;
        }
        return false;
    }

    @Override
    public int cantidadNodos() {
        return 0;
    }

    @Override
    public int cantidadHojas() {
        return 0;
    }

    @Override
    public int cantidadNodosInternos() {
        return 0;
    }
}
