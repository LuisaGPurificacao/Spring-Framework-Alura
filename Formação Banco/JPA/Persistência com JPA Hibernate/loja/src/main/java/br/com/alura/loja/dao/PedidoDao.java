package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		em.merge(pedido);
	}

	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		em.remove(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return em.find(Pedido.class, id);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "select sum(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioVendasVo( produto.nome, SUM(item.quantidade), MAX(pedido.data)) "
				+ "FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto "
				+ "GROUP BY produto.nome ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
	}

	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("select p from Pedido p join fetch p.cliente where p.id = :id", Pedido.class)
				.setParameter("id", id).getSingleResult();
	}

}
