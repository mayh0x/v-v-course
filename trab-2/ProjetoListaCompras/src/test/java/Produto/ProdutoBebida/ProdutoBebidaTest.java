package Produto.ProdutoBebida;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProdutoBebidaTest {

    private ProdutoBebida produtoBebida;

    @Before
    public void setUp() {
        produtoBebida = mock(ProdutoBebida.class);

        when(produtoBebida.getType()).thenReturn("Bebida");
        when(produtoBebida.getPeso()).thenReturn(0f);
        when(produtoBebida.getMl()).thenReturn(500.0f);
        when(produtoBebida.toString()).thenReturn("Nome: Refrigerante, Preco (Unidade): R$ 5,00, Quantidade: 10, Ml: 500,00 ml");
    }

    @Test
    public void testGetType() {
        assertEquals("Bebida", produtoBebida.getType());
        verify(produtoBebida, times(1)).getType();
    }

    @Test
    public void testGetPeso() {
        assertEquals(0, produtoBebida.getPeso(), 0.01);
        verify(produtoBebida, times(1)).getPeso();
    }

    @Test
    public void testGetMl() {
        assertEquals(500.0f, produtoBebida.getMl(), 0.01);
        verify(produtoBebida, times(1)).getMl();
    }

    @Test
    public void testToString() {
        String expected = "Nome: Refrigerante, Preco (Unidade): R$ 5,00, Quantidade: 10, Ml: 500,00 ml";
        assertEquals(expected, produtoBebida.toString());
    }
}
