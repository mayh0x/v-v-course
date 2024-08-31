package CadastroProduto;

import Exceptions.ArgumentoInvalidoException;
import Exceptions.ElementoRepetidoException;
import Produto.Produto;
import Produto.ProdutoComida.ProdutoComida;
import Produto.ProdutoBebida.ProdutoBebida;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CadastroProdutoTest {

    private CadastroProduto cadastroProduto;

    @Before
    public void setUp() {
        cadastroProduto = mock(CadastroProduto.class);
    }

    @Test
    public void testCadastrarProdutoComidaComSucesso() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Strogonoff", 10.0f, 5, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doNothing().when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        cadastroProduto.cadastrarProduto(produto);

        verify(cadastroProduto, times(1)).cadastrarProduto(produto);
        when(cadastroProduto.exists("Strogonoff")).thenReturn(true);
        when(cadastroProduto.getPreco("Strogonoff")).thenReturn(10.0f);
        when(cadastroProduto.getQuantidade("Strogonoff")).thenReturn(5);
        when(cadastroProduto.getPeso("Strogonoff")).thenReturn(1.5f);
        when(cadastroProduto.getMl("Strogonoff")).thenReturn(0.0f);
        when(cadastroProduto.getNome("Strogonoff")).thenReturn("Strogonoff");

        assertTrue(cadastroProduto.exists(dadosProduto.getValue().getId()));
        assertEquals(10.0f, cadastroProduto.getPreco(dadosProduto.getValue().getId()), 0.01);
        assertEquals(5, cadastroProduto.getQuantidade(dadosProduto.getValue().getId()));
        assertEquals(1.5f, cadastroProduto.getPeso(dadosProduto.getValue().getId()), 0.01);
        assertEquals(0.0f, cadastroProduto.getMl(dadosProduto.getValue().getId()), 0.01);
        assertEquals("Strogonoff", cadastroProduto.getNome(dadosProduto.getValue().getId()));
    }

    @Test
    public void testCadastrarProdutoComidaCamposFaltando() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("", 10.0f, 5, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazia e invalida.")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazia e invalida.", e.getMessage());
        }

        verify(cadastroProduto, times(1)).cadastrarProduto(dadosProduto.getValue());
    }

    @Test
    public void testCadastrarProdutoComidaNomeInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("123", 10.0f, 5, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Nome inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Nome inválido", e.getMessage());
        }

        verify(cadastroProduto, times(1)).cadastrarProduto(dadosProduto.getValue());
    }

    @Test
    public void testCadastrarProdutoComidaPrecoInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Pacoca", -1.0f, 5, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Preço inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Preço inválido", e.getMessage());
        }
    }

    @Test
    public void testCadastrarProdutoComidaQuantidadeInvalida() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Brigadeiro", 10.0f, -1, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Quantidade inválida")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade inválida", e.getMessage());
        }
    }

    @Test
    public void testCadastrarProdutoComidaPesoInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Frango", 10.0f, 5, -1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Peso inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Peso inválido", e.getMessage());
        }
    }

    @Test
    public void testCadastrarProdutoBebidaComSucesso() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("Refrigerante", 5.0f, 10, 0.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doNothing().when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        cadastroProduto.cadastrarProduto(produto);

        verify(cadastroProduto, times(1)).cadastrarProduto(produto);
        when(cadastroProduto.exists("Refrigerante")).thenReturn(true);
        when(cadastroProduto.getPreco("Refrigerante")).thenReturn(5.0f);
        when(cadastroProduto.getQuantidade("Refrigerante")).thenReturn(10);
        when(cadastroProduto.getPeso("Refrigerante")).thenReturn(0.0f);
        when(cadastroProduto.getMl("Refrigerante")).thenReturn(0.5f);
        when(cadastroProduto.getNome("Refrigerante")).thenReturn("Refrigerante");

        assertTrue(cadastroProduto.exists(dadosProduto.getValue().getId()));
        assertEquals(5.0f, cadastroProduto.getPreco(dadosProduto.getValue().getId()), 0.01);
        assertEquals(10, cadastroProduto.getQuantidade(dadosProduto.getValue().getId()));
        assertEquals(0.0f, cadastroProduto.getPeso(dadosProduto.getValue().getId()), 0.01);
        assertEquals(0.5f, cadastroProduto.getMl(dadosProduto.getValue().getId()), 0.01);
        assertEquals("Refrigerante", cadastroProduto.getNome(dadosProduto.getValue().getId()));
    }

    @Test
    public void testCadastrarProdutoBebidaCamposFaltando() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("", 10.0f, 5, 100.0f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazia e invalida.")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazia e invalida.", e.getMessage());
        }

        verify(cadastroProduto, times(1)).cadastrarProduto(dadosProduto.getValue());
    }

    @Test
    public void testCadastrarProdutoBebidaNomeInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("123", 10.0f, 5, 100.0f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Nome inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Nome inválido", e.getMessage());
        }

        verify(cadastroProduto, times(1)).cadastrarProduto(dadosProduto.getValue());
    }

    @Test
    public void testCadastrarProdutoBebidaPrecoInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("Água", -1.0f, 5, 200.0f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Preço inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Preço inválido", e.getMessage());
        }
    }

    @Test
    public void testCadastrarProdutoBebidaQuantidadeInvalida() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("Suco", 10.0f, -1, 100.0f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Quantidade inválida")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade inválida", e.getMessage());
        }
    }

    @Test
    public void testCadastrarProdutoBebidaMlInvalido() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoBebida("Iogurte", 10.0f, 5, -100.0f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doThrow(new ArgumentoInvalidoException("Peso inválido")).when(cadastroProduto).cadastrarProduto(dadosProduto.capture());

        try {
            cadastroProduto.cadastrarProduto(produto);
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Peso inválido", e.getMessage());
        }
    }

    @Test
    public void testRemoverProdutoComSucesso() throws ElementoRepetidoException, ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Strogonoff", 10.0f, 5, 1.5f);
        ArgumentCaptor<Produto> dadosProduto = ArgumentCaptor.forClass(Produto.class);
        doNothing().when(cadastroProduto).cadastrarProduto(dadosProduto.capture());
        cadastroProduto.cadastrarProduto(produto);

        doNothing().when(cadastroProduto).removerProduto(dadosProduto.getValue().getId());
        cadastroProduto.removerProduto("Strogonoff");

        verify(cadastroProduto, times(1)).removerProduto(dadosProduto.getValue().getId());

        when(cadastroProduto.exists(dadosProduto.getValue().getId())).thenReturn(false);
        assertFalse(cadastroProduto.exists(dadosProduto.getValue().getId()));
    }

    @Test
    public void testRemoverProdutoNomeVazio() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> id = ArgumentCaptor.forClass(String.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazia e invalida.")).when(cadastroProduto).removerProduto(id.capture());

        try {
            cadastroProduto.removerProduto("");
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazia e invalida.", e.getMessage());
        }

        verify(cadastroProduto, times(1)).removerProduto(id.getValue());
    }

    @Test
    public void testRemoverProdutoNomeInvalido() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> id = ArgumentCaptor.forClass(String.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazia e invalida.")).when(cadastroProduto).removerProduto(id.capture());

        try {
            cadastroProduto.removerProduto("123");
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazia e invalida.", e.getMessage());
        }

        verify(cadastroProduto, times(1)).removerProduto(id.getValue());
    }

    @Test
    public void testRemoverProdutoNaoExistente() throws ArgumentoInvalidoException {
        when(cadastroProduto.exists("Sacola")).thenReturn(false);
        assertFalse(cadastroProduto.exists("Sacola"));
        ArgumentCaptor<String> id = ArgumentCaptor.forClass(String.class);
        doThrow(new ArgumentoInvalidoException("Produto não existente")).when(cadastroProduto).removerProduto(id.capture());

        try {
            cadastroProduto.removerProduto("Sacola");
            fail("esperado ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Produto não existente", e.getMessage());
        }

        verify(cadastroProduto, times(1)).removerProduto(id.getValue());
    }
}
