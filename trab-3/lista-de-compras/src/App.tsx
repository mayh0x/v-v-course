import React, { useState } from 'react';
import CadastroProdutoForm from './components/CadastroProdutoForm';
import ListaProdutos from './components/ListaProdutos';

interface Produto {
  id: string;
  tipo: 'comida' | 'bebida';
  preco: number;
  quantidade: number;
  peso?: number;
  ml?: number;
}

const App: React.FC = () => {
  const [produtos, setProdutos] = useState<Produto[]>([]);

  const adicionarProduto = (produto: Produto) => {
    setProdutos([...produtos, produto]);
  };

  const adicionarQuantidade = (id: string, quantidade: number) => {
    setProdutos(
      produtos.map((produto) =>
        produto.id === id
          ? { ...produto, quantidade: produto.quantidade + quantidade }
          : produto
      )
    );
  };

  const removerQuantidade = (id: string, quantidade: number) => {
    setProdutos(
      produtos
        .map((produto) =>
          produto.id === id
            ? {
                ...produto,
                quantidade: Math.max(0, produto.quantidade - quantidade),
              }
            : produto
        )
        .filter((produto) => produto.quantidade > 0)
    );
  };

  const removerProduto = (id: string) => {
    setProdutos(produtos.filter((produto) => produto.id !== id));
  };

  const editarProduto = (produtoEditado: Produto) => {
    setProdutos(
      produtos.map((produto) =>
        produto.id === produtoEditado.id ? produtoEditado : produto
      )
    );
  };

  const limparLista = () => {
    setProdutos([]);
  };

  return (
    <div className="w-screen">
      <CadastroProdutoForm onAdicionarProduto={adicionarProduto} />
      
      <ListaProdutos
        produtos={produtos}
        onAdicionarQuantidade={adicionarQuantidade}
        onRemoverQuantidade={removerQuantidade}
        onRemoverProduto={removerProduto}
        onEditarProduto={editarProduto}
        onLimparLista={limparLista}
      />
    </div>
  );
};

export default App;
