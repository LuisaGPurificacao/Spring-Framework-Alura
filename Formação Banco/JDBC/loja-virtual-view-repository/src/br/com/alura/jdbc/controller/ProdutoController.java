package br.com.alura.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Produto;

public class ProdutoController {

	private ProdutoDAO dao;

	public ProdutoController() {
		Connection con = new ConnectionFactory().recuperarConexao();
		this.dao = new ProdutoDAO(con);
	}

	public void deletar(Integer id) {
		System.out.println("Deletando produto");
		dao.deletar(id);
	}

	public void salvar(Produto produto) {
		System.out.println("Salvando produto");
		dao.salvarComCategoria(produto);
	}

	public List<Produto> listar() {
		return dao.listar();
	}

	public void alterar(String nome, String descricao, Integer id) {
		System.out.println("Alterando produto");
		dao.alterar(nome, descricao, id);
	}
}
