package ucu.edu.aed.tda;

import java.io.*;


public class Main {
    public static void main(String[] args){
        //BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\ucu\\edu\\aed\\tda\\documentos\\consultas.txt"));
        //ejercicio 7
            ArbolBinario<Integer> arbol = new ArbolBinario<>();

        ElementoArbol<Integer> raiz = new ElementoArbol<>(5);

        raiz.setHijoIzquierdo(new ElementoArbol<>(2));
        raiz.setHijoDerecho(new ElementoArbol<>(8));

        raiz.getHijoIzquierdo().setHijoIzquierdo(new ElementoArbol<>(1));
        raiz.getHijoIzquierdo().setHijoDerecho(new ElementoArbol<>(3));

        raiz.getHijoDerecho().setHijoIzquierdo(new ElementoArbol<>(7));
        raiz.getHijoDerecho().setHijoDerecho(new ElementoArbol<>(9));
        // armo la ecuacion (x+2)*3
        // cambiando la x por 5
        //da 21
        arbol.obtenerRaiz(raiz);

        arbol.sustituirX(5);

        ListaEnlazada<TDAElemento<Integer>> listaEnNivel = (ListaEnlazada<TDAElemento<Integer>>) arbol.enNivel(2);
        System.out.println(arbol.evaluar());
    }
}
