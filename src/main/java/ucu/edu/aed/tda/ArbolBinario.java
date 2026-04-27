package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T>{
    private ElementoArbol<T> raiz;

    public void obtenerRaiz(TDAElemento<T> raiz){
        this.raiz = (ElementoArbol<T>) raiz;
    }

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
        return raiz;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (esVacio() || criterioBusqueda ==null) {
            return false;
        }
        if (raiz.buscar(criterioBusqueda)== null) {
            return false;
        }
        raiz = (ElementoArbol<T>) raiz.eliminar(criterioBusqueda);
        return true;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if(this.esVacio()){
            this.raiz = new ElementoArbol<>((T) dato);
            T datoInsertar = (T) dato;
            this.raiz.setDato(datoInsertar);
            return this.raiz.getDato() == datoInsertar;
        }
        else{
             raiz.insertar(dato);
        }
        return true;
    }

    @Override // consumer espera un dato
    public void inOrder(Consumer<T> consumidor) {
        if (raiz!=null) {
            raiz.inOrder(x -> {
                consumidor.accept(x.getDato());
            });
        }
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (raiz!=null) {
            raiz.preOrder(x -> {
                consumidor.accept(x.getDato());
            });
        }
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (raiz!=null) {
            raiz.postOrder(x -> {
                consumidor.accept(x.getDato());
            });
        }
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public int cantidadNodos() {
        if (esVacio()) {
            return 0;
        }
        else {
            return raiz.cantidadNodos();
        }
    }

    @Override
    public int cantidadHojas() {
        if (raiz==null) {
            return 0;
        }
        else {
            if (raiz.esHoja()) {
                return 1;
            }
        }
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        if (esVacio()) {
            return 0;
        }
        else{
            return raiz.cantidadNodosInternos();
        }
    }
    public int altura(){
        if (esVacio()) {
            return 0;
        }
        else{
            return raiz.altura();
        }
    }
    public int tamaño(){
        if (esVacio()) {
            return 0;
        }
        else{
            return raiz.obtenerTamaño();
        }    
    }
    //Ejercicio 7
    public void sustituirX(double valor){
        sustituirXRecursivo(raiz,valor);
    }
    private void sustituirXRecursivo(TDAElemento<T> nodo, double valor){
        if (nodo==null) {
            return;
        }
        if (nodo.getDato().equals("x")){
            nodo.setDato((T) String.valueOf(valor));
        }
        sustituirXRecursivo(nodo.getHijoIzquierdo(), valor);
        sustituirXRecursivo(nodo.getHijoDerecho(), valor);
    }
    public double evaluar(){
        return evaluarRecursivo(raiz);
    }
    private double evaluarRecursivo(TDAElemento<T> nodo){
        if (nodo==null) {
            return 0;
        }
        //caso base: si el nodo es una hoja, devuelve su valor numérico
        if(nodo.getHijoIzquierdo()==null && nodo.getHijoDerecho()==null){
            return Double.parseDouble(nodo.getDato().toString());
        }
        double izq = evaluarRecursivo(nodo.getHijoIzquierdo());
        double der = evaluarRecursivo(nodo.getHijoDerecho());
        String operador = (String) nodo.getDato();
        if (operador.equals("+")) {
            return izq + der;
        }
        if (operador.equals("-")) {
            return izq - der;
        }
        if (operador.equals("*")) {
            return izq * der;
        }
        if (operador.equals("/")) {
            return izq / der;
        }
        return 0;
    }
    public TDALista<TDAElemento<T>> completos(){
        TDALista<TDAElemento<T>> listaCompletos = new ListaEnlazada<>();
        if(raiz==null){
            throw new NullPointerException("El árbol está vacío");
        }
        this.raiz.buscarCompletos(listaCompletos);
        return listaCompletos;
    }

    public TDALista<TDAElemento<T>> enNivel(int nivel){
        TDALista<TDAElemento<T>> listaEnNivel = new ListaEnlazada<>();
        return listaEnNivel;
    }
}

