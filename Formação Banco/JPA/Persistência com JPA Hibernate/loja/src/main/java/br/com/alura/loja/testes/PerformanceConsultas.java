package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.ItemPedido;
import br.com.alura.loja.model.Pedido;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {

		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();

		// Pedido pedido = em.find(Pedido.class, 1l);
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		em.close();

		System.out.println(pedido.getData() + " - " + pedido.getCliente().getNome());
		// System.out.println(pedido.getItens().size());

	}

	private static void popularBancoDeDados() {
		Categoria catCelular = new Categoria("CELULAR");
		Categoria catVideogame = new Categoria("VIDEOGAME");
		Categoria catInformatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), catCelular);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("4300"), catVideogame);
		Produto macbook = new Produto("Macbook", "Macbook pro", new BigDecimal("2900"), catInformatica);

		Cliente cliente = new Cliente("Luisa", "539.591.211-90");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));

		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(catCelular);
		categoriaDao.cadastrar(catVideogame);
		categoriaDao.cadastrar(catInformatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);

		em.getTransaction().commit();
		em.close();
	}

}
