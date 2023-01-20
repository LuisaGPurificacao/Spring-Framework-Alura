package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.model.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {

		Produto comoda = new Produto("Cômoda", "Cômoda vertical");

		try (Connection con = new ConnectionFactory().getConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(con);
			produtoDao.salvar(comoda);
			List<Produto> listaProdutos = produtoDao.listar();
			
			listaProdutos.stream().forEach(lp -> System.out.println(lp));
		}
	}
}
