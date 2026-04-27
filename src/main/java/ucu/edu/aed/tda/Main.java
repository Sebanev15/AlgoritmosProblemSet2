package ucu.edu.aed.tda;

import java.io.*;


public class Main {
    public static void main(String[] args){
        //BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\ucu\\edu\\aed\\tda\\documentos\\consultas.txt"));
        //ejercicio 7
            ArbolBinario<String> arbol = new ArbolBinario<>();

        ElementoArbol<String> raiz = new ElementoArbol<>("*");

        raiz.setHijoIzquierdo(new ElementoArbol<>("+"));
        raiz.setHijoDerecho(new ElementoArbol<>("3"));

        raiz.getHijoIzquierdo().setHijoIzquierdo(new ElementoArbol<>("x"));
        raiz.getHijoIzquierdo().setHijoDerecho(new ElementoArbol<>("2"));
        // armo la ecuacion (x+2)*3
        // cambiando la x por 5
        //da 21
        arbol.obtenerRaiz(raiz);

        arbol.sustituirX(5);
        ListaEnlazada<TDAElemento<String>> lista = (ListaEnlazada<TDAElemento<String>>) arbol.enNivel(1);
        lista.imprimir();
    }
}
