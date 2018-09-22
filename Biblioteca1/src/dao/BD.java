package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String SENHA = "postgres";
	private static final String USUARIO = "postgres";
	private static final String URL_CONEXAO = "jdbc:postgresql://localhost:5432/biblioteca";
	
	public Connection getConexao() {
		Connection c = null;
		try {
			Class.forName(BD.JDBC_DRIVER);
		} catch (ClassNotFoundException e){
			System.out.println("Nao encontrou o driver chamado " + BD.JDBC_DRIVER+ " na memoria");
		}
		try {
			c = DriverManager.getConnection(BD.URL_CONEXAO, BD.USUARIO, BD.SENHA);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return c;
	}
}
