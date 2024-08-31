package Produto.ProdutoComida;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProdutoComidaTest {

    private ProdutoComida produtoComida;

    @Before
    public void setUp() {
        produtoComida = mock(ProdutoComida.class);

        when(produtoComida.getType()).thenReturn("Comida");
        when(produtoComida.getPeso()).thenReturn(1000.0f);
        when(produtoComida.getMl()).thenReturn(0f);
        when(produtoComida.toString()).thenReturn("Nome: Arroz, Preco (Unidade): R$ 10,00, Quantidade: 5, Peso: 1000,00 g");
    }

    @Test
    public void testGetType() {
        assertEquals("Comida", produtoComida.getType());
        verify(produtoComida, times(1)).getType();
    }

    @Test
    public void testGetPeso() {
        assertEquals(1000.0f, produtoComida.getPeso(), 0.01);
        verify(produtoComida, times(1)).getPeso();
    }

    @Test
    public void testGetMl() {
        assertEquals(0f, produtoComida.getMl(), 0.01);
        verify(produtoComida, times(1)).getMl();
    }

    @Test
    public void testToString() {
        String expected = "Nome: Arroz, Preco (Unidade): R$ 10,00, Quantidade: 5, Peso: 1000,00 g";
        assertEquals(expected, produtoComida.toString());
    }
}
