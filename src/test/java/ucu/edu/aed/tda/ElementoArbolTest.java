package ucu.edu.aed.tda;

import org.junit.jupiter.api.Test;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ElementoArbolTest {

    private ElementoArbol<Integer> elemento1 = new ElementoArbol<>(1);
    private ElementoArbol<Integer> elemento2 = new ElementoArbol<>(2);
    private ElementoArbol<Integer> elemento3 = new ElementoArbol<>(3);

    @Test
    public void ConstructorWithValidDataTest() {
        int dato = 1;
        ElementoArbol<Integer> elemento = new ElementoArbol<>(dato);
        assertEquals(dato, elemento.getDato());
        assertNull(elemento.getHijoDerecho());
        assertNull(elemento.getHijoIzquierdo());
    }

    @Test
    public void SetAndGetHijoIzquierdoTest() {
        elemento2.setHijoIzquierdo(elemento1);
        assertEquals(elemento1, elemento2.getHijoIzquierdo());
    }

    @Test
    public void SetAndGetHijoDerechoTest() {
        elemento2.setHijoDerecho(elemento3);
        assertEquals(elemento3, elemento2.getHijoDerecho());
    }

    @Test
    public void SetAndGetDatoTest() {
        int dato = 4;
        elemento2.setDato(dato);
        assertEquals(dato, elemento2.getDato());
    }

    @Test
    public void BuscarElementoIsRootTest() {
        TDAElemento<Integer> resultado = elemento2.buscar(2);
        assertEquals(elemento2, resultado);
    }

    @Test
    public void BuscarElementoIsLeftSonTest() {
        elemento2.setHijoIzquierdo(elemento1);
        TDAElemento<Integer> resultado = elemento2.buscar(1);
        assertEquals(elemento1, resultado);
    }

    @Test
    public void BuscarElementoIsRightSonTest() {
        elemento2.setHijoDerecho(elemento3);
        TDAElemento<Integer> resultado = elemento2.buscar(3);
        assertEquals(elemento3, resultado);
    }

    @Test
    public void BuscarElementoIsNotLeftSonTest() {
        assertNull(elemento2.getHijoIzquierdo());
        assertThrows(NoSuchElementException.class, () -> elemento2.buscar(1));
    }

    @Test
    public void BuscarElementoIsNotRightSonTest() {
        assertNull(elemento2.getHijoDerecho());
        assertThrows(NoSuchElementException.class, () -> elemento2.buscar(3));
    }

    @Test
    public void InsertarElementoMenorTest() {
        boolean insertado = elemento2.insertar(1);
        assertTrue(insertado);
        assertNotNull(elemento2.getHijoIzquierdo());
        assertEquals(1, elemento2.getHijoIzquierdo().getDato());
    }

    @Test
    public void InsertarElementoMayorTest() {
        boolean insertado = elemento2.insertar(3);
        assertTrue(insertado);
        assertNotNull(elemento2.getHijoDerecho());
        assertEquals(3, elemento2.getHijoDerecho().getDato());
    }

    @Test
    public void InsertarElementoDuplicadoTest() {
        boolean insertado = elemento2.insertar(2);
        assertFalse(insertado);
        assertNull(elemento2.getHijoIzquierdo());
        assertNull(elemento2.getHijoDerecho());
    }

    @Test
    public void InsertarElementoMayorWhenRightSonAlreadyExistsTest() {
        elemento2.setHijoDerecho(elemento3);
        boolean insertado = elemento2.insertar(4);

        assertTrue(insertado);
        assertNotNull(elemento3.getHijoDerecho());
        assertEquals(4, elemento3.getHijoDerecho().getDato());
    }

    @Test
    public void InsertarElementoMenorWhenLeftSonAlreadyExistsTest() {
        elemento2.setHijoIzquierdo(elemento1);
        boolean insertado = elemento2.insertar(0);

        assertTrue(insertado);
        assertNotNull(elemento1.getHijoIzquierdo());
        assertEquals(0, elemento1.getHijoIzquierdo().getDato());
    }

    @Test
    public void EliminarElementoIsLeftSonTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.eliminar(1);
        assertNull(elemento2.getHijoIzquierdo());
    }

    @Test
    public void EliminarElementoIsRightSonTest() {
        elemento2.setHijoDerecho(elemento3);
        elemento2.eliminar(3);
        assertNull(elemento2.getHijoDerecho());
    }

    @Test
    public void EliminarElementoIsNotLeftSonTest() {
        assertNull(elemento2.getHijoIzquierdo());
        assertThrows(NoSuchElementException.class, () -> elemento2.eliminar(1));
    }

    @Test
    public void EliminarElementoIsNotRightSonTest() {
        assertNull(elemento2.getHijoDerecho());
        assertThrows(NoSuchElementException.class, () -> elemento2.eliminar(3));
    }

    @Test
    public void EliminarElementoNoExisteEnSubarbolTest() {
        elemento2.setHijoIzquierdo(elemento1);
        assertThrows(NoSuchElementException.class, () -> elemento2.eliminar(0));
    }

    @Test
    public void EliminarRaizWhenSinHijosTest() {
        TDAElemento<Integer> resultado = elemento2.eliminar(2);
        assertNull(resultado);
    }

    @Test
    public void EliminarRaizWithOnlyLeftSonTest() {
        elemento2.setHijoIzquierdo(elemento1);
        TDAElemento<Integer> resultado = elemento2.eliminar(2);

        assertNotNull(resultado);
        assertEquals(1, resultado.getDato());
        assertNull(resultado.getHijoIzquierdo());
        assertNull(resultado.getHijoDerecho());
    }

    @Test
    public void EliminarRaizWithOnlyRightSonTest() {
        elemento2.setHijoDerecho(elemento3);
        TDAElemento<Integer> resultado = elemento2.eliminar(2);

        assertNotNull(resultado);
        assertEquals(3, resultado.getDato());
        assertNull(resultado.getHijoIzquierdo());
        assertNull(resultado.getHijoDerecho());
    }

    @Test
    public void EliminarRaizWithTwoChildrenDirectPredecessorTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);

        TDAElemento<Integer> resultado = elemento2.eliminar(2);

        assertNotNull(resultado);
        assertEquals(1, resultado.getDato());
        assertNull(resultado.getHijoIzquierdo());
        assertNotNull(resultado.getHijoDerecho());
        assertEquals(3, resultado.getHijoDerecho().getDato());
    }

    @Test
    public void EliminarRaizWithTwoChildrenDeepPredecessorTest() {
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        ElementoArbol<Integer> elemento6 = new ElementoArbol<>(6);
        ElementoArbol<Integer> elemento5 = new ElementoArbol<>(5);

        elemento5.setHijoIzquierdo(elemento2);
        elemento5.setHijoDerecho(elemento6);
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento4);

        TDAElemento<Integer> resultado = elemento5.eliminar(5);

        assertEquals(4, resultado.getDato());
        assertEquals(2, resultado.getHijoIzquierdo().getDato());
        assertEquals(6, resultado.getHijoDerecho().getDato());
    }

    @Test
    public void EsHojaWhenNoTieneHijosTest() {
        assertTrue(elemento2.esHoja());
    }

    @Test
    public void EsHojaWhenTieneHijoIzquierdoTest() {
        elemento2.setHijoIzquierdo(elemento1);
        assertFalse(elemento2.esHoja());
    }

    @Test
    public void EsHojaWhenTieneSoloHijoDerechoTest() {
        elemento2.setHijoDerecho(elemento3);
        assertFalse(elemento2.esHoja());
    }

    @Test
    public void CantidadHojasWhenSoloRaizTest() {
        assertEquals(1, elemento2.cantidadHojas());
    }

    @Test
    public void CantidadHojasWhenTieneDosHijosHojaTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        assertEquals(2, elemento2.cantidadHojas());
    }

    @Test
    public void CantidadHojasWhenSoloSubArbolDerechoTest() {
        elemento2.setHijoDerecho(elemento3);
        assertEquals(1, elemento2.cantidadHojas());
    }

    @Test
    public void CantidadHojasWhenSoloSubArbolIzquierdoTest() {
        elemento2.setHijoIzquierdo(elemento1);
        assertEquals(1, elemento2.cantidadHojas());
    }

    @Test
    public void CantidadNodosInternosWhenSoloRaizTest() {
        assertEquals(0, elemento2.cantidadNodosInternos());
    }

    @Test
    public void CantidadNodosInternosWhenRaizConDosHijosTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        assertEquals(1, elemento2.cantidadNodosInternos());
    }

    @Test
    public void CantidadNodosWhenTresNodosTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        assertEquals(3, elemento2.cantidadNodos());
    }

    @Test
    public void ObtenerTamañoWhenTresNodosTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        assertEquals(3, elemento2.obtenerTamaño());
    }

    @Test
    public void AlturaWhenSoloRaizTest() {
        assertEquals(1, elemento2.altura());
    }

    @Test
    public void AlturaWhenTresNivelesTest() {
        ElementoArbol<Integer> elemento0 = new ElementoArbol<>(0);
        elemento2.setHijoIzquierdo(elemento1);
        elemento1.setHijoIzquierdo(elemento0);
        assertEquals(3, elemento2.altura());
    }

    @Test
    public void AlturaWhenTieneSubArbolDerechoTest() {
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        elemento2.setHijoDerecho(elemento3);
        elemento3.setHijoDerecho(elemento4);

        assertEquals(3, elemento2.altura());
    }

    @Test
    public void ObtenerNivelWhenEsRaizTest() {
        assertEquals(0, elemento2.obtenerNivel(2));
    }

    @Test
    public void ObtenerNivelWhenEsNietoIzquierdoTest() {
        ElementoArbol<Integer> elemento0 = new ElementoArbol<>(0);
        elemento2.setHijoIzquierdo(elemento1);
        elemento1.setHijoIzquierdo(elemento0);
        assertEquals(2, elemento2.obtenerNivel(0));
    }

    @Test
    public void ObtenerNivelWhenEsHijoIzquierdoTest() {
        ElementoArbol<Integer> elemento0 = new ElementoArbol<>(0);
        elemento2.setHijoIzquierdo(elemento1);
        assertEquals(1, elemento2.obtenerNivel(1));
    }
    @Test
    public void ObtenerNivelIsRightGrandSonTest() {
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        elemento2.setHijoDerecho(elemento3);
        elemento3.setHijoDerecho(elemento4);

        assertEquals(2, elemento2.obtenerNivel(4));
    }

    @Test
    public void ObtenerNivelWhenNoExisteTest() {
        assertEquals(-1, elemento2.obtenerNivel(99));
    }

    @Test
    public void InOrderTraversalTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);

        List<Integer> recorrido = new ArrayList<>();
        elemento2.inOrder(x -> recorrido.add(x.getDato()));

        assertEquals(1, recorrido.get(0));
        assertEquals(2, recorrido.get(1));
        assertEquals(3, recorrido.get(2));
    }

    @Test
    public void PreOrderTraversalTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);

        List<Integer> recorrido = new ArrayList<>();
        elemento2.preOrder(x -> recorrido.add(x.getDato()));

        assertEquals(2, recorrido.get(0));
        assertEquals(1, recorrido.get(1));
        assertEquals(3, recorrido.get(2));
    }

    @Test
    public void PostOrderTraversalTest() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);

        List<Integer> recorrido = new ArrayList<>();
        elemento2.postOrder(x -> recorrido.add(x.getDato()));

        List<Integer> recorrido2 = new ArrayList<>();


        assertEquals(1, recorrido.get(0));
        assertEquals(3, recorrido.get(1));
        assertEquals(2, recorrido.get(2));
    }

    @Test
    public void BuscarInEmptyTreeTest() {
        assertThrows(NoSuchElementException.class, () -> elemento2.buscar(1));
    }

    @Test
    public void BuscarCompletosInTreeWithOnlyRootCompletedTest(){
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento2.buscarCompletos(resultado);
        assertEquals(elemento2, resultado.head.dato());
        assertEquals(1, resultado.tamaño());
    }

    @Test
    public void BuscarCompletosInTreeWithMoreThanOneBranch(){
        ElementoArbol<Integer> elemento0 = new ElementoArbol<>(0);
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        ElementoArbol<Integer> elemento5 = new ElementoArbol<>(5);
        ElementoArbol<Integer> elemento6 = new ElementoArbol<>(6);
        elemento3.setHijoIzquierdo(elemento1);
        elemento3.setHijoDerecho(elemento5);
        elemento1.setHijoIzquierdo(elemento0);
        elemento1.setHijoDerecho(elemento2);
        elemento5.setHijoIzquierdo(elemento4);
        elemento5.setHijoDerecho(elemento6);
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento3.buscarCompletos(resultado);
        assertEquals(elemento3, resultado.head.dato());
        assertEquals(elemento1, resultado.head.siguiente.dato());
        assertEquals(elemento5, resultado.head.siguiente.siguiente.dato());
        assertEquals(3, resultado.tamaño());
    }

    @Test
    public void BuscarCompletosInTreeWithCompleteBranchInLeftBranches(){
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        ElementoArbol<Integer> elemento5 = new ElementoArbol<>(5);
        ElementoArbol<Integer> elemento6 = new ElementoArbol<>(6);
        elemento6.setHijoIzquierdo(elemento5);
        elemento5.setHijoIzquierdo(elemento4);
        elemento4.setHijoIzquierdo(elemento2);
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento6.buscarCompletos(resultado);
        assertEquals(1, resultado.tamaño());
        assertEquals(elemento2, resultado.head.dato());
    }

    @Test
    public void BuscarCompletosInTreeWithCompleteBranchInRightBranches(){
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        ElementoArbol<Integer> elemento5 = new ElementoArbol<>(5);
        ElementoArbol<Integer> elemento6 = new ElementoArbol<>(6);
        elemento1.setHijoDerecho(elemento2);
        elemento2.setHijoDerecho(elemento3);
        elemento3.setHijoDerecho(elemento5);
        elemento5.setHijoDerecho(elemento6);
        elemento5.setHijoIzquierdo(elemento4);
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento1.buscarCompletos(resultado);
        assertEquals(1, resultado.tamaño());
        assertEquals(elemento5, resultado.head.dato());
    }

    @Test
    public void BuscarEnNivelInEmptyTreeTest() {
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento2.buscarEnNivel(1, resultado);
        assertTrue(resultado.esVacio());
    }

    @Test
    public void BuscarEnNivel0Test() {
        elemento2.setHijoIzquierdo(elemento1);
        elemento2.setHijoDerecho(elemento3);
        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento2.buscarEnNivel(0, resultado);
        assertEquals(elemento2, resultado.head.dato());
        assertEquals(1, resultado.tamaño());
    }

    @Test
    public void buscarEnNivelInTreeWithMultipleLevelsTest() {
        ElementoArbol<Integer> elemento0 = new ElementoArbol<>(0);
        ElementoArbol<Integer> elemento4 = new ElementoArbol<>(4);
        ElementoArbol<Integer> elemento5 = new ElementoArbol<>(5);
        ElementoArbol<Integer> elemento6 = new ElementoArbol<>(6);
        elemento3.setHijoIzquierdo(elemento1);
        elemento3.setHijoDerecho(elemento5);
        elemento1.setHijoIzquierdo(elemento0);
        elemento1.setHijoDerecho(elemento2);
        elemento5.setHijoIzquierdo(elemento4);
        elemento5.setHijoDerecho(elemento6);

        ListaEnlazada<TDAElemento<Integer>> resultado = new ListaEnlazada<>();
        resultado =(ListaEnlazada<TDAElemento<Integer>>) elemento3.buscarEnNivel(2, resultado);

        assertEquals(4, resultado.tamaño());
        assertEquals(elemento0, resultado.head.dato());
        assertEquals(elemento2, resultado.head.siguiente.dato());
        assertEquals(elemento4, resultado.head.siguiente.siguiente.dato());
        assertEquals(elemento6, resultado.head.siguiente.siguiente.siguiente.dato());
    }
}
