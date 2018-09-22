package negocio;

import java.util.Collection;

import dao.LivroDAO;
import modelo.LivroModelo;

public class LivroNegocio {
	private LivroDAO chamadaLivroDAO= new LivroDAO();
	
	public void incluir(LivroModelo livroNovo) {
		//LivrosDAO livroIncluirDAO= new LivrosDAO();
		this.chamadaLivroDAO.inserir(livroNovo);
	}
	public LivroModelo pesquisarIsbn(long isbnPesquisa) {
		//LivrosDAO livroPesquisarDAO = new LivrosDAO();
		LivroModelo livroModeloPesquisa = this.chamadaLivroDAO.buscarIsbn(isbnPesquisa);
		return livroModeloPesquisa;
	}
	
	public void alterar(LivroModelo livroAlterado) {
		this.chamadaLivroDAO.alterar(livroAlterado);
	}
	
	public void apagar(LivroModelo livroParaApagar) {
		this.chamadaLivroDAO.excluir(livroParaApagar);
	}
	
	public Collection<LivroModelo> listar() {
		return this.chamadaLivroDAO.listarTodos();
	}
	
	public Collection<LivroModelo> pesquisarString(String pesquisa, int tipo){
		//quando o tipo for 0(zero), sera pesquisa por titulo
		if(tipo == 0) {
			return this.chamadaLivroDAO.pesquisarString(pesquisa, "nome");
		}
		// quando for 1, sera pesquisa por editora
		else {
			return this.chamadaLivroDAO.pesquisarString(pesquisa, "editora");
		}
		
	}
}
