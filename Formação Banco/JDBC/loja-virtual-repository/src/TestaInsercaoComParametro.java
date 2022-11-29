import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {

//		Scanner scan = new Scanner(System.in);
//
//		System.out.println("Insira o nome do produto:");
//		String nome = scan.next() + scan.nextLine();
//		System.out.println("Insira a descrição:");
//		String descricao = scan.next() + scan.nextLine();
//		System.out.println("Insira o nome do produto:");
//		String nome2 = scan.next() + scan.nextLine();
//		System.out.println("Insira a descrição:");
//		String descricao2 = scan.next() + scan.nextLine();

		ConnectionFactory conFactory = new ConnectionFactory();

		try (Connection con = conFactory.getConexao()) {
			con.setAutoCommit(false);

			try (PreparedStatement stmt = con.prepareStatement("insert into produto (nome, descricao) values (?, ?)",
					Statement.RETURN_GENERATED_KEYS);) {

				adicionarVariavel("SmartTV", "45 polegadas", stmt);
				adicionarVariavel("Radio", "Radio de bateria", stmt);

				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Roolback executado");
				con.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, nome);
		stmt.setString(2, descricao);

		if (nome.equals("Radio"))
			throw new RuntimeException("Não foi possível adicionar o produto");

		stmt.execute();

		try (ResultSet rst = stmt.getGeneratedKeys()) {
			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O ID criado foi: " + id);
			}
		}
	}

}
