package userInterface;

import java.util.Scanner;

import modelo.LivroModelo;
import modelo.ReservaModelo;
import modelo.UsuarioModelo;
import negocio.EmprestimoNegocio;
import negocio.LivroNegocio;
import negocio.ReservaNegocio;
import negocio.UsuarioNegocio;

public class OpcoesEmprestimo {
	Scanner entrada = new Scanner(System.in);
	private EmprestimoNegocio objetoEmprestimoNegocio = new EmprestimoNegocio();
	private UsuarioNegocio objetoUsuarioNegocio;
	private LivroNegocio objetoLivroNegocio = new LivroNegocio();
	private ReservaNegocio objetoReservaNegocio;
	
	public OpcoesEmprestimo(OpcoesUsuario objetoOpcoesUsuario, OpcoesReserva objetoOpcoesReserva){
		this.objetoUsuarioNegocio = objetoOpcoesUsuario.getObjetoUsuarioNegocio();
		this.objetoReservaNegocio = objetoOpcoesReserva.getObjetoReservaNegocio();
	}
	
	public void cadastrarEmprestimo(){
		System.out.println("Digite o Isbn");
		long isbnPesquisaLivro = Long.parseLong(entrada.nextLine());
		//verificando se o livro existe
		LivroModelo livroModeloPesquisa = objetoLivroNegocio.pesquisarIsbn(isbnPesquisaLivro);
		if(livroModeloPesquisa == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else { //
			String loginUsuarioPesquisa;
			System.out.println("Digite o login do usuario:");
			loginUsuarioPesquisa = entrada.nextLine();
			//verificar se o livro possui reserva e, se a reserva é referente ao login informado
			ReservaModelo objetoReservaModelo = this.objetoReservaNegocio.consultaReservaIsbnModelo(isbnPesquisaLivro);
			if((objetoReservaModelo == null) || (objetoReservaModelo.getLogin().compareTo(loginUsuarioPesquisa) == 0)) {
				//verificando se usuario existe
				UsuarioModelo objetoUsuarioModelo = this.objetoUsuarioNegocio.pesquisaLogin(loginUsuarioPesquisa);
				if(objetoUsuarioModelo == null) {
					System.out.println("Usuario nao encontrado!");
				}
				else {
					//Usuario existe!
					//Verifica se tem multa:
					if(objetoUsuarioModelo.getMulta() == 0) {
						//verificar se o usuario ja tem emprestimo(s) (falta fazer!)
						
						//verificar o tipo de usuario
						if(objetoUsuarioModelo.getTipo() == 1) {
							//aluno pode retirar 3 livros por 7 dias
							
							//verifica numero de emprestimos do usuario
							
						}
						else {
							//professor pode retirar 5 livros por 15 dias 
							
							//verifica numero de emprestimos do usuario
						}
					}
					else{
						//possui multa
						
						//verifica
					}
				}
			}
			else {
				System.out.println("O livro ja esta reservado para outro usuario...");
			}
		}

	}
	
	public void renovarEmprestimo(){}
	
	public void excluirEmprestimo(){} // devolver livro
	
	public void listarEmprestimo(){}
	
	public void buscarEmprestimoIsbn(){}
	
	public void buscarEmprestimoTitulo(){}
	
	public void buscarEmprestimoUsuario(){}// pelo login! 
	
	// FALTA FAZER FUNCAO DE ATUALIZACAO 'AUTOMATICA' DA MULTA!!!
	
		
}
