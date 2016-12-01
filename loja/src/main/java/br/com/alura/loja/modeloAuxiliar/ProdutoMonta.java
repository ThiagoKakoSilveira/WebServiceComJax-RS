package br.com.alura.loja.modeloAuxiliar;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Produto")
public class ProdutoMonta {
	private long id;
	private int quantidade;
	
	public ProdutoMonta(long id, int quantidade) {
		this.id = id;
		this.quantidade = quantidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
