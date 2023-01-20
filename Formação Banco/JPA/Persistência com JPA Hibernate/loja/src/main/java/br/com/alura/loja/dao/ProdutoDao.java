package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		if (nome != null && !nome.trim().isEmpty())
			jpql = "AND p.nome = :nome";
		if (preco != null)
			jpql = "AND p.preco = :preco";
		if (dataCadastro != null)
			jpql = "AND p.dataCadastro = :dataCadastro";

		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

		if (nome != null && !nome.trim().isEmpty())
			query.setParameter("nome", nome);
		if (preco != null)
			query.setParameter("preco", preco);
		if (dataCadastro != null)
			query.setParameter("dataCadastro", dataCadastro);

		return query.getResultList();
	}

	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();

		if (nome != null && !nome.trim().isEmpty())
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		if (preco != null)
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		if (dataCadastro != null)
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));

		query.where(filtros);

		return em.createQuery(query).getResultList();
	}

}
