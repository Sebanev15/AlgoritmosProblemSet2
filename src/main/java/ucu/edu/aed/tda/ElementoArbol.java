package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ElementoArbol<T> implements TDAElemento<T> {
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;
    private T dato;

    public ElementoArbol(T dato){
        this.dato=dato;
        this.hijoDerecho=null;
        this.hijoIzquierdo=null;
    }

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
        return this.hijoIzquierdo;
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
        if (criterioBusqueda.compareTo(this.getDato())<0) {
            if (this.hijoIzquierdo !=null) {
                this.hijoIzquierdo = this.hijoIzquierdo.eliminar(criterioBusqueda); 
            }
            return this;
        }
        else if( criterioBusqueda.compareTo(this.getDato())>0){
            if(this.hijoDerecho !=null) {
                this.hijoDerecho = this.hijoDerecho.eliminar(criterioBusqueda);
            }
            return this;
        }
        return eliminarNodo();
    }

    @Override
    public boolean insertar(Comparable<T> nuevoDato) {
        ElementoArbol<T> nuevoElemento = new ElementoArbol<>((T) nuevoDato);
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
        if(this.hijoIzquierdo== null && this.hijoDerecho== null){
            return true;
        }
        return false;
    }

    @Override
    public int cantidadHojas() {
        if(esHoja()){
            return 1;
        }
        int izq = 0;
        if (hijoIzquierdo!=null){
            izq=hijoIzquierdo.cantidadHojas();
        }
        int der=0;
        if(hijoDerecho!=null){
            der=hijoDerecho.cantidadHojas();
        }
        return izq+der;
    }

    @Override
    public int cantidadNodosInternos() {

        return cantidadNodos()-cantidadHojas();
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
    public TDAElemento<T> eliminarNodo(){ // metodo auxiliar para eliminar
        if (this.hijoIzquierdo == null){
            return this.hijoDerecho;
        } else if (this.hijoDerecho == null){
            return this.hijoIzquierdo;
        } else {
            TDAElemento<T> elHijo = this.hijoIzquierdo;
            TDAElemento<T> elPadre = this;
            while (elHijo.getHijoDerecho() != null){
                elPadre = elHijo;
                elHijo = elHijo.getHijoDerecho();
            }
            if (elPadre != this){
                elPadre.setHijoDerecho(elHijo.getHijoIzquierdo());
                elHijo.setHijoIzquierdo(this.hijoIzquierdo);
            }
            elHijo.setHijoDerecho(this.hijoDerecho);
            return elHijo;
        }
    }

}
