package br.com.alura.loja.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoDAO {
	
	private static Map<Long, Carrinho> banco = new HashMap<Long, Carrinho>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		java.util.List<Produto> produtos = new ArrayList<>();
		Produto videogame = new Produto(6237, "Playstation 4", 4000, 1);
		Produto esporte = new Produto(3467, "FIFA 16", 60, 2);
		produtos.add(esporte);
		produtos.add(videogame);
		Carrinho carrinho = new Carrinho(produtos, "Rua Santo Antonio 347", "Manaus", 1l);								
		banco.put(1l, carrinho);
	}
	
	public void adiciona(Carrinho carrinho) {
		long id = contador.incrementAndGet();
		carrinho.setId(id);
		banco.put(id, carrinho);
	}
	
	public Carrinho busca(Long id) {
		return banco.get(id);
	}
	
	public Carrinho remove(long id) {
		return banco.remove(id);
	}

}
