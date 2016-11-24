package br.com.alura.loja.daoBD;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;

public interface CarrinhoDaoInterface {
	
	public void inserir(Carrinho car);
	
	public void deletar(Carrinho car);
	
	public void atualizar(Carrinho car);
	
	public List<Carrinho> listarCarrinhos();
	
	public Carrinho procurarPorCodigo(int id);

}
