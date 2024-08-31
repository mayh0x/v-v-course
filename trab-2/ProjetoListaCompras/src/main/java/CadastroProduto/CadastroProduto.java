package CadastroProduto;

import Exceptions.ArgumentoInvalidoException;
import Exceptions.ElementoRepetidoException;
import Produto.Produto;

import java.util.ArrayList;

public interface CadastroProduto {
    public void cadastrarProduto(Produto p) throws ElementoRepetidoException, ArgumentoInvalidoException;
    public void removerProduto(String id) throws ArgumentoInvalidoException;
    public boolean exists(String id);
    public float getPreco(String id) throws ArgumentoInvalidoException;
    public int getQuantidade(String id) throws ArgumentoInvalidoException;
    public float getPeso(String id) throws ArgumentoInvalidoException;
    public float getMl(String id) throws ArgumentoInvalidoException;
    public String getNome(String id) throws ArgumentoInvalidoException;
}
