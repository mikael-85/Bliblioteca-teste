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
}
