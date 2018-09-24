package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.UsuarioModelo;
//import negocio.UsuarioNegocio;

public class UsuarioDAO {
	private Collection<UsuarioModelo> listaUsuarios = new ArrayList<>();
	
	public void inserir (UsuarioModelo usuario) {
		//try {
			this.listaUsuarios.add(usuario);
			System.out.println("Usuario cadastrado com sucesso! ");
		//}catch(NullPointerException e){
			//System.out.println("Erro, nao foi possivel inserir usuario devido a erro de preenchimento");
		//}
	}
	
	public void excluir(String login) {
		for (UsuarioModelo usuarioAtual : listaUsuarios) {
			if(usuarioAtual.getLogin().compareTo(login) == 0) {
				listaUsuarios.remove(usuarioAtual);
				break;
			}
		}
	}	
	
	public Collection<UsuarioModelo> listarTodos(){
		return this.listaUsuarios;
	}
	
	public Collection<UsuarioModelo> pesquisarNome(String nome){
		Collection<UsuarioModelo> usuarioTemp = new ArrayList<>();
		for (UsuarioModelo usuarioAtual : listaUsuarios) {
			if(usuarioAtual.getNome().contains(nome)) {
				usuarioTemp.add(usuarioAtual);
			}
		}
		return usuarioTemp;
	}
	
	public UsuarioModelo pesquisarLogin(String login) {
		UsuarioModelo resultadoPesquisa = null;
		for (UsuarioModelo usuarioAtual : listaUsuarios) {
			if(usuarioAtual.getLogin().compareTo(login) == 0) {
				resultadoPesquisa = usuarioAtual;
			}
		}
		return resultadoPesquisa;
	}
	
	public UsuarioModelo zeraMulta(String login) {
		UsuarioModelo usuarioZeraMulta = null;
		for (UsuarioModelo usuarioAtual : listaUsuarios) {
			if(usuarioAtual.getLogin().compareTo(login) == 0) {
				usuarioAtual.setMulta(0);
				usuarioZeraMulta = usuarioAtual;
			}
		}
		return usuarioZeraMulta;
	}
	
	public void alteraMulta(String login, double multa) {
		for(UsuarioModelo usuarioAtual : listaUsuarios) {
			if(usuarioAtual.getLogin().compareTo(login) == 0) {
				//le a multa atual
				double multaTemp = usuarioAtual.getMulta();
				multaTemp += multa;
				System.out.println("Multa Total do usuario: "+ multaTemp);
				//set nova multa
				usuarioAtual.setMulta(multaTemp);
				break;
			}
		}
	}
}
