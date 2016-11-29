package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Produto")
public class Produto {

	private long id;
	private String nome;
	private double preco;
	private int quantidade;
	
	public Produto(long id, String nome, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public double getPrecoTotal() {
		return quantidade * preco;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String toXML() {
		XStream xStream = new XStream();
		xStream.processAnnotations(Produto.class);
		return xStream.toXML(this);
	}
}
