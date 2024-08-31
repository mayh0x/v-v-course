package Utils;

import Exceptions.ArgumentoInvalidoException;
import Produto.Produto;

import java.util.ArrayList;

public interface Utils {
    public ArrayList<Produto> searchProduto(String pattern, ArrayList<Produto> produtos) throws ArgumentoInvalidoException;
    public String valorTotal(ArrayList<Produto> produtos);
    public void limparLista(ArrayList<Produto> produtos);
    public boolean exists(String id, ArrayList<Produto> produtos);
    public void show(ArrayList<Produto> produtos);
    public String comparaTipo(Produto produto);
    public String toString(ArrayList<Produto> produtos);
    public String floatFormatado(float aux);
}
