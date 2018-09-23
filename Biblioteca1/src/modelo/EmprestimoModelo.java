package modelo;

import java.util.Calendar;;

public class EmprestimoModelo {
	private long isbn;
	private String login;
	private Calendar dataEmprestimo;
	private Calendar dataDevolucao;
	
	public long getIsbn() {
		return isbn;
	
	}
	
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}
