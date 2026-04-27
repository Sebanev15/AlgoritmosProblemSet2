package ucu.edu.aed.tda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


public class ListaEnlazadaTest {

    ListaEnlazada<Integer> lista = new ListaEnlazada<>();
    @Test
    public void ConstructorTest(){

        assertNull(lista.head);
        assertEquals(0, lista.size);
    }

    @Test
    public void AddElementInEmptyListTest(){
        lista.agregar(5);
        assertEquals(5, lista.head.dato);
        assertEquals(1, lista.size);
        assertNull(lista.head.siguiente);
    }

    @Test
    public void AddElementInListWithOneElementTest(){
        lista.agregar(1);
        lista.agregar(2);
        assertEquals(1, lista.head.dato);
        assertEquals(2, lista.size);
        assertEquals(2, lista.head.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente);
    }

    @Test
    public void AddElementInListWithMoreThanOneElementTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(1, lista.head.dato);
        assertEquals(3, lista.size);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente.siguiente);
    }

    @Test
    public void AddElementWithOutOfRangeIndexInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.agregarConIndice(1,1));
    }

    @Test
    public void AddElementWithOutOfRangeIndexInListWithOneElementTest(){
        lista.agregar(1);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> lista.agregarConIndice(2,1));
        assertEquals("Indice fuera de rango", exception.getMessage());
    }

    @Test
    public void AddElementWithInRangeIndexInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(3);
        lista.agregarConIndice(1, 2);
        assertEquals(1, lista.head.dato);
        assertEquals(3, lista.size);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente.siguiente);
    }

    @Test
    public void AddElementWithZeroRangeInListWithElementsTest(){
        lista.agregar(2);
        lista.agregar(3);
        lista.agregarConIndice(0, 1);
        assertEquals(1, lista.head.dato);
        assertEquals(3, lista.size);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente.siguiente);
    }

    @Test
    public void AddElementWithInRangeIndexInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(5);
        lista.agregar(6);
        lista.agregarConIndice(3, 4);
        assertEquals(1, lista.head.dato);
        assertEquals(6, lista.size);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertEquals(4, lista.head.siguiente.siguiente.siguiente.dato);
        assertEquals(5, lista.head.siguiente.siguiente.siguiente.siguiente.dato);
        assertEquals(6, lista.head.siguiente.siguiente.siguiente.siguiente.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente.siguiente.siguiente.siguiente.siguiente);
    }

    @Test
    public void GetElementWithOutOfRangeIndexInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.obtener(0));
    }

    @Test
    public void GetElementWithOutOfRangeIndexInListWithOneElementTest(){
        lista.agregar(1);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(1));
    }

    @Test
    public void GetElementInRangeIndexInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(1, lista.obtener(0));
        assertEquals(2, lista.obtener(1));
        assertEquals(3, lista.obtener(2));
    }

    @Test
    public void GetElementWithOutOfRangeIndexInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> lista.obtener(5));
    }

    @Test
    public void GetFirstElementInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(1, lista.obtener(0));
    }

    @Test
    public void GetFirstElementInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.obtener(0));
    }

    @Test
    public void RemoveElementWithOutOfRangeIndexInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.remover(0));
    }

    @Test
    public void RemoveElementWithOutOfRangeIndexInListWithOneElementTest(){
        lista.agregar(1);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> lista.remover(2));
    }
    @Test
    public void RemoveElementInRangeIndexInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(2, lista.remover(1));
        assertEquals(1, lista.head.dato);
        assertEquals(3, lista.head.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente);
    }

    @Test
    public void RemoveElementWithOutOfRangeIndexInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> lista.remover(5));
    }

    @Test
    public void RemoveFirstElementInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(1, lista.remover(0));
        assertEquals(2, lista.head.dato);
        assertEquals(3, lista.head.siguiente.dato);
        assertNull(lista.head.siguiente.siguiente);
    }

    @Test
    public void RemoveFirstElementInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.remover(0));
    }

    @Test
    public void RemoveElementIsInListFirstElmentTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertTrue(lista.remover(Integer.valueOf(1))); //Se hacer el Integer.valueOf para que sepa que es un objeto y no un indice.
        assertEquals(2, lista.head.dato);
        assertEquals(3, lista.head.siguiente.dato);
        assertEquals(2, lista.size);
        assertNull(lista.head.siguiente.siguiente);
    }

    @Test
    public void RemoveElementIsInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertTrue(lista.remover(Integer.valueOf(2))); //Se hacer el Integer.valueOf para que sepa que es un objeto y no un indice.
        assertEquals(1, lista.head.dato);
        assertEquals(3, lista.head.siguiente.dato);
        assertEquals(2, lista.size);
    }

    @Test
    public void RemoveElementIsInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        lista.agregar(6);
        assertTrue(lista.remover(Integer.valueOf(4))); //Se hacer el Integer.valueOf para que sepa que es un objeto y no un indice.
        assertEquals(1, lista.head.dato);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertEquals(5, lista.head.siguiente.siguiente.siguiente.dato);
        assertEquals(6, lista.head.siguiente.siguiente.siguiente.siguiente.dato);
        assertEquals(5, lista.size);
    }

    @Test
    public void RemoveElementInEmptyList(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.remover(Integer.valueOf(1))); //Se hacer el Integer.valueOf para que sepa que es un objeto y no un indice.
    }

    @Test
    public void RemoveElementIsNotInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertFalse(lista.remover(Integer.valueOf(4))); //Se hacer el Integer.valueOf para que sepa que es un objeto y no un indice.
        assertEquals(1, lista.head.dato);
        assertEquals(2, lista.head.siguiente.dato);
        assertEquals(3, lista.head.siguiente.siguiente.dato);
        assertEquals(3, lista.size);
    }

    @Test
    public void ContainsElementWithReturnIsInFirstElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(lista.head,lista.contieneNodo(1));
    }

    @Test
    public void ContainsElementWithReturnInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(lista.head.siguiente,lista.contieneNodo(2));
    }

    @Test
    public void ContainsElementWithReturnIsInLastElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(lista.head.siguiente.siguiente,lista.contieneNodo(3));
    }

    @Test
    public void ContainsElementWithReturnInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        lista.agregar(6);
        lista.agregar(7);

        Nodo<Integer> sextoNodo = lista.head.siguiente.siguiente.siguiente.siguiente.siguiente;
        assertEquals(sextoNodo, lista.contieneNodo(6));
    }

    @Test
    public void NotContainsElementWithReturnInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        assertNull(lista.contieneNodo(3));
    }

    @Test
    public void ContainsElementWithReturnInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.contieneNodo(1));
    }

    @Test
    public void ContainsElementIsInFirstElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertTrue(lista.contiene(1));
    }

    @Test
    public void ContainsElementInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertTrue(lista.contiene(2));
    }

    @Test
    public void ContainsElementIsInLastElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertTrue(lista.contiene(3));
    }

    @Test
    public void ContainsElementInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        lista.agregar(6);
        lista.agregar(7);
        assertTrue(lista.contiene(6));
    }

    @Test
    public void NotContainsElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        assertFalse(lista.contiene(3));
    }

    

    @Test
    public void IndexOfElementIsInFirstElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(0, lista.indiceDe(1));
    }

    @Test
    public void IndexOfElementInListWithElementsTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(1, lista.indiceDe(2));
    }

    @Test
    public void IndexOfElementIsInLastElementInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(2, lista.indiceDe(3));
    }

    @Test
    public void IndexOfElementInLargeListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);
        lista.agregar(6);
        lista.agregar(7);
        assertEquals(5, lista.indiceDe(6));
    }

    @Test
    public void IndexOfElementNotInListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals(-1, lista.indiceDe(4));
    }

    @Test
    public void IndexOfElementInEmptyListTest(){
        Throwable exception = assertThrows(NullPointerException.class, () -> lista.indiceDe(1));
    }

    @Test
    public void SearchElementInEmptyListTest() {
        assertNull(lista.buscar(x -> x.equals(10)));
    }

    @Test
    public void SearchElementInListWithOneElementTest() {
        lista.agregar(10);
        assertEquals(10, lista.buscar(x -> x.equals(10)));
    }

    @Test
    public void SearchElementNotInListTest() {
        lista.agregar(10);
        assertNull(lista.buscar(x -> x.equals(20)));
    }

    @Test
    public void SearchFirstMatchingElementInListTest() {
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);

        assertEquals(2, lista.buscar(x -> x % 2 == 0));
    }

    @Test
    public void SearchLastMatchingElementInListTest() {
        lista.agregar(1);
        lista.agregar(3);
        lista.agregar(5);
        lista.agregar(8);

        assertEquals(8, lista.buscar(x -> x > 6));
    }

    @Test
    public void SearchWithMultipleMatchingElementsReturnsFirstTest() {
        lista.agregar(2);
        lista.agregar(4);
        lista.agregar(6);
        lista.agregar(8);

        assertEquals(2, lista.buscar(x -> x % 2 == 0));
    }

    @Test
    public void SearchWhenNoElementMatchesTest() {
        lista.agregar(1);
        lista.agregar(3);
        lista.agregar(5);

        assertNull(lista.buscar(x -> x % 2 == 0));
    }

    @Test
    public void SearchWithAlwaysTruePredicateTest() {
        lista.agregar(7);
        lista.agregar(8);
        lista.agregar(9);

        assertEquals(7, lista.buscar(x -> true));
    }

    @Test
    public void SearchWithAlwaysFalsePredicateTest() {
        lista.agregar(7);
        lista.agregar(8);
        lista.agregar(9);

        assertNull(lista.buscar(x -> false));
    }

    @Test
    public void OrderElementsInEmptyListTest() {
        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.naturalOrder());

        assertTrue(listaOrdenada.esVacio());
        assertEquals(0, listaOrdenada.tamaño());
    }

    @Test
    public void OrderElementsInListWithOneElementTest() {
        lista.agregar(5);

        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.naturalOrder());

        assertEquals(1, listaOrdenada.tamaño());
        assertEquals(5, listaOrdenada.obtener(0));
    }

    @Test
    public void OrderElementsInListWithElementsTest() {
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);

        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.naturalOrder());

        assertEquals(3, listaOrdenada.tamaño());
        assertEquals(1, listaOrdenada.obtener(0));
        assertEquals(2, listaOrdenada.obtener(1));
        assertEquals(3, listaOrdenada.obtener(2));
    }

    @Test
    public void OrderElementsInListWithRepeatedElementsTest() {
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(1);
        lista.agregar(2);

        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.naturalOrder());

        assertEquals(5, listaOrdenada.tamaño());
        assertEquals(1, listaOrdenada.obtener(0));
        assertEquals(1, listaOrdenada.obtener(1));
        assertEquals(2, listaOrdenada.obtener(2));
        assertEquals(2, listaOrdenada.obtener(3));
        assertEquals(3, listaOrdenada.obtener(4));
    }

    @Test
    public void OrderElementsInListInReverseOrderTest() {
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);

        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.reverseOrder());

        assertEquals(4, listaOrdenada.tamaño());
        assertEquals(4, listaOrdenada.obtener(0));
        assertEquals(3, listaOrdenada.obtener(1));
        assertEquals(2, listaOrdenada.obtener(2));
        assertEquals(1, listaOrdenada.obtener(3));
    }

    @Test
    public void OrderElementsDoesNotModifyOriginalListTest() {
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);

        TDALista<Integer> listaOrdenada = lista.ordenar(Comparator.naturalOrder());

        // La lista original debe seguir igual
        assertEquals(3, lista.obtener(0));
        assertEquals(1, lista.obtener(1));
        assertEquals(2, lista.obtener(2));

        // La lista ordenada debe estar correcta
        assertEquals(1, listaOrdenada.obtener(0));
        assertEquals(2, listaOrdenada.obtener(1));
        assertEquals(3, listaOrdenada.obtener(2));
    }

    @Test
    public void IsEmptyInEmptyListTest(){
        assertTrue(lista.esVacio());
    }

    @Test
    public void IsEmptyInNotEmptyListTest(){
        lista.agregar(1);
        assertFalse(lista.esVacio());
    }

    @Test
    public void MakeEmptyInNotEmptyListTest(){
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.vaciar();
        assertTrue(lista.esVacio());
    }

    @Test
    public void MakeEmptyInEmptyListTest(){
        assertTrue(lista.esVacio());
        lista.vaciar();
        assertTrue(lista.esVacio());
    }

    @Test
    public void MakeEmptyInListWithOneElementTest(){
        lista.agregar(1);
        lista.vaciar();
        assertTrue(lista.esVacio());
    }

}
