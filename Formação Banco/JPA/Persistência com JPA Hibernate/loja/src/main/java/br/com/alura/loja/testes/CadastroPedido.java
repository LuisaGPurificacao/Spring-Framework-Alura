package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.alura.loja.vo.RelatorioVendasVo;

public class CadastroPedido {

	public static void main(String[] args) {

		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);

		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(1l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, produto3));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);

		BigDecimal totalVendido = pedidoDao.valorTotalVendido();

		System.out.println("\nValor total: " + totalVendido);

		em.getTransaction().commit();

		List<RelatorioVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);

		List<Produto> list = produtoDao.buscarPorNomeCategoria("IDEOGAM");

		list.forEach(p -> System.out.println(p.getNome() + " - " + p.getDescricao()));

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

		em.getTransaction().begin();

		categoriaDao.cadastrar(catCelular);
		categoriaDao.cadastrar(catVideogame);
		categoriaDao.cadastrar(catInformatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}

}
