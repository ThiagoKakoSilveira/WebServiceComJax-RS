package br.com.alura.loja.daoBD;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Montador;
import br.com.alura.loja.modelo.Produto;
import contratos.CarrinhoDaoInterface;
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
			comando.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}

	}

	public void atualizaProduto(Produto prod, long carId) {
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

			while (resultado.next()) {
				// criação do carrinho
			}

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}

		return listaCarrinhos;
	}

	@Override
	public Montador procurarPorCodigo(long id) {
		List<Produto> listaProdutos = new ArrayList<>();
		Montador montador = null;
		boolean motou = false;
		// 					   1 	  2 	    3 			4 			5		 6 		7
		String sql = "select c.id, u.nome, con.email, ic.quantidade, p.nome, p.preco, p.id from carrinho as c "
				+ "join usuario as u on c.usuarioid = u.id " + "join contato as con on u.contatoId = con.id "
				+ "join endereco as ende on u.enderecoId = ende.id "
				+ "join itemCarrinho as ic on c.id = ic.carrinhoid " + "join produto as p on ic.produtoId = p.id "
				+ "where c.id=? order by c.id";

		try {

			conectar(sql);
			comando.setLong(1, id);

			ResultSet resultado = comando.executeQuery();

			while (resultado.next()) {
				int quanti = resultado.getInt(4);
				long prodId = resultado.getLong(7);
				String prodNome = resultado.getString(5);
				double prodPreco = resultado.getDouble(6);

				Produto prod = new Produto(prodId, prodNome, prodPreco, quanti);
				listaProdutos.add(prod);
				
				if(!motou){
					long idCar = resultado.getLong(1);
					String nome = resultado.getString(2);
					String email = resultado.getString(3);
					
					montador = new Montador(idCar, nome, email);
					motou = true;
				}

			}

		} catch (SQLException ex) {
			Logger.getLogger(CarrinhoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fecharConexao();
		}
		
		montador.setListaProduto(listaProdutos);

		return montador;
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
