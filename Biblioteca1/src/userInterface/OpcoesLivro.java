package userInterface;

import java.util.Collection;
import java.util.Scanner;
import modelo.LivroModelo;
import negocio.LivroNegocio;

public class OpcoesLivro { //
	Scanner entrada = new Scanner(System.in); 
	
	public void incluirLivro() {
		LivroModelo livroNovo = new LivroModelo(); 
		System.out.println("Digite o Isbn");
		livroNovo.setIsbn(entrada.nextLong());// mandando a entrada do teclado para a funcao setIsbn
		System.out.println("Digite o autor");
		entrada.nextLine();
		livroNovo.setAutores(entrada.nextLine());
		System.out.println("Digite a edicao");
		livroNovo.setEdicao(entrada.nextInt());
		System.out.println("Digite a editora");
		entrada.nextLine();
		livroNovo.setEditora(entrada.nextLine());
		System.out.println("Digite o nome");
		livroNovo.setNome(entrada.nextLine());
		System.out.println("Digite o ano");
		livroNovo.setAno(entrada.nextInt());
		entrada.nextLine();//apagar
		System.out.println("Isbn: "+ livroNovo.getIsbn()+", autor: "+livroNovo.getAutores() +", edicao: "+livroNovo.getEdicao()+ 
				", editora: "+livroNovo.getEditora()+ ", nome: "+livroNovo.getNome()+ ", ano: "+ livroNovo.getAno());
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		
		objetoLivroNegocio.incluir(livroNovo);
		
	}
	
	public void alterarLivro() {
		System.out.println("Digite o Isbn");
		long isbnPesquisa = Long.parseLong(entrada.nextLine());// alterado
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		LivroModelo livroModeloPesquisa = objetoLivroNegocio.pesquisarIsbn(isbnPesquisa);


		if(livroModeloPesquisa == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else {
			System.out.println("livro encontrado! ");
			System.out.println("Isbn: "+ livroModeloPesquisa.getIsbn()+", autor: "+livroModeloPesquisa.getAutores() +", edicao: "+livroModeloPesquisa.getEdicao()+ 
					", editora: "+livroModeloPesquisa.getEditora()+ ", nome: "+livroModeloPesquisa.getNome()+ ", ano: "+ livroModeloPesquisa.getAno());
			Integer opcao = -1;
			while(opcao != 0) {
				System.out.println("Digite a opcao que deseja alterar: ");
				//System.out.println("1 - Isbn: ");
				System.out.println("1 - Autor: ");
				System.out.println("2 - Edicao: ");
				System.out.println("3 - Editora: ");
				System.out.println("4 - Nome: ");
				System.out.println("5 - Ano: ");
				System.out.println("0 - Salvar");
				opcao = -1;
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
					/*case 1:
						System.out.println("Digite o Isbn: ");
							livroModeloPesquisa.setIsbn(Long.parseLong(entrada.nextLine()));
					break;*/
					case 1:
						System.out.println("Digite o Autor: ");
						livroModeloPesquisa.setAutores(entrada.nextLine());
					break;
					case 2:
						System.out.println("Digite o Edicao: ");
						livroModeloPesquisa.setEdicao(Integer.parseInt(entrada.nextLine()));
					break;
					case 3:
						System.out.println("Digite o Editora: ");
						livroModeloPesquisa.setEditora(entrada.nextLine());
					break;
					case 4:
						System.out.println("Digite o Nome: ");
						livroModeloPesquisa.setNome(entrada.nextLine());
					break;
					case 5:
						System.out.println("Digite o Ano: ");
						livroModeloPesquisa.setAno(Integer.parseInt(entrada.nextLine()));
					break;
					default:
					break;
				}
			}
			objetoLivroNegocio.alterar(livroModeloPesquisa);
			System.out.println("Livro alterado com sucesso: ");
		}
	}
	
	public void excluirLivro() {
		System.out.println("Digite o Isbn");
		long isbnPesquisa = Long.parseLong(entrada.nextLine());
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		LivroModelo livroModeloPesquisa = objetoLivroNegocio.pesquisarIsbn(isbnPesquisa);


		if(livroModeloPesquisa == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else {
			System.out.println("O livro foi encontrado e sera apagado: ");
			System.out.println("Isbn: "+ livroModeloPesquisa.getIsbn()+", autor: "+livroModeloPesquisa.getAutores() +", edicao: "+livroModeloPesquisa.getEdicao()+ 
					", editora: "+livroModeloPesquisa.getEditora()+ ", nome: "+livroModeloPesquisa.getNome()+ ", ano: "+ livroModeloPesquisa.getAno());
			objetoLivroNegocio.apagar(livroModeloPesquisa);
		}
	}// ultima
	
	public void listarLivro() {
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		Collection<LivroModelo>todosLivros = objetoLivroNegocio.listar();
		if(todosLivros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		 }
		else {
			System.out.println("Listando os livros...");
			for (LivroModelo livroModelo : todosLivros) {
				System.out.println("Isbn: "+ livroModelo.getIsbn()+", autor: "+livroModelo.getAutores() +", edicao: "+livroModelo.getEdicao()+ 
						", editora: "+livroModelo.getEditora()+ ", nome: "+livroModelo.getNome()+ ", ano: "+ livroModelo.getAno());
			}
		}
	}
	
	public void buscarLivroIsbn() {
		System.out.println("Digite o Isbn");
		long isbnPesquisa = Long.parseLong(entrada.nextLine());// alterado
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		LivroModelo livroModeloPesquisa = objetoLivroNegocio.pesquisarIsbn(isbnPesquisa);


		if(livroModeloPesquisa == null) {
			System.out.println("Isbn nao encontrado...");
		}
		else {
			System.out.println("livro encontrado! ");
			System.out.println("Isbn: "+ livroModeloPesquisa.getIsbn()+", autor: "+livroModeloPesquisa.getAutores() +", edicao: "+livroModeloPesquisa.getEdicao()+ 
					", editora: "+livroModeloPesquisa.getEditora()+ ", nome: "+livroModeloPesquisa.getNome()+ ", ano: "+ livroModeloPesquisa.getAno());
			
		}
	}
	
	
	public void buscarLivroTitulo() {
		System.out.println("Digite o titulo");
		String tituloPesquisa = entrada.nextLine();
		
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		Collection<LivroModelo>todosLivros = objetoLivroNegocio.pesquisarString(tituloPesquisa, 0);
		if(todosLivros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		 }
		else {
			System.out.println("Listando os livros...");
			for (LivroModelo livroModelo : todosLivros) {
				System.out.println("Isbn: "+ livroModelo.getIsbn()+", autor: "+livroModelo.getAutores() +", edicao: "+livroModelo.getEdicao()+ 
						", editora: "+livroModelo.getEditora()+ ", nome: "+livroModelo.getNome()+ ", ano: "+ livroModelo.getAno());
			}
		}
	}
	
	public void buscarLivroEditora() {
		System.out.println("Digite a editora");
		String editoraPesquisa = entrada.nextLine();
		
		LivroNegocio objetoLivroNegocio = new LivroNegocio();
		Collection<LivroModelo>todosLivros = objetoLivroNegocio.pesquisarString(editoraPesquisa, 1);
		if(todosLivros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		 }
		else {
			System.out.println("Listando os livros...");
			for (LivroModelo livroModelo : todosLivros) {
				System.out.println("Isbn: "+ livroModelo.getIsbn()+", autor: "+livroModelo.getAutores() +", edicao: "+livroModelo.getEdicao()+ 
						", editora: "+livroModelo.getEditora()+ ", nome: "+livroModelo.getNome()+ ", ano: "+ livroModelo.getAno());
			}
		}
	}	
	
}
