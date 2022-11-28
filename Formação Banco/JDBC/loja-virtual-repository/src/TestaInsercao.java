import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		Connection con = ConnectionFactory.getConexao();

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
