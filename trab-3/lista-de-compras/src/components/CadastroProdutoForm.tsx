// src/components/CadastroProdutoForm.tsx
import React, { useState } from 'react';

interface Produto {
  id: string;
  tipo: 'comida' | 'bebida';
  preco: number;
  quantidade: number;
  peso?: number;
  ml?: number;
}

interface CadastroProdutoFormProps {
  onAdicionarProduto: (produto: Produto) => void;
}

const CadastroProdutoForm: React.FC<CadastroProdutoFormProps> = ({ onAdicionarProduto }) => {
  const [id, setId] = useState('');
  const [tipo, setTipo] = useState<'comida' | 'bebida'>('comida');
  const [preco, setPreco] = useState<number | ''>('');
  const [quantidade, setQuantidade] = useState<number | ''>('');
  const [peso, setPeso] = useState<number | ''>('');
  const [ml, setMl] = useState<number | ''>('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Validação básica
    if (
      id.trim() === '' ||
      preco === '' ||
      quantidade === '' ||
      (tipo === 'comida' && peso === '') ||
      (tipo === 'bebida' && ml === '')
    ) {
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    const novoProduto: Produto = {
      id,
      tipo,
      preco: Number(preco),
      quantidade: Number(quantidade),
      ...(tipo === 'comida' ? { peso: Number(peso) } : { ml: Number(ml) }),
    };

    onAdicionarProduto(novoProduto);

    // Limpar os campos após o cadastro
    setId('');
    setTipo('comida');
    setPreco('');
    setQuantidade('');
    setPeso('');
    setMl('');
  };

  return (
    <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-10 p-4 border rounded shadow">
      <div className="mb-4">
        <h2 className="text-2xl font-bold mb-4 text-center text-white">Cadastro de Produtos</h2>
        <label className="block text-white text-sm font-bold mb-2">Nome</label>
        <input
          type="text"
          value={id}
          onChange={(e) => setId(e.target.value)}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-white"
          placeholder="Nome do Produto"
          required
        />
      </div>
      
      <div className="mb-4">
        <label className="block text-white text-sm font-bold mb-2">Tipo</label>
        <select
          value={tipo}
          onChange={(e) => setTipo(e.target.value as 'comida' | 'bebida')}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700"
          required
        >
          <option value="comida">Comida</option>
          <option value="bebida">Bebida</option>
        </select>
      </div>

      <div className="mb-4">
        <label className="block text-white text-sm font-bold mb-2">Preço</label>
        <input
          type="number"
          value={preco}
          onChange={(e) => setPreco(e.target.value === '' ? '' : parseFloat(e.target.value))}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-white"
          placeholder="Preço"
          required
          min="0"
          step="0.01"
        />
      </div>
      
      <div className="mb-4">
        <label className="block text-white text-sm font-bold mb-2">Quantidade</label>
        <input
          type="number"
          value={quantidade}
          onChange={(e) => setQuantidade(e.target.value === '' ? '' : parseInt(e.target.value))}
          className="shadow appearance-none border rounded w-full py-2 px-3 text-white"
          placeholder="Quantidade"
          required
          min="0"
        />
      </div>
      
      {tipo === 'comida' && (
        <div className="mb-4">
          <label className="block text-white text-sm font-bold mb-2">Peso (g)</label>
          <input
            type="number"
            value={peso}
            onChange={(e) => setPeso(e.target.value === '' ? '' : parseFloat(e.target.value))}
            className="shadow appearance-none border rounded w-full py-2 px-3 text-white"
            placeholder="Peso em gramas"
            required={tipo === 'comida'}
            min="0"
            step="0.1"
          />
        </div>
      )}
      
      {tipo === 'bebida' && (
        <div className="mb-4">
          <label className="block text-white text-sm font-bold mb-2">Ml</label>
          <input
            type="number"
            value={ml}
            onChange={(e) => setMl(e.target.value === '' ? '' : parseFloat(e.target.value))}
            className="shadow appearance-none border rounded w-full py-2 px-3 text-white"
            placeholder="Ml"
            required={tipo === 'bebida'}
            min="0"
            step="0.1"
          />
        </div>
      )}
      
      <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        Cadastrar Produto
      </button>
    </form>
  );
};

export default CadastroProdutoForm;
