package EditaProduto;

import Exceptions.ArgumentoInvalidoException;
import Produto.Produto;

import java.util.ArrayList;

public interface EditaProduto {
    public void addQuantidade(String id, int quantidade, ArrayList<Produto> produtos) throws ArgumentoInvalidoException;
    public void rmQuantidade(String id, int quantidade, ArrayList<Produto> produtos) throws ArgumentoInvalidoException;
}
