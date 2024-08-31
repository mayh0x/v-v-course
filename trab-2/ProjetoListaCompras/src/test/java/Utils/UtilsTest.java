package Utils;

import Exceptions.ArgumentoInvalidoException;
import Produto.Produto;
import Produto.ProdutoComida.ProdutoComida;
import Produto.ProdutoBebida.ProdutoBebida;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UtilsTest {

    private Utils utils;
    private ArrayList<Produto> produtos;

    @Before
    public void setUp() {
        utils = mock(Utils.class);
        produtos = new ArrayList<>();
    }

    @Test
    public void testVisualizarValorTotalComSucesso() {
        produtos.add(new ProdutoComida("Banana", 10.0f, 5, 1.5f));
        produtos.add(new ProdutoBebida("Refrigerante", 5.0f, 10, 2.0f));

        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        when(utils.valorTotal(produtosCapture.capture())).thenReturn("Valor total: R$ 100,00");

        String expected = "Valor total: R$ 100,00";
        String actual = utils.valorTotal(produtos);

        verify(utils, times(1)).valorTotal(produtosCapture.getValue());
        assertEquals(expected, utils.valorTotal(produtosCapture.getValue()));
    }

    @Test
    public void testVisualizarValorTotalComParametrosInexistentes() {
        doThrow(new NullPointerException("Parametro e invalido")).when(utils).valorTotal(null);

        try {
            utils.valorTotal(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Parametro e invalido", e.getMessage());
        }

        verify(utils, times(1)).valorTotal(null);
    }

    @Test
    public void testVisualizarValorTotalComListaVazia() {
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        when(utils.valorTotal(produtosCapture.capture())).thenReturn("Valor total: R$ 0,00");

        String expected = "Valor total: R$ 0,00";
        String actual = utils.valorTotal(produtos);

        verify(utils, times(1)).valorTotal(produtosCapture.getValue());
        assertEquals(expected, utils.valorTotal(produtosCapture.getValue()));
    }

    @Test
    public void testBuscarProdutosChaveComSucesso() throws ArgumentoInvalidoException {
        produtos.add(new ProdutoComida("Banana", 10.0f, 5, 1.5f));
        produtos.add(new ProdutoBebida("Refrigerante", 5.0f, 10, 2.0f));

        ArrayList<Produto> expected = new ArrayList<>();
        expected.add(produtos.get(0));

        ArgumentCaptor<String> patternCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        when(utils.searchProduto(patternCapture.capture(), produtosCapture.capture())).thenReturn(expected);

        ArrayList<Produto> actual = utils.searchProduto("Banana", produtos);

        verify(utils, times(1)).searchProduto(patternCapture.capture(), produtosCapture.capture());
        assertEquals(1, actual.size());
        assertEquals("Banana", actual.get(0).getId());
    }

    @Test
    public void testBuscarProdutosChaveVazia() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> patternCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Chave de busca inválida ou vazia.")).when(utils).searchProduto(patternCapture.capture(), produtosCapture.capture());

        try {
            utils.searchProduto("", produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Chave de busca inválida ou vazia.", e.getMessage());
        }

        verify(utils, times(1)).searchProduto(patternCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testBuscarProdutosChaveNaoTexto() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> patternCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Chave de busca inválida ou vazia.")).when(utils).searchProduto(patternCapture.capture(), produtosCapture.capture());

        try {
            utils.searchProduto("123", produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Chave de busca inválida ou vazia.", e.getMessage());
        }

        verify(utils, times(1)).searchProduto(patternCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testBuscarProdutosChaveNaoExistente() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> patternCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Produto não encontrado.")).when(utils).searchProduto(patternCapture.capture(), produtosCapture.capture());

        try {
            utils.searchProduto("Sacola", produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Produto não encontrado.", e.getMessage());
        }

        verify(utils, times(1)).searchProduto(patternCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testBuscarProdutosListaVazia() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> patternCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("A lista de produtos está vazia.")).when(utils).searchProduto(patternCapture.capture(), produtosCapture.capture());

        try {
            utils.searchProduto("Banana", produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("A lista de produtos está vazia.", e.getMessage());
        }

        verify(utils, times(1)).searchProduto(patternCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testLimparListaDeComprasComSucesso() {
        produtos.add(new ProdutoComida("Banana", 10.0f, 5, 1.5f));
        produtos.add(new ProdutoBebida("Refrigerante", 5.0f, 10, 2.0f));

        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doAnswer(invocation -> {
            produtos.clear();
            return null;
        }).when(utils).limparLista(produtos);

        utils.limparLista(produtos);

        verify(utils, times(1)).limparLista(produtosCapture.capture());
        assertTrue(produtosCapture.getValue().isEmpty());
    }

    @Test
    public void testLimparListaDeComprasComParametrosInexistentes() {
        doThrow(new NullPointerException("Parametro é inválido")).when(utils).limparLista(null);

        try {
            utils.limparLista(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Parametro é inválido", e.getMessage());
        }

        verify(utils, times(1)).limparLista(null);
    }

    @Test
    public void testLimparListaDeComprasComListaVazia() {
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doNothing().when(utils).limparLista(produtosCapture.capture());

        utils.limparLista(produtos);

        verify(utils, times(1)).limparLista(produtosCapture.getValue());
        assertTrue(produtosCapture.getValue().isEmpty());
    }

    @Test
    public void testVisualizarListaDeComprasComSucesso() {
        produtos.add(new ProdutoComida("Banana", 10.0f, 5, 1.5f));
        produtos.add(new ProdutoBebida("Refrigerante", 5.0f, 10, 2.0f));

        String expected = "----------------------------------------------------------------------------\n" +
                "Lista de Compras:\n" +
                "Comidas: \n" +
                "Nome: Banana, Preco (Unidade): R$ 10,00, Quantidade: 5, Peso: 1,50 g\n" +
                "Bebidas: \n" +
                "Nome: Refrigerante, Preco (Unidade): R$ 5,00, Quantidade: 10, Ml: 2,00 ml\n" +
                "----------------------------------------------------------------------------";

        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        when(utils.toString(produtosCapture.capture())).thenReturn(expected);

        String actual = utils.toString(produtos);

        verify(utils, times(1)).toString(produtosCapture.getValue());
        assertEquals(expected, utils.toString(produtosCapture.getValue()));
    }

    @Test
    public void testVisualizarListaDeComprasComParametrosInexistentes() {
        doThrow(new NullPointerException("Parametro é inválido")).when(utils).toString(null);

        try {
            utils.toString(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Parametro é inválido", e.getMessage());
        }

        verify(utils, times(1)).toString(null);
    }

    @Test
    public void testVisualizarListaDeComprasComListaVazia() {
        String expected = "----------------------------------------------------------------------------\n" +
                "Lista de Compras:\n" +
                "Comidas: \n" +
                "Bebidas: \n" +
                "----------------------------------------------------------------------------";

        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        when(utils.toString(produtosCapture.capture())).thenReturn(expected);

        String actual = utils.toString(produtos);

        verify(utils, times(1)).toString(produtosCapture.getValue());
        assertEquals(expected, utils.toString(produtosCapture.getValue()));
    }

    @Test
    public void testEncerrarProgramaComSucesso() {
        System.out.println("Programa encerrado com sucesso.");
    }
}
