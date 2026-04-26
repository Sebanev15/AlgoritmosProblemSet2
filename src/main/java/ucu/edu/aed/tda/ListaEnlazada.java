package ucu.edu.aed.tda;

import java.util.Comparator;
import java.util.function.Predicate;




public class ListaEnlazada<T> implements TDALista<T> 
{
    
    protected Nodo<T> head;
    protected int size;

    public ListaEnlazada() 
    {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void agregar(T elemento)
    {
        if (this.head == null) 
        {
            this.head = new Nodo<>(elemento, null);  
        } 
        else 
        {
            Nodo<T> actual = this.head;
            while (actual.siguiente != null) 
            {
                actual = actual.siguiente;
            }
            actual.siguiente = new Nodo<T>(elemento, null);
            
        }
        this.size ++;
    }

    @Override
    public void agregarConIndice(int indice, T elemento)
    {
        int i ;
        Nodo<T> actual = this.head;

        if(indice==0){
            this.head = new Nodo<T>(elemento, this.head);
            this.size++;
            return;
        }
        for (  i = 0; i < indice -1; i++)
        {
            if(actual.siguiente!=null){
                actual = actual.siguiente;
            }else{
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
        } 
        Nodo<T>temporal = actual.siguiente;
        actual.siguiente = new Nodo<T>(elemento, temporal);
        this.size++;
    }

    @Override
    public T obtener(int index)
    {
        int i;

        if(index==0){
            if(this.head!=null){
                return this.head.dato;
            }
            throw new NullPointerException("La lista esta vacia por lo que no tiene ningun elemento");
        }
        Nodo<T> actual = this.head;
        for (i = 0; i < index; i++)
        {
            if(actual.siguiente!=null){
                actual = actual.siguiente;
            }else{
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
        }
        return actual.dato;
    }

    @Override
    public T remover(int index)
    {

        int i;
        if(index==0){
            if(this.head!=null){
                T datoEliminar = this.head.dato;
                head = head.siguiente;
                return datoEliminar;
            }
            throw new NullPointerException("La lista esta vacia por lo que no tiene ningun elemento");
        }
        Nodo<T> actual = this.head;
        for (i = 0; i < index -1; i++)
        {
            if(actual.siguiente!=null){
                actual = actual.siguiente;
            }else{
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
        }
        if(actual.siguiente!=null){
            Nodo<T> temporal = actual.siguiente;
            actual.siguiente = temporal.siguiente;
            return temporal.dato;
        }else{
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

    }

    @Override
    public boolean remover(T elemento)//borra el siguiente, no el que queremos. 
    {
        if(this.head!=null){
            if(this.head.dato.equals(elemento)){
                this.head = this.head.siguiente;
                this.size--;
                return true;
            }
        }else{
            throw new NullPointerException("La lista esta vacia por lo que no tiene ningun elemento");
        }

        Nodo<T> actual = this.head;

        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(elemento)) {
                actual.siguiente = actual.siguiente.siguiente;
                this.size--;
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }
    @Override
    public boolean contiene(T elemento){
        if(this.head!=null){
            if(this.head.dato.equals(elemento)) {
                return true;
            }
        }

        Nodo<T> actual=this.head;

        while (actual!= null) {
            if (actual.dato.equals(elemento)){
                return true;
            }
            actual=actual.siguiente;   
        } 
            return false;
    }
    public Nodo<T> contieneNodo(T elemento){
        if(this.head!=null){
            if(this.head.dato.equals(elemento)) {
                return this.head;
            }
        }else{
            throw new NullPointerException("La lista esta vacia por lo que no tiene ningun elemento");
        }

        Nodo<T> actual=this.head;

        while (actual!= null) {
            if (actual.dato.equals(elemento)){
                return actual;
            }
            actual=actual.siguiente;
        }
        return null;
    }
    public int indiceDe(T elem){
        if(this.head==null){
            throw new NullPointerException("La lista esta vacia por lo que no tiene ningun elemento");
        }
        Nodo<T> actual= this.head;
        int contador=0;
        while (actual != null) {
            if(actual.dato.equals(elem)){
                return contador;
            }
            actual=actual.siguiente;
            contador++;
        }
        return -1;
    }

    public T buscar(Predicate<T> criterio){
        Nodo<T> actual = head;
        while (actual != null){
            if (criterio.test(actual.dato)){
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public TDALista<T> ordenar(Comparator<T> comparator){
        ListaEnlazada<T> listaOrdenada= new ListaEnlazada<>();
        Nodo<T> actual = this.head;

        while (actual != null)
        {
            insertarOrdenado(listaOrdenada, actual.dato, comparator);
            actual = actual.siguiente;
        }
        return listaOrdenada;
    }
    public int tamaño(){
        int size = 0;
        Nodo<T> actual = head;
        if(actual==null){
            return size;
        }
        size++;
        while(actual.siguiente!=null){
            size++;
            actual =  actual.siguiente;
        }
        return size;
    }

    public boolean esVacio(){
        return (head == null);
    }
    public void vaciar(){
        if(this.head!=null) {
            while (head != null) {
                head = head.siguiente;
            }
        }
    }
    //Funcion auxiliar para el ordenar
    private void insertarOrdenado(ListaEnlazada<T> lista, T elemento, Comparator<T> comparator){   
    Nodo<T> nuevo = new Nodo<>(elemento, null);

    if (lista.head == null || comparator.compare(elemento, lista.head.dato) < 0)
    {
        nuevo.siguiente = lista.head;
        lista.head = nuevo;
        return;
    }

    Nodo<T> actual = lista.head;

    while (actual.siguiente != null &&
           comparator.compare(elemento, actual.siguiente.dato) >= 0)
    {
        actual = actual.siguiente;
    }

    nuevo.siguiente = actual.siguiente;
    actual.siguiente = nuevo;
    }
    public void imprimir() {
    Nodo<T> actual = head;

    while (actual != null) {
        System.out.println(actual.dato);
        actual = actual.siguiente;
    }
}
}

    
  

