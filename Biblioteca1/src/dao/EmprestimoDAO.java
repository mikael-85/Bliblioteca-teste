package dao;

import java.util.ArrayList;
import java.util.Collection;
import modelo.EmprestimoModelo;
import modelo.UsuarioModelo;

public class EmprestimoDAO {
	private Collection<EmprestimoModelo> listaEmprestimo = new ArrayList<>();
	
	public void cadastrarEmprestimo(EmprestimoModelo novoEmprestimo) {
		this.listaEmprestimo.add(novoEmprestimo);
	}
	
	public Collection<EmprestimoModelo> consultaEmprestimoUsuario (String loginUsuario){
		Collection<EmprestimoModelo> emprestimoUsuarioTemp = new ArrayList<>();
		for (EmprestimoModelo emprestimoAtual : listaEmprestimo) {
			if(emprestimoAtual.getLogin().compareTo(loginUsuario) == 0) {
				emprestimoUsuarioTemp.add(emprestimoAtual);
			}
		}
		return emprestimoUsuarioTemp;
	}
	
	public Collection<EmprestimoModelo> listarEmprestimos(){
		return this.listaEmprestimo;
	}
	
	public EmprestimoModelo buscarEmprestimoIsbn(long isbn) {
		EmprestimoModelo resultadoBusca = null;
		for(EmprestimoModelo emprestimoAtual: listaEmprestimo) {
			if(emprestimoAtual.getIsbn() == isbn) {
				resultadoBusca = emprestimoAtual;
			}
		}
		return resultadoBusca;
	}
	
	public void excluir(long isbn) {
		for (EmprestimoModelo emprestimoAtual : listaEmprestimo) {
			if(emprestimoAtual.getIsbn() == isbn) {
				listaEmprestimo.remove(emprestimoAtual);
				break;
			}
		}
	}	
	
}
