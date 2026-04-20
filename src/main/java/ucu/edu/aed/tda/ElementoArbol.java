package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ElementoArbol<T> implements TDAElemento<T> {
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;
    private T dato;
    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return hijoDerecho;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda){
        TDAElemento<T> resultado = null;
        if (criterioBusqueda.compareTo(this.getDato()) == 0){
            resultado = this;
        }
        else{
            if(criterioBusqueda.compareTo(this.getDato()) < 0){
                if (this.hijoIzquierdo != null){
                    resultado = this.hijoIzquierdo.buscar(criterioBusqueda);
                }
            }
            else{
                if (this.hijoDerecho != null){
                    resultado = this.hijoDerecho.buscar(criterioBusqueda);
                }
            }
        }
        return resultado;
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        return null;
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        ElementoArbol<T> nuevoElemento = new ElementoArbol<T>();
        nuevoElemento.setDato((T) nuevoDato);
        if(nuevoDato.compareTo(this.getDato()) == 0){
            return false;
        }
        if(nuevoDato.compareTo(this.getDato()) > 0) {
            if(this.getHijoDerecho() == null){
                this.setHijoDerecho(nuevoElemento);
            }else{
                this.getHijoDerecho().insertar(nuevoDato);
            }
        }else{
            if(nuevoDato.compareTo(this.getDato())<0){
                if (this.getHijoIzquierdo() == null){
                    this.setHijoIzquierdo(nuevoElemento);
                }else{
                    this.getHijoIzquierdo().insertar(nuevoDato);
                }
            }
        }
        return true;
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
