package contratos;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modeloAuxiliar.MontaAdicCar;
import br.com.alura.loja.modeloAuxiliar.MontadorApresentacao;

public interface CarrinhoDaoInterface {
	
	public void inserir(MontaAdicCar car);
	
	public void deletar(Carrinho car);
	
	public void atualizar(Carrinho car);
	
	public List<Carrinho> listarCarrinhos();
	
	public MontadorApresentacao procurarPorCodigo(long id);

}
