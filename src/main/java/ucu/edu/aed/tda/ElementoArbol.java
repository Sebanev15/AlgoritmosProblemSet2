package ucu.edu.aed.tda;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ElementoArbol<T> implements TDAElemento<T> {
    private ElementoArbol<T> hijoIzquierdo;
    private ElementoArbol<T> hijoDerecho;
    private T dato;
    private int nivel;

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public int getNivel(){
        return nivel;
    }

    public ElementoArbol(T dato){
        this.dato=dato;
        this.hijoDerecho=null;
        this.hijoIzquierdo=null;
    }

    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = (ElementoArbol<T>) hijoIzquierdo;
        this.hijoIzquierdo.setNivel(nivel+1);
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = (ElementoArbol<T>) hijoDerecho;
        this.hijoDerecho.setNivel(nivel+1);
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
                }else{
                    throw new NoSuchElementException("El elemento no se encuentra en el árbol");
                }
            }
            else{
                if (this.hijoDerecho != null){
                    resultado = this.hijoDerecho.buscar(criterioBusqueda);
                }else{
                    throw new NoSuchElementException("El elemento no se encuentra en el árbol");
                }
            }
        }
        return resultado;
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda.compareTo(this.getDato())<0) {
            if (this.hijoIzquierdo !=null) {
                this.hijoIzquierdo = (ElementoArbol<T>) this.hijoIzquierdo.eliminar(criterioBusqueda);
            }else{
                throw new NoSuchElementException("El elemento no se encuentra en el árbol");
            }
            return this;
        }
        else if(criterioBusqueda.compareTo(this.getDato())>0){
            if(this.hijoDerecho !=null) {
                this.hijoDerecho = (ElementoArbol<T>) this.hijoDerecho.eliminar(criterioBusqueda);
            }else{
                throw new NoSuchElementException("El elemento no se encuentra en el árbol");
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
        return obtenerTamaño();
    }

    @Override
    public int altura() {
        int subArbolI = 0;
        int subArbolD = 0;
        if (this.getHijoIzquierdo()!= null) {
            subArbolI=this.getHijoIzquierdo().altura();
        }
        if (this.getHijoDerecho()!=null) {
            subArbolD=this.getHijoDerecho().altura();
        }
        return Math.max(subArbolI, subArbolD) + 1;
         // math max devuelve el mayor entre subArbolI y subArbolD, y se le suma 1 por el nodo actual
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        
        return obtenerNivelRecursivo(this, criterioBusqueda,0);
    }

    private int obtenerNivelRecursivo(TDAElemento<T> nodoElemento, Comparable<T> criterioBusqueda, int nivel){
        if (nodoElemento==null) {
            return -1;
        }
        if (criterioBusqueda.compareTo(nodoElemento.getDato())==0) {
            return nivel;
        }
        else if (criterioBusqueda.compareTo(nodoElemento.getDato())<0) {
            return obtenerNivelRecursivo(nodoElemento.getHijoIzquierdo(), criterioBusqueda, nivel+1);
        }
        else {
            return obtenerNivelRecursivo(nodoElemento.getHijoDerecho(), criterioBusqueda, nivel+1);
        }
    }
    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo!=null) {
            hijoIzquierdo.postOrder(consumidor);
        }
        if (hijoDerecho!=null) {
            hijoDerecho.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (hijoIzquierdo!=null) {
            hijoIzquierdo.preOrder(consumidor);
        }
        if (hijoDerecho!=null) {
            hijoDerecho.preOrder(consumidor);
        }
    }

    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo!=null) {
            hijoIzquierdo.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (hijoDerecho!=null) {
            hijoDerecho.inOrder(consumidor);
        }
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
    public int obtenerTamaño(){
        int subarbolI = 0;
        int subarbolD = 0;
        if (hijoIzquierdo != null) {
            subarbolI = hijoIzquierdo.obtenerTamaño();
        }
        if (hijoDerecho != null) {
            subarbolD = hijoDerecho.obtenerTamaño();
        }
        return subarbolI + subarbolD + 1; // +1 por el nodo actual
    }

    public TDALista<TDAElemento<T>> buscarCompletos(TDALista<TDAElemento<T>> listaCompletos){
        if(this.getHijoDerecho()!=null && this.getHijoIzquierdo()!= null){
            listaCompletos.agregar(this);
            this.hijoIzquierdo.buscarCompletos(listaCompletos);
            this.hijoDerecho.buscarCompletos(listaCompletos);
        }else{
            if(this.hijoDerecho != null){
                {
                    this.hijoDerecho.buscarCompletos(listaCompletos);
                }
            } else if (this.getHijoIzquierdo() != null) {
                this.hijoIzquierdo.buscarCompletos(listaCompletos);
            }
        }
        return listaCompletos;
    }

    public TDALista<TDAElemento<T>> enNivel(TDALista<TDAElemento<T>> listaEnNivel, int nivel){
        this.preOrder(x -> {
            ElementoArbol<T> elemento = (ElementoArbol<T>) x;
            if ( elemento.getNivel() == nivel){
                listaEnNivel.agregar(elemento);
            }
        });
        return listaEnNivel;
    }

    public boolean esDescendiente(TDAElemento<T> x, TDAElemento<T> y) {
    if (x == null) return false;

    if (x == y) return true;

    return esDescendiente(x.getHijoIzquierdo(), y) || esDescendiente(x.getHijoDerecho(), y);
    }
    


}
