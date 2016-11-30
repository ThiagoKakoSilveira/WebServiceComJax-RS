package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Carrinho")
public class Carrinho {

	private long id;
	private Usuario usuario;
	private List<Produto> produtos;
	private double valorTotal;
	
	public Carrinho(Usuario user, List<Produto>prods){
		produtos = new ArrayList<>();
		this.usuario = user;
		this.produtos = prods;
		this.valorTotal = calcularQuantidade(prods).doubleValue();
	}
		
	public Carrinho(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Produto> getProdutos() {
		return produtos;
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

//	public Carrinho setId(long id) {
//		this.id = id;
//		return this;
//	}

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
	
	private BigDecimal calcularQuantidade(List<Produto> lista){		
		BigDecimal valorTotal = new BigDecimal(0);
		for (Produto p : lista) {
			BigDecimal valorP = new BigDecimal(p.getPreco());
			valorP = valorP.multiply(new BigDecimal(p.getQuantidade()));
			valorTotal = valorTotal.add(valorP);
		}
		return valorTotal;
	}
		
	public String toXML() {
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		
		return xStream.toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}
