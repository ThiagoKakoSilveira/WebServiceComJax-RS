package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Carrinho")
public class Montador {
	private long idCarrinho;
	private String nomeUsuario;
	private String email;
	@XStreamAlias("Produtos")
	private List<Produto>listaProduto;
	private double valorTotal;	
	
	
	public Montador(long idCarrinho, String nomeUsuario, String email, List<Produto> listaProduto) {
		listaProduto = new ArrayList<>();
		this.idCarrinho = idCarrinho;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.listaProduto = listaProduto;
		this.valorTotal = calcularQuantidade(listaProduto).doubleValue();
	}
	
	public Montador(long idCarrinho, String nomeUsuario, String email) {
		this.idCarrinho = idCarrinho;
		this.nomeUsuario = nomeUsuario;
		this.email = email;		
	}



	public long getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
		this.valorTotal = calcularQuantidade(listaProduto).doubleValue();
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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
