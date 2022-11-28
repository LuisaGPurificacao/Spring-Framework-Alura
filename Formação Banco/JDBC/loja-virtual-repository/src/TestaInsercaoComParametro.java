import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {

		Scanner scan = new Scanner(System.in);

		System.out.println("Insira o nome do produto:");
		String nome = scan.next() + scan.nextLine();
		System.out.println("Insira a descrição:");
		String descricao = scan.next() + scan.nextLine();

		Connection con = ConnectionFactory.getConexao();

		PreparedStatement stmt = con.prepareStatement("insert into produto (nome, descricao) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, nome);
		stmt.setString(2, descricao);
		
		stmt.execute();
		
		ResultSet rst = stmt.getGeneratedKeys();

		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}

		scan.close();

	}

}
