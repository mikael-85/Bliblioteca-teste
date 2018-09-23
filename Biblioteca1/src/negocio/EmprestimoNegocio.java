package negocio;

import java.util.Collection;
import dao.EmprestimoDAO;
import modelo.EmprestimoModelo;

public class EmprestimoNegocio {
	private EmprestimoDAO objetoEmprestimoDAO = new EmprestimoDAO();
	
	public Collection<EmprestimoModelo> buscarEmprestimoUsuario(String login){
		return this.objetoEmprestimoDAO.consultaEmprestimoUsuario(login);
		
	}
}
