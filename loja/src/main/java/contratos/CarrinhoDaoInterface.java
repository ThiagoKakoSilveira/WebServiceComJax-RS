package contratos;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Montador;

public interface CarrinhoDaoInterface {
	
	public void inserir(Carrinho car);
	
	public void deletar(Carrinho car);
	
	public void atualizar(Carrinho car);
	
	public List<Carrinho> listarCarrinhos();
	
	public Montador procurarPorCodigo(long id);

}
