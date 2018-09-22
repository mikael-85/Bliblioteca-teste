package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.LivroModelo;
import dao.BD;

public class LivroDAO {
	
	
	public void inserir (LivroModelo livro) {
		Connection conexao = new BD().getConexao();
		// variavel do tipo Connection onde recebe uma instacia de BD onde armazena o método getConexao();
		try {
			PreparedStatement sql = conexao.prepareStatement(
					"INSERT INTO livro (isbn, autores, edicao, editora, nome, ano) VALUES (?, ?, ?, ?, ?, ?)");
			sql.setLong(1,livro.getIsbn());
			sql.setString(2, livro.getAutores());
			sql.setInt(3, livro.getEdicao());
			sql.setString(4, livro.getEditora());
			sql.setString(5, livro.getNome());
			sql.setInt(6, livro.getAno());
			sql.execute();
			sql.close();
			conexao.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar (LivroModelo livro) {
		Connection conexao = new BD().getConexao();
		try {
			PreparedStatement sql = conexao.prepareStatement(
					"UPDATE livro SET autores = ?, edicao = ?, editora = ?, nome = ?, ano  = ?  WHERE isbn = ?");
			sql.setString(1, livro.getAutores());
			sql.setInt(2, livro.getEdicao());
			sql.setString(3, livro.getEditora());
			sql.setString(4, livro.getNome());
			sql.setInt(5, livro.getAno());
			sql.setLong(6,livro.getIsbn());
			sql.execute();
			sql.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir (LivroModelo livro) {
		Connection conexao = new BD().getConexao();
		try {
			PreparedStatement sql = conexao.prepareStatement(
					"DELETE FROM livro WHERE isbn = ?");
			sql.setLong(1, livro.getIsbn());
			sql.execute();
			sql.close();
			conexao.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//public buscar() {}
		
	public LivroModelo buscarIsbn (long isbn) {
		Connection conexao = new BD().getConexao();
		
		LivroModelo livroBusca = new LivroModelo();
		try {
			PreparedStatement sql = conexao.prepareStatement(
					"SELECT * FROM livro WHERE isbn = ?");
			sql.setLong(1, isbn);
			ResultSet resultado = sql.executeQuery();
			
			while(resultado.next()) {
			livroBusca.setIsbn(resultado.getLong("isbn"));
			livroBusca.setAutores(resultado.getString("autores"));
			livroBusca.setEdicao(resultado.getInt("edicao"));
			livroBusca.setEditora(resultado.getString("editora"));
			livroBusca.setNome(resultado.getString("nome"));
			livroBusca.setAno(resultado.getInt("ano"));
			}	
			resultado.close();
			sql.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(livroBusca.getAutores() == null) {
			return null;
		}
		return livroBusca;
	}
	
	public Collection<LivroModelo> listarTodos() {
		Connection conexao = new BD().getConexao();
		Collection<LivroModelo> todosLivros = new ArrayList<>();
		try {
			PreparedStatement sql;
			sql = conexao.prepareStatement(
					"SELECT * FROM livro");

			ResultSet resultado = sql.executeQuery();
			while(resultado.next()) {
				LivroModelo livroAtual = new LivroModelo();
				livroAtual.setIsbn(resultado.getLong("isbn"));
				livroAtual.setAutores(resultado.getString("autores"));
				livroAtual.setEdicao(resultado.getInt("edicao"));
				livroAtual.setEditora(resultado.getString("editora"));
				livroAtual.setNome(resultado.getString("nome"));
				livroAtual.setAno(resultado.getInt("ano"));

				todosLivros.add(livroAtual);
			}
			resultado.close();
			sql.close();
			conexao.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return todosLivros; 
	}
	
	public Collection<LivroModelo> pesquisarString(String pesquisa, String tipo){
		Connection conexao = new BD().getConexao();
		Collection<LivroModelo> todosLivros = new ArrayList<>();
		try {
			PreparedStatement sql;
			sql = conexao.prepareStatement(
					"SELECT * FROM livro WHERE "+tipo+" LIKE ?");
	//		sql.setString(1, tipo);
			sql.setString(1, "%"+pesquisa+"%");
			ResultSet resultado = sql.executeQuery();
			while(resultado.next()) {
				LivroModelo livroAtual = new LivroModelo();
				livroAtual.setIsbn(resultado.getLong("isbn"));
				livroAtual.setAutores(resultado.getString("autores"));
				livroAtual.setEdicao(resultado.getInt("edicao"));
				livroAtual.setEditora(resultado.getString("editora"));
				livroAtual.setNome(resultado.getString("nome"));
				livroAtual.setAno(resultado.getInt("ano"));

				todosLivros.add(livroAtual);
			}
			resultado.close();
			sql.close();
			conexao.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return todosLivros; 

	}
}


