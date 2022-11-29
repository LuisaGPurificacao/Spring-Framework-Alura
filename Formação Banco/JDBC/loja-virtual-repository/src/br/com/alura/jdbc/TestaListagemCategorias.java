package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

public class TestagemListagemCategorias {

	public static void main(String[] args) throws SQLException {

		try (Connection con = new ConnectionFactory().getConexao()) {

			CategoriaDAO categoriaDAO = new CategoriaDAO(con);
			List<Categoria> listaCategorias = categoriaDAO.listar();

			listaCategorias.stream().forEach(ct -> {
				System.out.println(ct);
				try {
					for (Produto produto : new ProdutoDAO(con).listarPorCategoria(ct)) {
						System.out.println(ct.getNome() + " - " + produto.getNome());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			System.out.println();
			
			List<Categoria> listaCategoriasComProdutos = categoriaDAO.listarComProdutos();

			listaCategoriasComProdutos.stream().forEach(ct -> {
				System.out.println(ct);
				for (Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getNome());
				}
			});
			
		}
	}

}
