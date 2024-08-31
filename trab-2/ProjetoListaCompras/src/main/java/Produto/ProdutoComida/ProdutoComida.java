package Produto.ProdutoComida;

import Exceptions.ArgumentoInvalidoException;
import Produto.Produto;
import Produto.TipoProduto;

public class ProdutoComida extends Produto implements TipoProduto {

	public String type;
	public float peso;

	public ProdutoComida(String id, float preco, int quantidade, float peso) {
		super(id, preco, quantidade, peso, 0);
		this.peso = peso;
		this.type = "Comida";
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public float getPeso() {
		return peso;
	}

	@Override
	public float getMl() {
		return 0;
	}

	@Override
	public String toString() {
		return "Nome: " + getId() + ", Preco (Unidade): R$ " + floatFormatado(getPreco()) + ", Quantidade: " + getQuantidade() + ", Peso: " + floatFormatado(getPeso()) + " g";
	}

	public String floatFormatado(float aux) {
		return String.format("%.02f", aux);
	}
}
