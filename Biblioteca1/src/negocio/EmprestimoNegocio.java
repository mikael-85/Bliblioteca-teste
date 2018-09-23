package negocio;

import java.util.Collection;
import dao.EmprestimoDAO;
import modelo.EmprestimoModelo;

public class EmprestimoNegocio {
	private EmprestimoDAO objetoEmprestimoDAO = new EmprestimoDAO();
	
	public Collection<EmprestimoModelo> buscarEmprestimoUsuario(String login){
		return this.objetoEmprestimoDAO.consultaEmprestimoUsuario(login);
	}
	
	public void cadastraEmprestimo(EmprestimoModelo novoEmprestimo) {
		this.objetoEmprestimoDAO.cadastrarEmprestimo(novoEmprestimo);
	}
	
	public Collection<EmprestimoModelo> listarEmprestimos (){
		return this.objetoEmprestimoDAO.listarEmprestimos();
	}
	
	public EmprestimoModelo buscarEmprestimoIsbn(long isbn){
		return this.objetoEmprestimoDAO.buscarEmprestimoIsbn(isbn);
	}
	
	public void excluirEmprestimo(long isbn) {
		this.objetoEmprestimoDAO.excluir(isbn);
	}
		
}
