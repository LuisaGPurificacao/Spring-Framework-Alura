package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.getConexao();

		Statement stmt = con.createStatement();

		stmt.execute("insert into produto (nome, descricao) values ('Mouse', 'Mouse sem fio')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet rst = stmt.getGeneratedKeys();

		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}

	}

}
