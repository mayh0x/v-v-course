import React, { useState } from 'react';

interface Produto {
  id: string;
  tipo: 'comida' | 'bebida';
  preco: number;
  quantidade: number;
  peso?: number;
  ml?: number;
}

interface ListaProdutosProps {
  produtos: Produto[];
  onAdicionarQuantidade: (id: string, quantidade: number) => void;
  onRemoverQuantidade: (id: string, quantidade: number) => void;
  onRemoverProduto: (id: string) => void;
  onEditarProduto: (produtoEditado: Produto) => void;
  onLimparLista: () => void;
}

const ListaProdutos: React.FC<ListaProdutosProps> = ({
  produtos,
  onAdicionarQuantidade,
  onRemoverQuantidade,
  onRemoverProduto,
  onEditarProduto,
  onLimparLista,
}) => {
  const [quantidadePersonalizada, setQuantidadePersonalizada] = useState<{ [key: string]: number }>({});
  const [produtoEditando, setProdutoEditando] = useState<string | null>(null);
  const [produtoEditado, setProdutoEditado] = useState<Produto | null>(null);

  const handleInputChange = (id: string, value: number) => {
    setQuantidadePersonalizada({
      ...quantidadePersonalizada,
      [id]: value,
    });
  };

  const iniciarEdicao = (produto: Produto) => {
    if (produtoEditando === null) {
      setProdutoEditando(produto.id);
      setProdutoEditado({ ...produto });
    }
  };

  const handleEditarProduto = () => {
    if (produtoEditado) {
      onEditarProduto(produtoEditado);
      setProdutoEditando(null);
    }
  };

  const cancelarEdicao = () => {
    setProdutoEditando(null);
    setProdutoEditado(null);
  };

  return (
    <div className='flex justify-center'>
      <div className="w-full max-w-6xl mt-10 p-4 border rounded shadow">
        <h2 className="text-2xl font-bold mb-4 text-center text-white">Lista de Produtos</h2>
        {produtos.length === 0 ? (
          <p className="text-gray-400 text-center">Nenhum produto cadastrado.</p>
        ) : (
          <>
            <table className="min-w-full table-auto bg-white text-gray-900 border-collapse">
              <thead>
                <tr>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Nome</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Tipo</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Preço</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Quantidade</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Peso (g)</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Ml</th>
                  <th className="py-3 px-4 border-b border-gray-300 text-center">Ações</th>
                </tr>
              </thead>
              <tbody>
                {produtos.map((produto, index) => (
                  <React.Fragment key={index}>
                    <tr className="text-center border-t border-gray-200">
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">{produto.id}</td>
                      <td className="py-3 px-4 border-b border-gray-300 capitalize whitespace-nowrap">{produto.tipo}</td>
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">R$ {produto.preco.toFixed(2)}</td>
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">{produto.quantidade} un</td>
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">{produto.peso !== undefined ? produto.peso + ' g' : '-'}</td>
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">{produto.ml !== undefined ? produto.ml + ' ml' : '-'}</td>
                      <td className="py-3 px-4 border-b border-gray-300 whitespace-nowrap">
                        <div className="flex items-center justify-center space-x-2">
                          <input
                            type="number"
                            min="1"
                            value={quantidadePersonalizada[produto.id] || 1}
                            onChange={(e) => handleInputChange(produto.id, parseInt(e.target.value))}
                            className="w-16 p-1 text-center border border-gray-700 rounded bg-white"
                          />
                          <button
                            onClick={() => onAdicionarQuantidade(produto.id, quantidadePersonalizada[produto.id] || 1)}
                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded"
                          >
                            Adicionar
                          </button>
                          <button
                            onClick={() => onRemoverQuantidade(produto.id, quantidadePersonalizada[produto.id] || 1)}
                            className="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-1 px-2 rounded"
                          >
                            Remover
                          </button>
                          <button
                            onClick={() => iniciarEdicao(produto)}
                            className={`bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded ${produtoEditando ? 'opacity-50 cursor-not-allowed' : ''}`}
                            disabled={!!produtoEditando}
                          >
                            Editar
                          </button>
                          <button
                            onClick={() => onRemoverProduto(produto.id)}
                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded"
                          >
                            Remover Produto
                          </button>
                        </div>
                      </td>
                    </tr>

                    {produtoEditando === produto.id && (
                      <tr>
                        <td colSpan={7}>
                          <div className="p-4 bg-gray-100">
                            <fieldset>
                              <legend className="font-bold mb-2">Editar Produto</legend>
                              <div className="mb-2">
                                <h2 className="font-bold mb-1">Nome</h2>
                                <input
                                  type="text"
                                  value={produtoEditado?.id || ''}
                                  onChange={(e) =>
                                    setProdutoEditado((prev) => prev ? { ...prev, id: e.target.value } : null)
                                  }
                                  className="mr-2 p-1 border border-gray-300 rounded text-white"
                                  placeholder="ID"
                                />
                              </div>
                              <div className="mb-2">
                                <h2 className="font-bold mb-1">Preço</h2>
                                <input
                                  type="number"
                                  value={produtoEditado?.preco || 0}
                                  onChange={(e) =>
                                    setProdutoEditado((prev) => prev ? { ...prev, preco: parseFloat(e.target.value) } : null)
                                  }
                                  className="mr-2 p-1 border border-gray-300 rounded text-white"
                                  placeholder="Preço"
                                />
                              </div>
                              <div className="mb-2">
                                <h2 className="font-bold mb-1">Quantidade</h2>
                                <input
                                  type="number"
                                  value={produtoEditado?.quantidade || 0}
                                  onChange={(e) =>
                                    setProdutoEditado((prev) => prev ? { ...prev, quantidade: parseInt(e.target.value) } : null)
                                  }
                                  className="mr-2 p-1 border border-gray-300 rounded text-white"
                                  placeholder="Quantidade"
                                />
                              </div>
                              {produtoEditado?.tipo === 'comida' && (
                                <div className="mb-2">
                                  <h2 className="font-bold mb-1">Peso (g)</h2>
                                  <input
                                    type="number"
                                    value={produtoEditado?.peso || 0}
                                    onChange={(e) =>
                                      setProdutoEditado((prev) => prev ? { ...prev, peso: parseFloat(e.target.value) } : null)
                                    }
                                    className="mr-2 p-1 border border-gray-300 rounded text-white"
                                    placeholder="Peso (g)"
                                  />
                                </div>
                              )}
                              {produtoEditado?.tipo === 'bebida' && (
                                <div className="mb-2">
                                  <h2 className="font-bold mb-1">Ml</h2>
                                  <input
                                    type="number"
                                    value={produtoEditado?.ml || 0}
                                    onChange={(e) =>
                                      setProdutoEditado((prev) => prev ? { ...prev, ml: parseFloat(e.target.value) } : null)
                                    }
                                    className="mr-2 p-1 border border-gray-300 rounded text-white"
                                    placeholder="Ml"
                                  />
                                </div>
                              )}
                              <div className="flex space-x-2 mt-4">
                                <button
                                  onClick={handleEditarProduto}
                                  className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-4 rounded"
                                >
                                  Salvar
                                </button>
                                <button
                                  onClick={cancelarEdicao}
                                  className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-4 rounded"
                                >
                                  Cancelar
                                </button>
                              </div>
                            </fieldset>
                          </div>
                        </td>
                      </tr>
                    )}
                  </React.Fragment>
                ))}
              </tbody>
            </table>

            <div className="flex justify-end mt-4">
              <button
                onClick={onLimparLista}
                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
              >
                Limpar Lista
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default ListaProdutos;
