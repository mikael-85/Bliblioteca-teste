package negocio;

import java.util.Collection;
import java.util.List;

import dao.UsuarioDAO;
import modelo.UsuarioModelo;

public class UsuarioNegocio {
	private UsuarioDAO chamadaUsuarioDAO = new UsuarioDAO();
	
	/*1 - incluir usuario ");
					System.out.println("2 - excluir usuario ");
					System.out.println("3 - listar usuario ");
					System.out.println("4 - buscar usuario por nome ");
					System.out.println("5 - buscar usuario pelo login"*/
	//alterar?? depois talvez...
	
	public void incluir(UsuarioModelo novoUsuario) {
		this.chamadaUsuarioDAO.inserir(novoUsuario);
	}

	public void excluirUsuario(String login) {
		this.chamadaUsuarioDAO.excluir(login);
	}
	
	public Collection<UsuarioModelo> listar(){
		return this.chamadaUsuarioDAO.listarTodos();
	}
	
	public UsuarioModelo pesquisaLogin(String login) {
		UsuarioModelo usuarioModeloPesquisa = this.chamadaUsuarioDAO.pesquisarLogin(login);
		return usuarioModeloPesquisa;
	}
	public Collection<UsuarioModelo> pesquisaNome(String nome) {
		return this.chamadaUsuarioDAO.pesquisarNome(nome);
	}
	
	public UsuarioModelo zeraMulta(String login) {
		return this.chamadaUsuarioDAO.zeraMulta(login);
	}
	
	
	/*public int verificaTipoString(int tipoUsuario) {
		if(tipoUsuario == 1) {
			return 1;
		}
		else if(tipoUsuario == 2) {
			return 2;
		}
		else {
			return 0;
		}
	}*/
	//public Excluir(){}
}
