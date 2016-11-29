package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Carrinho")
public class Carrinho {

	private long id;
	private String cidade;
	private String rua;
	
	private Collection<Produto> produtos = new ArrayList<Produto>();
	private double valorTotal;
	
	public Carrinho(List<Produto> produtos, String rua, String cidade, long id) {
		this.produtos = produtos;
		this.rua = rua;
		this.cidade = cidade;
		this.id = id;
		this.valorTotal = calcularQuantidade(produtos).doubleValue();
	}	
	
	public Carrinho(Carrinho car){
		this.id = car.getId();
		this.rua = car.getRua();
		this.cidade = car.getCidade();		
		this.valorTotal = calcularQuantidade(car.getProdutos()).doubleValue();		
	}
	
	public Carrinho(){
		
	}
	
	public void adiciona(Produto produto){
		boolean add = false;
		for (Produto p : produtos) {
			if(p.getId()==produto.getId()){
				add = true;
				p.setQuantidade((p.getQuantidade() + produto.getQuantidade()));
				troca(p);
			}			
		}
		if(!add){
			this.produtos.add(produto);
		}		
	}

	public Carrinho setId(long id) {
		this.id = id;
		return this;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public long getId() {
		return id;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void remove(long id) {
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext();) {
			Produto produto = (Produto) iterator.next();
			if(produto.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void troca(Produto produto) {
		remove(produto.getId());
		adiciona(produto);
	}

	public void trocaQuantidade(Produto produto) {
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext();) {
			Produto p = (Produto) iterator.next();
			if(p.getId() == produto.getId()) {
				p.setQuantidade(produto.getQuantidade());
				return;
			}
		}
	}
	
	private BigDecimal calcularQuantidade(Collection<Produto> lista){		
		BigDecimal valorTotal = new BigDecimal(0);
		for (Produto p : lista) {
			BigDecimal valorP = new BigDecimal(p.getPreco());
			valorP.multiply(new BigDecimal(p.getQuantidade()));
			valorTotal = valorTotal.add(valorP);
		}
		return valorTotal;
	}
	
	public Collection<Produto> getProdutos() {
		return produtos;
	}
	
	public String toXML() {
		XStream xStream = new XStream();
		xStream.processAnnotations(Carrinho.class);
		xStream.processAnnotations(Produto.class);
//		xStream.autodetectAnnotations(true);
		xStream.alias("Produtos", Collection.class);
		return xStream.toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
