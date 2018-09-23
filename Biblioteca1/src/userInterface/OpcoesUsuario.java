package userInterface;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
//import dao.UsuarioDAO;
import modelo.UsuarioModelo;
import negocio.UsuarioNegocio;

public class OpcoesUsuario {
	Scanner entrada = new Scanner(System.in);
	private UsuarioNegocio objetoUsuarioNegocio = new UsuarioNegocio();

	public UsuarioNegocio getObjetoUsuarioNegocio() {
		return this.objetoUsuarioNegocio;
	}
	
	public OpcoesUsuario() {//teste de novos usuarios 
		UsuarioModelo novoUsuario1 = new UsuarioModelo("Carlos", "123eu", 1, 0);
		UsuarioModelo novoUsuario2 = new UsuarioModelo("Anna", "123ana", 2, 0);
		this.objetoUsuarioNegocio.incluir(novoUsuario1);
		this.objetoUsuarioNegocio.incluir(novoUsuario2);
	}
	
	public void incluirUsuario() {
		//UsuarioNegocio objetoUsuarioNegocio = new UsuarioNegocio();
		UsuarioModelo usuarioNovo = new UsuarioModelo();
		System.out.println("Digite o login do usuario: ");
		String usuarioLoginTemp = entrada.nextLine();
		
		UsuarioModelo usuarioModeloPesquisa = objetoUsuarioNegocio.pesquisaLogin(usuarioLoginTemp); 
		if(usuarioModeloPesquisa == null) {
			usuarioNovo.setLogin(usuarioLoginTemp);
			System.out.println("Digite o nome do usuario: ");
			usuarioNovo.setNome(entrada.nextLine());
					
			int verifica=0;
			while ((verifica != 1) && (verifica != 2) ){
				System.out.println("Digite o tipo de usuario (1 - Aluno | 2 - Professor)");
				verifica = Integer.parseInt(entrada.nextLine());
			}
			usuarioNovo.setTipo(verifica);
			usuarioNovo.setMulta(0);//mandando 0
			objetoUsuarioNegocio.incluir(usuarioNovo);
		}
		else {
			System.out.println("Usuario com este login ja cadastrado! ");
			incluirUsuario();
		}
	
	}
	
	public void excluirUsuario() {
		System.out.println("Digite o Login do usuario: ");
		String usuarioLoginTemp = entrada.nextLine();
		
		UsuarioModelo usuarioModeloPesquisa = objetoUsuarioNegocio.pesquisaLogin(usuarioLoginTemp);
		if(usuarioModeloPesquisa == null) {
			System.out.println("Usuario nao encontrado...");
		}
		else {
			System.out.println("Usuario foi encontrado e foi removido com sucesso! ");
			System.out.println("Nome: "+ usuarioModeloPesquisa.getNome()+", Login: "+usuarioModeloPesquisa.getLogin()+", tipo: "
			+ usuarioModeloPesquisa.getTipoString()+ 
					", multa: "+usuarioModeloPesquisa.getMulta());
		objetoUsuarioNegocio.excluirUsuario(usuarioModeloPesquisa.getLogin());//criar
		}
	}
		
	

	public void listarUsuario() {
		Collection<UsuarioModelo> todosUsuarios = objetoUsuarioNegocio.listar();
		if(todosUsuarios.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado.");
		 }
		else {
			System.out.println("Listando os usuarios...");
			for (UsuarioModelo usuarioAtual : todosUsuarios) {
				System.out.println("Nome: "+ usuarioAtual.getNome() +", Login: " +usuarioAtual.getLogin()+", tipo: "
						+ usuarioAtual.getTipoString()+ 
								", multa: "+usuarioAtual.getMulta());
			}	
		}
	}
	
	public void buscarUsuarioNome() {
		System.out.println("Digite o Nome do usuario: ");
		String usuarioNomeTemp = entrada.nextLine();
		Collection<UsuarioModelo> usuariosNomeEncontrados = objetoUsuarioNegocio.pesquisaNome(usuarioNomeTemp);
		if(usuariosNomeEncontrados.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado.");
		 }
		else {
			System.out.println("Listando os usuarios com nome "+ usuarioNomeTemp);
			for (UsuarioModelo usuarioAtual : usuariosNomeEncontrados) {
				System.out.println("Nome: "+ usuarioAtual.getNome() +", Login: " +usuarioAtual.getLogin()+", tipo: "
						+ usuarioAtual.getTipoString()+ 
								", multa: "+usuarioAtual.getMulta());
			}	
		}
	}
	
	public void buscarUsuarioLogin() {
		System.out.println("Digite o Login do usuario: ");
		String usuarioLoginTemp = entrada.nextLine();
		UsuarioModelo usuarioModeloPesquisa = objetoUsuarioNegocio.pesquisaLogin(usuarioLoginTemp);
		if(usuarioModeloPesquisa == null) {
			System.out.println("Usuario nao encontrado...");
		}
		else {
			System.out.println("Usuario encontrado! ");
			System.out.println("Nome: "+ usuarioModeloPesquisa.getNome()+", Login: "+usuarioModeloPesquisa.getLogin()+", tipo: "
			+ usuarioModeloPesquisa.getTipoString()+ 
					", multa: "+usuarioModeloPesquisa.getMulta());
		}
	}
	
	public void ConsultarMulta(){
		buscarUsuarioLogin();
	}
	
	public void pagarMulta(){
		System.out.println("Digite o Login do usuario: ");
		String usuarioLoginTemp = entrada.nextLine();
		UsuarioModelo usuarioEncontrado = objetoUsuarioNegocio.zeraMulta(usuarioLoginTemp); // 
		if(usuarioEncontrado != null) {
			System.out.println("O usuario de nome: "+usuarioEncontrado.getNome()+", e login: "+usuarioEncontrado.getLogin()+
					", com a multa: " +usuarioEncontrado.getMulta()+", foi alterado com sucesso!");
			//usuarioEncontrado.setMulta(2);
			System.out.println("O usuario de nome: "+usuarioEncontrado.getNome()+", e login: "+usuarioEncontrado.getLogin()+
					 ", teve a multa alterada para: " +usuarioEncontrado.getMulta()+ ", foi alterado com sucesso!");
		}
		else {
			System.out.println("Usuario não encontrado! ");
		}
		
	}
}	
