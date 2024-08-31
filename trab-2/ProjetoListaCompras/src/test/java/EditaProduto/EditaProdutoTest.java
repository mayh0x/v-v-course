package EditaProduto;

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

public class EditaProdutoTest {

    private EditaProduto editaProduto;
    private ArrayList<Produto> produtos;

    @Before
    public void setUp() {
        editaProduto = mock(EditaProduto.class);
        produtos = new ArrayList<>();
    }

    @Test
    public void testAddQuantidadeComidaComSucesso() throws ArgumentoInvalidoException {
        ProdutoComida produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        produtos.add(produto);

        doAnswer(_ -> {
            produto.addQuantidade(quantidadeCapture.getValue());
            return null;
        }).when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        editaProduto.addQuantidade("Banana", 5, produtos);

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
        ArrayList<Produto> capturedProdutos = produtosCapture.getValue();
        Produto capturedProduto = capturedProdutos.get(0);
        assertEquals(10, capturedProduto.getQuantidade());
    }

    @Test
    public void testAddQuantidadeBebidaComSucesso() throws ArgumentoInvalidoException {
        ProdutoBebida produto = new ProdutoBebida("Refrigerante", 10.0f, 5, 100.0f);
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        produtos.add(produto);

        doAnswer(_ -> {
            produto.addQuantidade(quantidadeCapture.getValue());
            return null;
        }).when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        editaProduto.addQuantidade("Refrigerante", 3, produtos);

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
        ArrayList<Produto> capturedProdutos = produtosCapture.getValue();
        Produto capturedProduto = capturedProdutos.get(0);
        assertEquals(8, capturedProduto.getQuantidade());
    }

    @Test
    public void testAddQuantidadeParametrosVazios() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazio.")).when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.addQuantidade("", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazio.", e.getMessage());
        }

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testAddQuantidadeNomeNaoTexto() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID inválido.")).when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.addQuantidade("123", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID inválido.", e.getMessage());
        }

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testAddQuantidadeNaoInteira() {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);
        try {
            editaProduto.addQuantidade("Banana", Integer.parseInt("abc"), produtos);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException | ArgumentoInvalidoException e) {
            assertTrue(e instanceof NumberFormatException);
        }
    }

    @Test
    public void testAddQuantidadeProdutoNaoExistente() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("O produto com esse ID nao foi encontrado na lista de compras."))
                .when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.addQuantidade("Sacola", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("O produto com esse ID nao foi encontrado na lista de compras.", e.getMessage());
        }

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testAddQuantidadeNegativa() throws ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doThrow(new ArgumentoInvalidoException("A quantidade passada deve ser inteira não vazia."))
                .when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.addQuantidade("Banana", -5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("A quantidade passada deve ser inteira não vazia.", e.getMessage());
        }

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testAddQuantidadeExtremamenteGrande() throws ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doAnswer(_ -> {
            produto.addQuantidade(quantidadeCapture.getValue());
            return null;
        }).when(editaProduto).addQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        editaProduto.addQuantidade("Banana", Integer.MAX_VALUE, produtos);

        verify(editaProduto, times(1)).addQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
        ArrayList<Produto> capturedProdutos = produtosCapture.getValue();
        Produto capturedProduto = capturedProdutos.get(0);
        assertEquals(Integer.MAX_VALUE + 5, capturedProduto.getQuantidade());
    }

    @Test
    public void testRmQuantidadeComidaComSucesso() throws ArgumentoInvalidoException {
        ProdutoComida produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doAnswer(_ -> {
            produto.rmQuantidade(quantidadeCapture.getValue());
            return null;
        }).when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        editaProduto.rmQuantidade("Banana", 3, produtos);

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
        ArrayList<Produto> capturedProdutos = produtosCapture.getValue();
        Produto capturedProduto = capturedProdutos.get(0);
        assertEquals(2, capturedProduto.getQuantidade());
    }

    @Test
    public void testRmQuantidadeBebidaComSucesso() throws ArgumentoInvalidoException {
        ProdutoBebida produto = new ProdutoBebida("Refrigerante", 10.0f, 5, 100.0f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doAnswer(_ -> {
            produto.rmQuantidade(quantidadeCapture.getValue());
            return null;
        }).when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        editaProduto.rmQuantidade("Refrigerante", 2, produtos);

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
        ArrayList<Produto> capturedProdutos = produtosCapture.getValue();
        Produto capturedProduto = capturedProdutos.get(0);
        assertEquals(3, capturedProduto.getQuantidade());
    }

    @Test
    public void testRmQuantidadeParametrosVazios() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID vazio.")).when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.rmQuantidade("", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID vazio.", e.getMessage());
        }

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testRmQuantidadeNomeNaoTexto() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("Passagem de parametro de ID inválido.")).when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.rmQuantidade("123", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("Passagem de parametro de ID inválido.", e.getMessage());
        }

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testRmQuantidadeNaoInteira() {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);
        try {
            editaProduto.rmQuantidade("Banana", Integer.parseInt("abc"), produtos);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException | ArgumentoInvalidoException e) {
            assertTrue(e instanceof NumberFormatException);
        }
    }

    @Test
    public void testRmQuantidadeProdutoNaoExistente() throws ArgumentoInvalidoException {
        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);
        doThrow(new ArgumentoInvalidoException("O produto com esse ID nao foi encontrado na lista de compras."))
                .when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.rmQuantidade("Sacola", 5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("O produto com esse ID nao foi encontrado na lista de compras.", e.getMessage());
        }

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testRmQuantidadeNegativa() throws ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doThrow(new ArgumentoInvalidoException("A quantidade passada deve ser inteira não vazia."))
                .when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.rmQuantidade("Banana", -5, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("A quantidade passada deve ser inteira não vazia.", e.getMessage());
        }

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }

    @Test
    public void testRmQuantidadeMaiorQueExistente() throws ArgumentoInvalidoException {
        Produto produto = new ProdutoComida("Banana", 10.0f, 5, 1.5f);
        produtos.add(produto);

        ArgumentCaptor<String> idCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantidadeCapture = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ArrayList> produtosCapture = ArgumentCaptor.forClass(ArrayList.class);

        doThrow(new ArgumentoInvalidoException("A quantidade passada deve ser igual ou menor que a quantidade em estoque."))
                .when(editaProduto).rmQuantidade(idCapture.capture(), quantidadeCapture.capture(), produtosCapture.capture());

        try {
            editaProduto.rmQuantidade("Banana", 6, produtos);
            fail("Expected ArgumentoInvalidoException");
        } catch (ArgumentoInvalidoException e) {
            assertEquals("A quantidade passada deve ser igual ou menor que a quantidade em estoque.", e.getMessage());
        }

        verify(editaProduto, times(1)).rmQuantidade(idCapture.getValue(), quantidadeCapture.getValue(), produtosCapture.getValue());
    }
}
