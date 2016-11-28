package br.com.alura.loja.daoBD;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarrinhoDaoBD implements CarrinhoDaoInterface {

	private Connection conexao;
	private PreparedStatement comando;

	@Override
	public void inserir(Carrinho car) {
		long id;
		try {
			String sql = "INSERT INTO CARRINHO (cidade, rua) " + "VALUES(?,?)";

			conectarObtendoId(sql);
			comando.setString(1, car.getCidade());
			comando.setString(2, car.getRua());
			comando.executeUpdate();

			ResultSet consulta = comando.getGeneratedKeys();

			if (consulta.next()) {
				id = consulta.getInt(1);
				car.setId(id);
			}

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void deletar(Carrinho car) {
		try {
			String sql = "DELETE FROM carrinho WHERE id = ?";
			conectar(sql);
			comando.setLong(1, car.getId());
			comando.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
	}

	@Override
	public void atualizar(Carrinho car) {
		try {
			String sql = "UPDATE carrinho SET id=?, rua=?, cidade=? WHERE id=?";

			conectar(sql);
			comando.setLong(1, car.getId());
			comando.setString(2, car.getRua());
			comando.setString(3, car.getCidade());
			comando.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}

	}
	
	public void atualizaProduto(Produto prod, long carId){
		try {
			String sql = "UPDATE itemcarrinho SET quantidade=? WHERE carrinhoid=? and produto=?";
			
			conectar(sql);
			comando.setInt(1, prod.getQuantidade());			
			comando.setLong(2, carId);
			comando.setLong(3, prod.getId());
			comando.executeUpdate();
			
		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
	}

	// Foi feito mas n�o ser� usado
	@Override
	public List<Carrinho> listarCarrinhos() {
		List<Carrinho> listaCarrinhos = new ArrayList<>();

		String sql = "select * from carrinho as c join itemCarrinho as ic on c.id = ic.id "
				+ "join produto as p on ic.produto = p.id order by c.id";

		try {
			conectar(sql);
			
			ResultSet resultado = comando.executeQuery();
			
			while(resultado.next()){				
				//criação do carrinho				
				long idCar = resultado.getLong(1);
				String cidadeCar = resultado.getString(2);
				String ruaCar = resultado.getString(3);
				//criação dos produtos
//				if(resultado.next()) ver como vou fazer isso
			}

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}

		return listaCarrinhos;
	}

	@Override
	public Carrinho procurarPorCodigo(long id) {
		List<Produto>listaProdutos = new ArrayList<>();
		Carrinho car = null;
				
		String sqlCar = "SELECT id, cidade, rua from carrinho where id=?";
		try {
			conectar(sqlCar);
			comando.setLong(1, id);
			
			ResultSet resultCarro = comando.executeQuery();
			
			if(resultCarro.next()){
				int idCar = resultCarro.getInt(1);
				String cidade = resultCarro.getString(2);
				String rua = resultCarro.getString(3);
				
				car = new Carrinho(listaProdutos, rua, cidade, idCar);
			}else{
				return null;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
		
		String sql = "SELECT ic.quantidade, p.id, p.nome, p.preco FROM carrinho as c "
				+ "join itemcarrinho as ic on c.id= ic.carrinhoid "
				+ "join produto as p on p.id = ic.produto "
				+ "where c.id=?";
		
		try {
			
			conectar(sql);
			comando.setLong(1, id);
			
			ResultSet resultado = comando.executeQuery();
			
			while(resultado.next()){
				int quanti = resultado.getInt(1);
				int prodId = resultado.getInt(2);
				String prodNome = resultado.getString(3);
				double prodPreco = resultado.getDouble(4);
				
				Produto prod = new Produto(prodId, prodNome, prodPreco, quanti);
				listaProdutos.add(prod);				
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
		
		 return car;
	}

	public void conectar(String sql) throws SQLException {
		conexao = ConnectionFactory.getConnection();
		comando = conexao.prepareStatement(sql);
	}

	public void conectarObtendoId(String sql) throws SQLException {
		conexao = ConnectionFactory.getConnection();
		comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	}

	public void fecharConexao() {
		try {
			if (comando != null) {
				comando.close();
			}
			if (conexao != null) {
				conexao.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
