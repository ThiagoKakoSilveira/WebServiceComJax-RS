package br.com.alura.loja.modeloAuxiliar;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Dados")
public class MontaAdicCar {
	private long usuarioId;
	private long carrinhoId;
	@XStreamAlias("Produtos")
	private List<ProdutoMonta>produtos;
	
	public MontaAdicCar(long usuarioId, List<ProdutoMonta> produtos) {
		this.usuarioId = usuarioId;
		this.produtos = produtos;
	}
	
	public MontaAdicCar() {
		
	}

	public long getCarrinhoId() {
		return carrinhoId;
	}

	public void setCarrinhoId(long carrinhoId) {
		this.carrinhoId = carrinhoId;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<ProdutoMonta> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoMonta> produtos) {
		this.produtos = produtos;
	}
	
}
