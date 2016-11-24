package br.com.alura.loja.daoBD;

import java.util.List;

import br.com.alura.loja.modelo.Carrinho;

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

	// Foi feito mas não será usado
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
	public Carrinho procurarPorCodigo(int id) {
		// TODO Auto-generated method stub
		return null;
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
