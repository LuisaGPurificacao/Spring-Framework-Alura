package br.com.alura.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Categoria;

public class CategoriaController {

	private CategoriaDAO dao;

	public CategoriaController() {
		Connection con = new ConnectionFactory().recuperarConexao();
		this.dao = new CategoriaDAO(con);
	}

	public List<Categoria> listar() {
		return this.dao.listar();
	}
}
