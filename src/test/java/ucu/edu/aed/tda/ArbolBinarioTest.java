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
}
