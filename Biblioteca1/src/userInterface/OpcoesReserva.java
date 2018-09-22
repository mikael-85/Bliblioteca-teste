package userInterface;

import java.util.Scanner;

import modelo.LivroModelo;
import modelo.ReservaModelo;
import modelo.UsuarioModelo;
import negocio.LivroNegocio;
import negocio.ReservaNegocio;
import negocio.UsuarioNegocio;

public class OpcoesReserva {
	private ReservaNegocio objetoReservaNegocio = new ReservaNegocio();
	Scanner entrada = new Scanner(System.in);
	//private OpcoesLivro objetoOpcoesLivro;
	private UsuarioNegocio objetoUsuarioNegocio; //conversa com DAO
		
	public OpcoesReserva(OpcoesUsuario objetoOpcoesUsuario) {
		//OpcoesUsuario objetoOpcoesUsuarioTemp = objetoOpcoesUsuario;
		this.objetoUsuarioNegocio = objetoOpcoesUsuario.getObjetoUsuarioNegocio(); // mesma referencia de memoria para consultar o que ja foi
	} 																				// cadastrado na outra
	
	public ReservaNegocio getObjetoReservaNegocio() {
		return this.objetoReservaNegocio; 
	}
	
	public void cadastraReserva() {
		long isbnReserva;
		String loginReserva;
		System.out.println("Digite o isbn");
		isbnReserva = Long.parseLong(entrada.nextLine());
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
				
		LivroModelo livroModeloPesquisa = objetoLivroNegocio.pesquisarIsbn(isbnReserva);

		if(livroModeloPesquisa == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else {
			UsuarioModelo usuarioPesquisaLogin; 
			System.out.println("Digite o login");
			loginReserva = entrada.nextLine();
			usuarioPesquisaLogin = this.objetoUsuarioNegocio.pesquisaLogin(loginReserva);
			if(usuarioPesquisaLogin == null) {
				System.out.println("Login nao encontrado...");
			}
			else{
				if(consultaReservaIsbn(isbnReserva) == 1){
					System.out.println("O livro ja foi reservado!");
				}
				else {
					ReservaModelo novaReserva = new ReservaModelo();
					novaReserva.setIsbn(isbnReserva); 
					novaReserva.setLogin(loginReserva);
					this.objetoReservaNegocio.cadastraReserva(novaReserva);
					System.out.println("O livro foi reservado com sucesso!");
				}
				
			}
		}
	}	
	
	public void removeReserva() { //talvez fazer uma verificação se o livro existe...
		long isbnReserva;
		System.out.println("Digite o isbn");
		isbnReserva = Long.parseLong(entrada.nextLine());
		if(consultaReservaIsbn(isbnReserva) == 1){
			this.objetoReservaNegocio.removeReserva(isbnReserva);
			System.out.println("A reserva foi removida com sucesso! ");
		}
		else {
			System.out.println("O livro nao possui reservas");
		}
	}
	
	public int consultaReservaIsbn(long isbnReserva) {
		return this.objetoReservaNegocio.consultaReservaIsbn(isbnReserva); 
	}
	
	public void consultaReservaUsuario() {}
}
