package Produto.ProdutoBebida;

import Produto.Produto;
import Produto.TipoProduto;

public class ProdutoBebida extends Produto implements TipoProduto {

	public String type;
	public float ml;

	public ProdutoBebida(String id, float preco, int quantidade, float ml) {
		super(id, preco, quantidade, 0, ml);
		this.ml = ml;
		this.type = "Bebida";
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public float getPeso() {
		return 0;
	}

	@Override
	public float getMl() {
		return ml;
	}

	@Override
	public String toString() {
		return "Nome: " + getId() + ", Preco (Unidade): R$ " + floatFormatado(getPreco()) + ", Quantidade: " + getQuantidade() + ", Ml: " + floatFormatado(getMl()) + " ml";
	}

	public String floatFormatado(float aux) {
		return String.format("%.02f", aux);
	}
}
