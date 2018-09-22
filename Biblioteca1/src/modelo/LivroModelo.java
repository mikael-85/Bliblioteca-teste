package modelo;

import java.math.BigInteger;

public class LivroModelo {
	private long ISBN;
	private String autores;
	private int edicao;
	private String editora;
	private String nome;
	private int ano;
	
	public LivroModelo(long ISBN, String autores, int edicao, String editora, String nome, int ano) {
		setIsbn(ISBN);
		setAutores(autores);
		setEdicao(edicao);
		setEditora(editora);
		setNome(nome);
		setAno(ano);
	}
	public LivroModelo() {}
	
	public long getIsbn() {
		return ISBN;
	}

	public void setIsbn(long ISBN) {
		this.ISBN = ISBN;
	}
	//
	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	//@Override
	//public String toString() {
	//	return "Livro [autores=" + autores + ", edicao=" + edicao + ", editora=" + editora + ", nome=" + nome + ", ano="
		//		+ ano + "]";
	//}
	

}
