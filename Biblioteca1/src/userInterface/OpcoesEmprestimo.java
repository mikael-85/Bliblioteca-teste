package userInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Scanner;

import modelo.EmprestimoModelo;
import modelo.LivroModelo;
import modelo.ReservaModelo;
import modelo.UsuarioModelo;
import negocio.EmprestimoNegocio;
import negocio.LivroNegocio;
import negocio.ReservaNegocio;
import negocio.UsuarioNegocio;

public class OpcoesEmprestimo {
	Scanner entrada = new Scanner(System.in);
	
	public Calendar dataAtual = Calendar.getInstance();	
	
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
		else { //o livro existe
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
						//verificar se o usuario ja tem emprestimo(s)
						Collection<EmprestimoModelo> emprestimoUsuarioTemp =  this.objetoEmprestimoNegocio.buscarEmprestimoUsuario(loginUsuarioPesquisa);
						int numeroEmprestimosUsuario = emprestimoUsuarioTemp.size();
						//verificar o tipo de usuario
						if(objetoUsuarioModelo.getTipo() == 1) {
							//verifica numero de emprestimos do usuario
							//aluno pode retirar 3 livros por 7 dias
							if(numeroEmprestimosUsuario < 3) {
								EmprestimoModelo novoEmprestimo = criaEmprestimoModelo(loginUsuarioPesquisa, isbnPesquisaLivro,7);
								this.objetoEmprestimoNegocio.cadastraEmprestimo(novoEmprestimo); 
								System.out.println("Livro emprestado com sucesso, data de devolucao: "+ 
										novoEmprestimo.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
				                        (novoEmprestimo.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
				                        novoEmprestimo.getDataDevolucao().get(Calendar.YEAR)
								);
							}
							else {
								System.out.println("Usuario ja possui 3 emprestimos...");
							}
						}
						else {
							//verifica numero de emprestimos do usuario
							//professor pode retirar 5 livros por 15 dias 
							if(numeroEmprestimosUsuario < 5) {
								EmprestimoModelo novoEmprestimo = criaEmprestimoModelo(loginUsuarioPesquisa, isbnPesquisaLivro,15);
								this.objetoEmprestimoNegocio.cadastraEmprestimo(novoEmprestimo);
								System.out.println("Livro emprestado com sucesso, data de devolucao: "+ 
										novoEmprestimo.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
				                        (novoEmprestimo.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
				                        novoEmprestimo.getDataDevolucao().get(Calendar.YEAR)
								);
							}
							else {
								System.out.println("Usuario ja possui 5 emprestimos...");
							}
						}
					}
					else{
						//possui multa
						//verificar se o usuario ja tem emprestimo(s)
						Collection<EmprestimoModelo> emprestimoUsuarioTemp =  this.objetoEmprestimoNegocio.buscarEmprestimoUsuario(loginUsuarioPesquisa);
						int numeroEmprestimosUsuario = emprestimoUsuarioTemp.size();
						int tempoEmprestimo;
						if(objetoUsuarioModelo.getTipo() == 1) {
							//se é aluno
							tempoEmprestimo = 7;
							
						}
						else {
							//se é professor 
							tempoEmprestimo = 15;
						}
						
						if(numeroEmprestimosUsuario == 0) {
							EmprestimoModelo novoEmprestimo = criaEmprestimoModelo(loginUsuarioPesquisa, isbnPesquisaLivro,tempoEmprestimo);
							this.objetoEmprestimoNegocio.cadastraEmprestimo(novoEmprestimo);
							System.out.println("Livro emprestado com sucesso, data de devolucao: "+ 
									novoEmprestimo.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
			                        (novoEmprestimo.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
			                        novoEmprestimo.getDataDevolucao().get(Calendar.YEAR)
							);
						}
						else {
							System.out.println("O usuario possui multa e ja possui um emprestimo...");
						}
					}
				}
			}
			else {
				System.out.println("O livro ja esta reservado para outro usuario...");
			}
		}

	}
	
	public EmprestimoModelo criaEmprestimoModelo(String loginUsuarioPesquisa, long isbnPesquisaLivro, int tempoEmprestimo) {
		EmprestimoModelo novoEmprestimo = new EmprestimoModelo();
		novoEmprestimo.setIsbn(isbnPesquisaLivro);
		novoEmprestimo.setLogin(loginUsuarioPesquisa);
		
		//usado para imprimir a data: SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //grava a data atual
        novoEmprestimo.setDataEmprestimo(dataAtual);
        //data atual em int
        int diaAtualDoAno = dataAtual.get(Calendar.DAY_OF_YEAR);
        //data de entrega em int, max 365
        int diaEntregaDoAno = diaAtualDoAno + tempoEmprestimo;
        //crai nova variavel do tipo calendar com a data atual
        Calendar dataEntrega = Calendar.getInstance();
        //insere a data atual em dias no objeto do tipo Calendar
        dataEntrega.set(Calendar.DAY_OF_YEAR, diaEntregaDoAno);
        //usa setDataEntrega no novoEmprestimo da data no formato correto
        novoEmprestimo.setDataDevolucao(dataEntrega);
										
		return novoEmprestimo; 
	}
	
	public void renovarEmprestimo(){
		System.out.println("Digite o Isbn do livro: ");
		long isbnPesquisa = Long.parseLong(entrada.nextLine());
		EmprestimoModelo resultadoEmprestimoModelo = this.objetoEmprestimoNegocio.buscarEmprestimoIsbn(isbnPesquisa);
		if(resultadoEmprestimoModelo == null) {
			System.out.println("Livro nao foi emprestado ainda... ");
		}
		else {
			if(this.objetoReservaNegocio.consultaReservaIsbn(isbnPesquisa) == 0) {
				// quando nao tem reserva
				UsuarioModelo objetoUsuarioModelo = this.objetoUsuarioNegocio.pesquisaLogin(resultadoEmprestimoModelo.getLogin());
				if(objetoUsuarioModelo == null) {
					System.out.println("Usuario nao encontrado!");
				}
				else {
					//Usuario existe!
					//Verifica se tem multa:
					if(objetoUsuarioModelo.getMulta() == 0) {
						//nao tem multa
						this.objetoEmprestimoNegocio.excluirEmprestimo(isbnPesquisa);
						int tempoEmprestimo;
						if(objetoUsuarioModelo.getTipo() == 1) {
							//se é aluno
							tempoEmprestimo = 7;
							
						}
						else {
							//se é professor 
							tempoEmprestimo = 15;
						}
						
						EmprestimoModelo novoEmprestimo = criaEmprestimoModelo(objetoUsuarioModelo.getLogin(), isbnPesquisa, tempoEmprestimo );
						this.objetoEmprestimoNegocio.cadastraEmprestimo(novoEmprestimo); 
						System.out.println("Livro emprestado com sucesso, data de devolucao: "+ 
								novoEmprestimo.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
		                        (novoEmprestimo.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
		                        novoEmprestimo.getDataDevolucao().get(Calendar.YEAR)
						);
					}
					else {
						//tem multa
						Collection<EmprestimoModelo> emprestimoUsuarioTemp =  this.objetoEmprestimoNegocio.buscarEmprestimoUsuario(objetoUsuarioModelo.getLogin());
						int numeroEmprestimosUsuario = emprestimoUsuarioTemp.size();
						if(numeroEmprestimosUsuario == 1) {
							int tempoEmprestimo;
							if(objetoUsuarioModelo.getTipo() == 1) {
								//se é aluno
								tempoEmprestimo = 7;
								
							}
							else {
								//se é professor 
								tempoEmprestimo = 15;
							}
							EmprestimoModelo novoEmprestimo = criaEmprestimoModelo(objetoUsuarioModelo.getLogin(), isbnPesquisa, tempoEmprestimo);
							this.objetoEmprestimoNegocio.cadastraEmprestimo(novoEmprestimo);
							System.out.println("Livro emprestado com sucesso, data de devolucao: "+ 
									novoEmprestimo.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
			                        (novoEmprestimo.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
			                        novoEmprestimo.getDataDevolucao().get(Calendar.YEAR)
							);
						}
						else {
							System.out.println("O usuario possui multa e ja possui e nao pode ter mais que um emprestimo...");
						}
					}
				}

			}
			else {
				//quando tem reserva
				System.out.println("nao foi possivel emprestar o livro, pois livro ja esta reservado");
			}
		}
	}
	
	public void verificaCadastraMulta(EmprestimoModelo emprestimoPesquisaIsbn) {

		int diaAtualDoAno = dataAtual.get(Calendar.DAY_OF_YEAR);
		//data de entrega em int, max 365
		int diaEntregaDoAno = emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.DAY_OF_YEAR);
		if(diaAtualDoAno > diaEntregaDoAno) {
			int multaTemp = diaAtualDoAno - diaEntregaDoAno;
            double multa = (double)multaTemp;
			//double multa = (double)(diaEntregaDoAno - diaEntregaDoAno);
			System.out.println("Multa do livro entregue: "+ multa);
			//Chamada para atualizar o campo de multa do usuario 
			this.objetoUsuarioNegocio.alteraMulta(emprestimoPesquisaIsbn.getLogin(), multa);
			//diaEntragaDoAno  - diaAtualDoAno
		}
	
	}
	
	public void devolverLivro(){
		long isbnPesquisa;
		System.out.println("Digite o Isbn do livro que deseja devolver");
		isbnPesquisa = Long.parseLong(entrada.nextLine());
		EmprestimoModelo emprestimoPesquisaIsbn = this.objetoEmprestimoNegocio.buscarEmprestimoIsbn(isbnPesquisa);
		if(emprestimoPesquisaIsbn == null) {
			System.out.println("Emprestimo nao encontrado...");
		}
		else {
			verificaCadastraMulta(emprestimoPesquisaIsbn);
			this.objetoEmprestimoNegocio.excluirEmprestimo(isbnPesquisa);
			System.out.println("Emprestimo devolvido!");
		}
		
	} 
	
	public void listarEmprestimo(){
		Collection<EmprestimoModelo> todosOsEmprestimos = this.objetoEmprestimoNegocio.listarEmprestimos();
		if(todosOsEmprestimos.isEmpty()) {
			System.out.println("Nao existe nenhum emprestimo...");
		}
		else {
			for(EmprestimoModelo emprestimoAtual : todosOsEmprestimos) {
				System.out.println("Isbn: "+emprestimoAtual.getIsbn() + ", Login:"+emprestimoAtual.getLogin()+
						", Data do Emprestimo:"+
						emprestimoAtual.getDataEmprestimo().get(Calendar.DAY_OF_MONTH) + "/" +
                        (emprestimoAtual.getDataEmprestimo().get(Calendar.MONTH)+1) + "/" +
                        emprestimoAtual.getDataEmprestimo().get(Calendar.YEAR) +
                        ", Data de Devolucao: "+
                        emprestimoAtual.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
                        (emprestimoAtual.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
                        emprestimoAtual.getDataDevolucao().get(Calendar.YEAR)
               );
			}
		}
	}
	
	public void setDataAtual() {
		System.out.println("Digite a data atual (dd/mm/aaaa):");
		String dataTemp = entrada.nextLine();
		//char[] dataTempC = dataTemp.toCharArray(); 
		//dataTempC[4] = (char)((int)dataTempC[4]+1);
		//dataTemp =String.valueOf(dataTempC);
		
		//System.out.println(dataTemp);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataAtual.setTime(sdf.parse(dataTemp));
			System.out.println("Data alterada para:"+
					this.dataAtual.get(Calendar.DAY_OF_MONTH) + "/" +
                    
					(this.dataAtual.get(Calendar.MONTH)+1) + "/" +
                    this.dataAtual.get(Calendar.YEAR)
            );
		} catch (ParseException ex) {
			System.out.println("Data invalida!");
		}
	}
	
	public void buscarEmprestimoIsbn(){
		System.out.println("Digite o Isbn");
		long isbnPesquisaLivro = Long.parseLong(entrada.nextLine());
		EmprestimoModelo emprestimoPesquisaIsbn = this.objetoEmprestimoNegocio.buscarEmprestimoIsbn(isbnPesquisaLivro);
		if(emprestimoPesquisaIsbn == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else {
			System.out.println("Isbn: "+emprestimoPesquisaIsbn.getIsbn() + ", Login:"+emprestimoPesquisaIsbn.getLogin()+
					", Data do Emprestimo:"+
					emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.DAY_OF_MONTH) + "/" +
					(emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.MONTH)+1) + "/" +
					emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.YEAR) +
                    ", Data de Devolucao: "+
                    emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
                    (emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.MONTH)+1)+ "/" +
                    emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.YEAR)
           );
		}
	}
	
	public void buscarEmprestimoTitulo(){
		System.out.println("Digite o Titulo do livro");
		String tituloPesquisaLivro = entrada.nextLine();
		Collection <LivroModelo> resultadoPesquisaTitulo = this.objetoLivroNegocio.pesquisarString(tituloPesquisaLivro, 0);
	
		if(resultadoPesquisaTitulo.isEmpty()) {
			System.out.println("Nenhum livro encontrado...");
		 }
		else {
			System.out.println("Listando os livros com o titulo informado...");
			for (LivroModelo livroModelo : resultadoPesquisaTitulo) {
				System.out.println("Isbn: "+ livroModelo.getIsbn()+", autor: "+livroModelo.getAutores() +", edicao: "+livroModelo.getEdicao()+ 
						", editora: "+livroModelo.getEditora()+ ", nome: "+livroModelo.getNome()+ ", ano: "+ livroModelo.getAno());
			}
			System.out.println("Digite o Isbn do livro que deseja verificar emprestimo:");
			long isbnPesquisaLivro = Long.parseLong(entrada.nextLine());
			EmprestimoModelo emprestimoPesquisaIsbn = this.objetoEmprestimoNegocio.buscarEmprestimoIsbn(isbnPesquisaLivro);
			if(emprestimoPesquisaIsbn == null) {
				System.out.println("O livro com o Isbn informado nao possui emprestimos...");
			}
			else {
				System.out.println("Dados do emprestimo do livro:");
				System.out.println("Isbn: "+emprestimoPesquisaIsbn.getIsbn() + ", Login:"+emprestimoPesquisaIsbn.getLogin()+
						", Data do Emprestimo:"+
						emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.DAY_OF_MONTH) + "/" +
						(emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.MONTH)+1) + "/" +
						emprestimoPesquisaIsbn.getDataEmprestimo().get(Calendar.YEAR) +
	                    ", Data de Devolucao: "+
	                    emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
	                    (emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
	                    emprestimoPesquisaIsbn.getDataDevolucao().get(Calendar.YEAR)
	           );
			}
		}
	}
	
	public void buscarEmprestimoUsuario(){
		System.out.println("Digite o Login");
		String loginPesquisaUsuario = entrada.nextLine();
		Collection<EmprestimoModelo> emprestimoUsuarioTemp =  this.objetoEmprestimoNegocio.buscarEmprestimoUsuario(loginPesquisaUsuario);
		if(emprestimoUsuarioTemp.isEmpty()) {
			System.out.println("Nao existe nenhum emprestimo para este usuario...");
		}
		else {
			for(EmprestimoModelo emprestimoAtual : emprestimoUsuarioTemp) {
				System.out.println("Isbn: "+emprestimoAtual.getIsbn() + ", Login:"+emprestimoAtual.getLogin()+
						", Data do Emprestimo:"+
						emprestimoAtual.getDataEmprestimo().get(Calendar.DAY_OF_MONTH) + "/" +
						(emprestimoAtual.getDataEmprestimo().get(Calendar.MONTH)+1) + "/" +
						emprestimoAtual.getDataEmprestimo().get(Calendar.YEAR) +
						", Data de Devolucao: "+
						emprestimoAtual.getDataDevolucao().get(Calendar.DAY_OF_MONTH) + "/" +
						(emprestimoAtual.getDataDevolucao().get(Calendar.MONTH)+1) + "/" +
						emprestimoAtual.getDataDevolucao().get(Calendar.YEAR)
				);
			}
		}
	} 
	
	//FALTA FAZER FUNCAO DE ATUALIZACAO 'AUTOMATICA' DA MULTA!!!
	
		
}
