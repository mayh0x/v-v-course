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
}

const ListaProdutos: React.FC<ListaProdutosProps> = ({
  produtos,
  onAdicionarQuantidade,
  onRemoverQuantidade,
  onRemoverProduto,
}) => {
  const [quantidadePersonalizada, setQuantidadePersonalizada] = useState<{ [key: string]: number }>({});

  const handleInputChange = (id: string, value: number) => {
    setQuantidadePersonalizada({
      ...quantidadePersonalizada,
      [id]: value,
    });
  };

  return (
    <div className='flex justify-center'>
      <div className="w-full max-w-6xl mt-10 p-4 border rounded shadow">
        <h2 className="text-2xl font-bold mb-4 text-center text-white">Lista de Produtos</h2>
        {produtos.length === 0 ? (
          <p className="text-gray-400 text-center">Nenhum produto cadastrado.</p>
        ) : (
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
                <tr key={index} className="text-center border-t border-gray-200">
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
                        onClick={() => onRemoverProduto(produto.id)}
                        className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded"
                      >
                        Remover Produto
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default ListaProdutos;
