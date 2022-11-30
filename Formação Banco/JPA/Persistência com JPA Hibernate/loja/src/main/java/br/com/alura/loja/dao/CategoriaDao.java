package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}

}
