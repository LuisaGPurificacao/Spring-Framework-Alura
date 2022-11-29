package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.getConexao();

		PreparedStatement stmt = con.prepareStatement("select * from produto");
		stmt.execute();

		ResultSet rst = stmt.getResultSet();

		while (rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			System.out.println(id + " - " + nome);
			System.out.println(descricao);
			System.out.println();
		}

		con.close();

	}

}
