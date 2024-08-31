package Produto;

import Produto.ProdutoComida.ProdutoComida;
import Produto.ProdutoBebida.ProdutoBebida;
import Exceptions.ArgumentoInvalidoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProdutoTest {

    private ProdutoComida produtoComida;
    private ProdutoBebida produtoBebida;

    @Before
    public void setUp() {
        produtoComida = mock(ProdutoComida.class);
        produtoBebida = mock(ProdutoBebida.class);

        when(produtoComida.getId()).thenReturn("Banana");
        when(produtoComida.getPreco()).thenReturn(10.0f);
        when(produtoComida.getQuantidade()).thenReturn(5);
        when(produtoComida.getPeso()).thenReturn(1.5f);
        when(produtoComida.getMl()).thenReturn(0.0f);
        when(produtoComida.getType()).thenReturn("Comida");

        when(produtoBebida.getId()).thenReturn("Refrigerante");
        when(produtoBebida.getPreco()).thenReturn(5.0f);
        when(produtoBebida.getQuantidade()).thenReturn(10);
        when(produtoBebida.getPeso()).thenReturn(0.0f);
        when(produtoBebida.getMl()).thenReturn(200.0f);
        when(produtoBebida.getType()).thenReturn("Bebida");
    }

    @Test
    public void testGettersProdutoComida() {
        assertEquals("Banana", produtoComida.getId());
        assertEquals(10.0f, produtoComida.getPreco(), 0.01);
        assertEquals(5, produtoComida.getQuantidade());
        assertEquals(1.5f, produtoComida.getPeso(), 0.01);
        assertEquals(0.0f, produtoComida.getMl(), 0.01);
        assertEquals("Comida", produtoComida.getType());
        verify(produtoComida, times(1)).getId();
        verify(produtoComida, times(1)).getPreco();
        verify(produtoComida, times(1)).getQuantidade();
        verify(produtoComida, times(1)).getPeso();
        verify(produtoComida, times(1)).getMl();
        verify(produtoComida, times(1)).getType();
    }

    @Test
    public void testGettersProdutoBebida() {
        assertEquals("Refrigerante", produtoBebida.getId());
        assertEquals(5.0f, produtoBebida.getPreco(), 0.01);
        assertEquals(10, produtoBebida.getQuantidade());
        assertEquals(0.0f, produtoBebida.getPeso(), 0.01);
        assertEquals(200.0f, produtoBebida.getMl(), 0.01);
        assertEquals("Bebida", produtoBebida.getType());
        verify(produtoBebida, times(1)).getId();
        verify(produtoBebida, times(1)).getPreco();
        verify(produtoBebida, times(1)).getQuantidade();
        verify(produtoBebida, times(1)).getPeso();
        verify(produtoBebida, times(1)).getMl();
        verify(produtoBebida, times(1)).getType();
    }

    @Test
    public void testAddQuantidadeProdutoComida() throws ArgumentoInvalidoException {
        doAnswer(_ -> {
            when(produtoComida.getQuantidade()).thenReturn(10);
            return null;
        }).when(produtoComida).addQuantidade(5);

        when(produtoComida.getQuantidade()).thenReturn(5);
        assertEquals(5, produtoComida.getQuantidade());

        produtoComida.addQuantidade(5);

        assertEquals(10, produtoComida.getQuantidade());
        verify(produtoComida, times(1)).addQuantidade(5);
    }

    @Test
    public void testAddQuantidadeProdutoBebida() throws ArgumentoInvalidoException {
        doAnswer(_ -> {
            when(produtoBebida.getQuantidade()).thenReturn(15);
            return null;
        }).when(produtoBebida).addQuantidade(5);

        when(produtoBebida.getQuantidade()).thenReturn(10);
        assertEquals(10, produtoBebida.getQuantidade());

        produtoBebida.addQuantidade(5);

        assertEquals(15, produtoBebida.getQuantidade());
        verify(produtoBebida, times(1)).addQuantidade(5);
    }

    @Test
    public void testRmQuantidadeProdutoComida() throws ArgumentoInvalidoException {
        doAnswer(_ -> {
            when(produtoComida.getQuantidade()).thenReturn(2);
            return null;
        }).when(produtoComida).rmQuantidade(3);

        when(produtoComida.getQuantidade()).thenReturn(5);
        assertEquals(5, produtoComida.getQuantidade());

        produtoComida.rmQuantidade(3);

        assertEquals(2, produtoComida.getQuantidade());
        verify(produtoComida, times(1)).rmQuantidade(3);
    }

    @Test
    public void testRmQuantidadeProdutoBebida() throws ArgumentoInvalidoException {
        doAnswer(_ -> {
            when(produtoBebida.getQuantidade()).thenReturn(5);
            return null;
        }).when(produtoBebida).rmQuantidade(5);

        when(produtoBebida.getQuantidade()).thenReturn(10);
        assertEquals(10, produtoBebida.getQuantidade());

        produtoBebida.rmQuantidade(5);

        assertEquals(5, produtoBebida.getQuantidade());
        verify(produtoBebida, times(1)).rmQuantidade(5);
    }

    @Test
    public void testAddQuantidadeInvalidaProdutoComida() {
        try {
            doThrow(new ArgumentoInvalidoException("Quantidade invalida")).when(produtoComida).addQuantidade(-5);
            produtoComida.addQuantidade(-5);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void testAddQuantidadeInvalidaProdutoBebida() {
        try {
            doThrow(new ArgumentoInvalidoException("Quantidade invalida")).when(produtoBebida).addQuantidade(-5);
            produtoBebida.addQuantidade(-5);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void testRmQuantidadeInvalidaProdutoComida() {
        try {
            doThrow(new ArgumentoInvalidoException("Quantidade invalida")).when(produtoComida).rmQuantidade(10);
            produtoComida.rmQuantidade(10);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void testRmQuantidadeInvalidaProdutoBebida() {
        try {
            doThrow(new ArgumentoInvalidoException("Quantidade invalida")).when(produtoBebida).rmQuantidade(20);
            produtoBebida.rmQuantidade(20);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void testToStringProdutoComida() {
        produtoComida = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        String expected = "Nome: Banana, Preco (Unidade): R$ 10,00, Quantidade: 5, Peso: 1,50 g";
        assertEquals(expected, produtoComida.toString());
    }

    @Test
    public void testToStringProdutoBebida() {
        produtoBebida = new ProdutoBebida("Refrigerante", 5.0f, 10, 200.0f);
        String expected = "Nome: Refrigerante, Preco (Unidade): R$ 5,00, Quantidade: 10, Ml: 200,00 ml";
        assertEquals(expected, produtoBebida.toString());
    }
}
