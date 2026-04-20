package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ElementoArbol<T> implements TDAElemento<T> {
    @Override
    public void setHijoIzquierdo(TDAElemento hijoIzquierdo) {

    }

    @Override
    public void setHijoDerecho(TDAElemento hijoDerecho) {

    }

    @Override
    public TDAElemento getHijoIzquierdo() {
        return null;
    }

    @Override
    public TDAElemento getHijoDerecho() {
        return null;
    }

    @Override
    public void setDato(Object dato) {

    }

    @Override
    public T getDato() {
        return null;
    }

    @Override
    public TDAElemento buscar(Comparable criterioBusqueda) {
        return null;
    }

    @Override
    public TDAElemento eliminar(Comparable criterioBusqueda) {
        return null;
    }

    @Override
    public boolean insertar(Comparable nuevoDato) {
        return false;
    }

    @Override
    public boolean esHoja() {
        return false;
    }

    @Override
    public int cantidadHojas() {
        return 0;
    }

    @Override
    public int cantidadNodosInternos() {
        return 0;
    }

    @Override
    public int cantidadNodos() {
        return 0;
    }

    @Override
    public int altura() {
        return 0;
    }

    @Override
    public int obtenerNivel(Comparable criterioBusqueda) {
        return 0;
    }

    @Override
    public void postOrder(Consumer consumidor) {

    }

    @Override
    public void preOrder(Consumer consumidor) {

    }

    @Override
    public void inOrder(Consumer consumidor) {

    }
}
