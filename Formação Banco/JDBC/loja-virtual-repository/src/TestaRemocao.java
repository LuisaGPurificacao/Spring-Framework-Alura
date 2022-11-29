import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.getConexao();

		PreparedStatement stmt = con.prepareStatement("delete from produto where id > ?");

		stmt.setInt(1, 3);

		stmt.execute();

		int linhasModificadas = stmt.getUpdateCount();

		System.out.println("Quantidade de linhas que foram excluídas: " + linhasModificadas);

	}

}
