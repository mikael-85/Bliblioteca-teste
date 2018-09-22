package modelo;

public class UsuarioModelo {
	private String nome;
	private String login;
	private int tipo; //1 - Aluno | 2 - Professor
	private double multa;
	
	public UsuarioModelo(String nome, String login, int tipo, double multa) {
		setNome(nome);
		setLogin(login);
		setTipo(tipo);
		setMulta(multa);
	}
	
	public UsuarioModelo() {}

	public String getTipoString() {
		if(this.tipo == 1) {
			return "Aluno"; 
		}
		return "Professor"; 
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	

}
