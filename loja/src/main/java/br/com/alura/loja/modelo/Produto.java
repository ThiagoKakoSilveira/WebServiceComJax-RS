package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.XStream;

//1ª Anotação diz para o JAXB q esse objeto é serializável
//2ª Anotação diz q todos o campos serão serializados
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Produto {

	private double preco;
	private long id;
	private String nome;
	private int quantidade;
	
	public Produto(long id, String nome, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	//O JAXB exige um construtor sem argumentos para serializar os objetos
	public Produto(){
		
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
		return new XStream().toXML(this);
	}
}
