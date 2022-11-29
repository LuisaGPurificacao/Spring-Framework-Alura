package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		System.out.println("Abrindo conexao");

		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.getConexao();
		
		System.out.println("Fechando conexao");
		
		con.close();

	}

}
