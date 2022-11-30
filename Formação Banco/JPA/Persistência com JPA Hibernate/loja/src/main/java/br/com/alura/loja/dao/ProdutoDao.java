package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		em.persist(produto);
	}
	
}
