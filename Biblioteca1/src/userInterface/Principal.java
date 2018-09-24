package userInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) {
		OpcoesUsuario objetoOpcoesUsuario = new OpcoesUsuario();
		OpcoesLivro objetoOpcoesLivro= new OpcoesLivro();
		OpcoesReserva  objetoOpcoesReserva = new OpcoesReserva(objetoOpcoesUsuario); // passar dois objetos opcoesUsuario e opcoesLivro
		OpcoesEmprestimo objetoOpcoesEmprestimo = new OpcoesEmprestimo(objetoOpcoesUsuario, objetoOpcoesReserva);
		
		Scanner entrada = new Scanner(System.in); 
		int opcao = -1, opcao2= -1; 
		
		while(opcao != 0) {
			//opcao = -1;
			System.out.println("1 - cadastro de livros: ");
			System.out.println("2 - cadastro de usuarios: ");
			System.out.println("3 - emprestimos: ");
			System.out.println("4 - reserva: ");
			System.out.println("5 - multa: ");
			System.out.println("6 - Alterar Data: ");
			System.out.println("0 - sair: ");
			opcao = Integer.parseInt(entrada.nextLine());
						
			switch (opcao) {
			case 1:	
				opcao2 = -1;
				while(opcao2 != 0) {
					
					System.out.println("1 - incluir livro ");
					System.out.println("2 - alterar livros ");
					System.out.println("3 - excluir de livro: ");
					System.out.println("4 - listar livros: ");
					System.out.println("5 - buscar livro pelo isbn: ");
					System.out.println("6 - buscar livro pelo titulo: ");
					System.out.println("7 - buscar livro pela editora: ");
					System.out.println("0 - voltar");
					opcao2 = Integer.parseInt(entrada.nextLine());
					
					switch (opcao2) {
						case 1:
							objetoOpcoesLivro.incluirLivro();
							break;
	
						case 2:
							objetoOpcoesLivro.alterarLivro();
							break;
	
						case 3:
							objetoOpcoesLivro.excluirLivro();
							break;
	
						case 4:
							objetoOpcoesLivro.listarLivro();
							break;
	
						case 5:
							objetoOpcoesLivro.buscarLivroIsbn();
							break;
	
						case 6:
							objetoOpcoesLivro.buscarLivroTitulo();
							break;
	
						case 7:
							objetoOpcoesLivro.buscarLivroEditora();
							break;
	
						default:
							break;
						
					}
				}
				break;

			case 2:
				opcao2 = -1;
				while(opcao2 != 0) {
					
					System.out.println("1 - incluir usuario ");
					System.out.println("2 - excluir usuario ");
					System.out.println("3 - listar usuario ");
					System.out.println("4 - buscar usuario por nome ");
					System.out.println("5 - buscar usuario pelo login"); //ok
					System.out.println("0 - voltar");
					opcao2 = Integer.parseInt(entrada.nextLine());
					
					switch (opcao2) {
						case 1:
							objetoOpcoesUsuario.incluirUsuario();
							break;
	
						case 2:
							objetoOpcoesUsuario.excluirUsuario();
							break;
	
						case 3:
							objetoOpcoesUsuario.listarUsuario();
							break;
	
						case 4:
							objetoOpcoesUsuario.buscarUsuarioNome();
							break;
	
						case 5:
							objetoOpcoesUsuario.buscarUsuarioLogin();
							break;
	
						default:
							break;

					}
				}
			break;
			
			case 3:
				opcao2 = -1;
				while(opcao2 != 0) {
					
					System.out.println("1 - emprestar livro ");
					System.out.println("2 - renovar emprestimo ");
					System.out.println("3 - devolver livro ");
					System.out.println("4 - listar emprestimos ");
					System.out.println("5 - buscar emprestimo por usuario");
					System.out.println("6 - buscar emprestimo por Isbn ");
					System.out.println("7 - buscar emprestimo por titulo ");
					System.out.println("0 - voltar");
					opcao2 = Integer.parseInt(entrada.nextLine());
					
					switch (opcao2) {
						case 1:
							objetoOpcoesEmprestimo.cadastrarEmprestimo();
							break;
	
						case 2:
							objetoOpcoesEmprestimo.renovarEmprestimo();
							break;
	
						case 3:
							objetoOpcoesEmprestimo.devolverLivro();
							break;
	
						case 4:
							objetoOpcoesEmprestimo.listarEmprestimo();
							break;
	
						case 5:
							objetoOpcoesEmprestimo.buscarEmprestimoUsuario();
							break;
	
						case 6:
							objetoOpcoesEmprestimo.buscarEmprestimoIsbn();
							break;
	
						case 7:
							objetoOpcoesEmprestimo.buscarEmprestimoTitulo();
							break;
	
						default:
							break;
						
					}
				}
			break;

			case 4:
				opcao2 = -1;
				while(opcao2 != 0) {
					
					System.out.println("1 - solicitar reserva de livro ");
					System.out.println("2 - cancelar reserva de livro ");
					System.out.println("0 - voltar");
					opcao2 = Integer.parseInt(entrada.nextLine());
					
					switch (opcao2) {
						case 1:
							objetoOpcoesReserva.cadastraReserva();
							break;
	
						case 2:
							objetoOpcoesReserva.removeReserva();
							break;
							
	
						default:
							break;
						
					}
				}
			break;

			case 5:
				opcao2 = -1;
				while(opcao2 != 0) {
					
					System.out.println("1 - consultar multa ");
					System.out.println("2 - pagar multa ");
					System.out.println("0 - voltar");
					opcao2 = Integer.parseInt(entrada.nextLine());
					
					switch (opcao2) {
						case 1:
							objetoOpcoesUsuario.ConsultarMulta();
							break;
	
						case 2:
							objetoOpcoesUsuario.pagarMulta();
							break;
	
						default:
							break;
					}
				}
			break;
			case 6:
				objetoOpcoesEmprestimo.setDataAtual();
				break;
			default: //Quando nao escolhido nenhum case volta a opcao padrao 
				//break;
			} 
		}
	entrada.close();	
	}
}
