package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

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

	public void atualizar(Produto produto) {
		em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "select p from Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "select p from Produto p where p.nome like ?1";
		return em.createQuery(jpql, Produto.class).setParameter(1, "%" + nome + "%").getResultList();
	}

	public BigDecimal buscarPrecoPorNome(String nome) {
		String jpql = "select p.preco from Produto p where p.nome like ?1";
		return em.createQuery(jpql, BigDecimal.class).setParameter(1, "%" + nome + "%").getSingleResult();
	}
	
	public List<Produto> buscarPorNomeCategoria(String nome) {
		String jpql = "select p from Produto p where p.categoria.nome like :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", "%" + nome + "%").getResultList();
	}
	
}
