package ucu.edu.aed.tda;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArbolBinarioTest {
    @Test
    public void ArbolVacio(){
        ArbolBinario<String> arbol= new ArbolBinario<>();
        assertTrue(arbol.esVacio());
        //un arbol vacio no tiene nodos, hojas, nodos internos, altura ni tamaño
        assertEquals(0,arbol.cantidadNodos());
        assertEquals(0,arbol.cantidadHojas());
        assertEquals(0,arbol.cantidadNodosInternos());
        assertEquals(0,arbol.altura());
        assertEquals(0,arbol.tamaño());
        //no se puede eliminar un elemento de un arbol vacio
        assertNull(arbol.buscar("a"));
        assertFalse(arbol.eliminar("a"));
    }
    @Test
    public void InsertarYBuscar(){
        ArbolBinario<String> arbol =  new ArbolBinario<>();
        assertTrue(arbol.insertar("1"));
        assertTrue(arbol.insertar("2"));
        assertFalse(arbol.esVacio());
        assertNotNull(arbol.buscar("1"));
    }
    @Test
    public void Eliminar(){
        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.insertar("1");
        arbol.insertar("2");
        assertTrue(arbol.eliminar("1"));
        assertTrue(arbol.eliminar("2"));
        assertTrue(arbol.esVacio());
        assertFalse(arbol.eliminar("3"));
        assertFalse(arbol.eliminar(null));
    }
    @Test
    public void CantidadNodosYHojas(){
        ArbolBinario<String> arbol=new ArbolBinario<>();
        arbol.insertar("1");
        arbol.insertar("2");
        arbol.insertar("3");
        assertEquals(3,arbol.cantidadNodos());
        assertTrue(arbol.cantidadHojas()>=1);
        assertTrue(arbol.cantidadNodosInternos()>=1);
    }
    @Test
    public void Recorridos(){
        ArbolBinario<String> arbol=new ArbolBinario<>();
        arbol.insertar("1");
        arbol.insertar("2");
        arbol.insertar("3");
        StringBuilder preOrder= new StringBuilder();
        arbol.preOrder(x-> preOrder.append(x));
        StringBuilder inOrder= new StringBuilder();
        arbol.inOrder(x-> inOrder.append(x));
        StringBuilder postOrder= new StringBuilder();
        arbol.postOrder(x-> postOrder.append(x));
        assertNotNull(postOrder.toString());
        assertNotNull(inOrder.toString());
        assertNotNull(preOrder.toString());
    }
    //Ejercicio 7
   @Test
    public void testSustituirX() {
        ElementoArbol<String> raiz = new ElementoArbol<>("x");

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz); 

        arbol.sustituirX(10);

        assertEquals("10.0", arbol.obtenerRaiz().getDato());
    }
    @Test
    public void EvaluarExpresionSimple() {
        

        ElementoArbol<String> raiz = new ElementoArbol<>("+");
        raiz.setHijoIzquierdo(new ElementoArbol<>("3"));
        raiz.setHijoDerecho(new ElementoArbol<>("2"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(5.0, arbol.evaluar());
    }
    @Test 
    public void EvaluarNull(){
        ArbolBinario<String> arbol=new ArbolBinario<>();
      assertEquals(0,arbol.evaluar());

    }

    @Test
    public void EvaluarExpresionConSuma() {
        ElementoArbol<String> raiz = new ElementoArbol<>("+");
        raiz.setHijoIzquierdo(new ElementoArbol<>("5"));
        raiz.setHijoDerecho(new ElementoArbol<>("3"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(8.0, arbol.evaluar());
    }

    @Test
    public void EvaluarExpresionConResta() {
        ElementoArbol<String> raiz = new ElementoArbol<>("-");
        raiz.setHijoIzquierdo(new ElementoArbol<>("10"));
        raiz.setHijoDerecho(new ElementoArbol<>("4"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(6.0, arbol.evaluar());
    }

    @Test
    public void EvaluarExpresionConMultiplicacion() {
        ElementoArbol<String> raiz = new ElementoArbol<>("*");
        raiz.setHijoIzquierdo(new ElementoArbol<>("6"));
        raiz.setHijoDerecho(new ElementoArbol<>("7"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(42.0, arbol.evaluar());
    }

    @Test
    public void EvaluarExpresionConDivision() {
        ElementoArbol<String> raiz = new ElementoArbol<>("/");
        raiz.setHijoIzquierdo(new ElementoArbol<>("20"));
        raiz.setHijoDerecho(new ElementoArbol<>("4"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(5.0, arbol.evaluar());
    }

    @Test
    public void EvaluarExpresionComplejaMultipleOperadores() {
        ElementoArbol<String> raiz = new ElementoArbol<>("*");

        ElementoArbol<String> sumaIzq = new ElementoArbol<>("+");
        sumaIzq.setHijoIzquierdo(new ElementoArbol<>("2"));
        sumaIzq.setHijoDerecho(new ElementoArbol<>("3"));

        ElementoArbol<String> derecha = new ElementoArbol<>("4");

        raiz.setHijoIzquierdo(sumaIzq);
        raiz.setHijoDerecho(derecha);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(20.0, arbol.evaluar());
    }

    @Test
    public void EvaluarExpresionConDecimales() {
        ElementoArbol<String> raiz = new ElementoArbol<>("+");
        raiz.setHijoIzquierdo(new ElementoArbol<>("2.5"));
        raiz.setHijoDerecho(new ElementoArbol<>("3.5"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        assertEquals(6.0, arbol.evaluar());
    }

    @Test
    public void CompletosEnArbolVacioLanzaExcepcion() {
        ArbolBinario<String> arbol = new ArbolBinario<>();

        assertThrows(NullPointerException.class, () -> arbol.completos());
    }

    @Test
    public void CompletosConSoloRaiz() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.completos();

        assertEquals(0, resultado.tamaño());
    }

    @Test
    public void CompletosConMultiplesNodosCompletos() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ElementoArbol<String> izq = new ElementoArbol<>("2");
        ElementoArbol<String> der = new ElementoArbol<>("3");
        ElementoArbol<String> izqIzq = new ElementoArbol<>("4");
        ElementoArbol<String> izqDer = new ElementoArbol<>("5");

        raiz.setHijoIzquierdo(izq);
        raiz.setHijoDerecho(der);
        izq.setHijoIzquierdo(izqIzq);
        izq.setHijoDerecho(izqDer);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.completos();

        assertTrue(resultado.tamaño() > 0);
    }

    @Test
    public void CompletosConArbolSinNodosCompletos() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ElementoArbol<String> izq = new ElementoArbol<>("2");
        ElementoArbol<String> izqIzq = new ElementoArbol<>("3");

        raiz.setHijoIzquierdo(izq);
        izq.setHijoIzquierdo(izqIzq);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.completos();

        assertEquals(0, resultado.tamaño());
    }

    @Test
    public void EnNivelEnArbolVacioLanzaExcepcion() {
        ArbolBinario<String> arbol = new ArbolBinario<>();

        assertThrows(NullPointerException.class, () -> arbol.enNivel(0));
    }

    @Test
    public void EnNivelCero() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        raiz.setHijoIzquierdo(new ElementoArbol<>("2"));
        raiz.setHijoDerecho(new ElementoArbol<>("3"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.enNivel(0);

        assertEquals(1, resultado.tamaño());
    }

    @Test
    public void EnNivelUno() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ElementoArbol<String> izq = new ElementoArbol<>("2");
        ElementoArbol<String> der = new ElementoArbol<>("3");

        raiz.setHijoIzquierdo(izq);
        raiz.setHijoDerecho(der);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.enNivel(1);

        assertEquals(2, resultado.tamaño());
    }

    @Test
    public void EnNivelDos() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ElementoArbol<String> izq = new ElementoArbol<>("2");
        ElementoArbol<String> der = new ElementoArbol<>("3");
        ElementoArbol<String> izqIzq = new ElementoArbol<>("4");
        ElementoArbol<String> izqDer = new ElementoArbol<>("5");
        ElementoArbol<String> derDer = new ElementoArbol<>("6");

        raiz.setHijoIzquierdo(izq);
        raiz.setHijoDerecho(der);
        izq.setHijoIzquierdo(izqIzq);
        izq.setHijoDerecho(izqDer);
        der.setHijoDerecho(derDer);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.enNivel(2);

        assertEquals(3, resultado.tamaño());
    }

    @Test
    public void EnNivelMayorQueAltura() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        ElementoArbol<String> izq = new ElementoArbol<>("2");

        raiz.setHijoIzquierdo(izq);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.enNivel(5);

        assertTrue(resultado.esVacio());
    }

    @Test
    public void EnNivelNegativo() {
        ElementoArbol<String> raiz = new ElementoArbol<>("1");
        raiz.setHijoIzquierdo(new ElementoArbol<>("2"));

        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.obtenerRaiz(raiz);

        TDALista<TDAElemento<String>> resultado = arbol.enNivel(-1);

        assertTrue(resultado.esVacio());
    }

}
