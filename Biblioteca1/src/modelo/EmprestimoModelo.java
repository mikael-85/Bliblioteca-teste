package modelo;

import java.util.Date;

public class EmprestimoModelo {
	private long isbn;
	private String login;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	
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
	
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}
