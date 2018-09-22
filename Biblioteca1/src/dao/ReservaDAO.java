package dao;

import java.util.ArrayList;
import java.util.Collection;

import modelo.ReservaModelo;
import modelo.UsuarioModelo;

public class ReservaDAO {
	private Collection<ReservaModelo> listaReservas = new ArrayList<>();
	
	public void cadastraReserva(ReservaModelo novaReserva) {
		this.listaReservas.add(novaReserva);
	}
	
	public void removeReserva(long isbn) {
		for (ReservaModelo resultatoReservaAtual : listaReservas) {
			if(resultatoReservaAtual.getIsbn() == isbn) {
				this.listaReservas.remove(resultatoReservaAtual);
				break;
			}
		}	
	}
	
	public ReservaModelo consultaReservaIsbn(long isbn) {
		ReservaModelo resultadoPesquisaReserva = null;
		for (ReservaModelo resultatoReservaAtual : listaReservas) {
			if(resultatoReservaAtual.getIsbn() == isbn) {
				resultadoPesquisaReserva = resultatoReservaAtual;
				break;
			}			
		}	
		return resultadoPesquisaReserva;
	}
	
	//public int consultaReservaUsuario(String login) {}
}
