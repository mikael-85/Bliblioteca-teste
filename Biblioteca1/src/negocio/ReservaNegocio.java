package negocio;

import dao.ReservaDAO;
import modelo.ReservaModelo;

public class ReservaNegocio {
	ReservaDAO objetoReservaDAO = new ReservaDAO();
	
	public void cadastraReserva(ReservaModelo novaReserva) {
		this.objetoReservaDAO.cadastraReserva(novaReserva);
	}
	
	public void removeReserva(long isbn) {
		this.objetoReservaDAO.removeReserva(isbn);
	}
	
	public int consultaReservaIsbn(long isbn) {
		ReservaModelo objetoReservaModelo = this.objetoReservaDAO.consultaReservaIsbn(isbn);
		if(objetoReservaModelo == null) {
			return 0;
		}
		return 1;
	}
	
	public ReservaModelo consultaReservaIsbnModelo(long isbn) {
		return this.objetoReservaDAO.consultaReservaIsbn(isbn);
	}
}
	
	//public int consultaReservaUsuario(String login) {}

