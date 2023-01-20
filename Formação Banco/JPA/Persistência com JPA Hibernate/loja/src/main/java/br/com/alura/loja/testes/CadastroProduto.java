package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.CategoriaId;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		cadastrarProduto();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> lista = produtoDao.buscarPorNomeCategoria("CELULA");

		lista.forEach(p2 -> System.out.println(p.getNome()));

		BigDecimal precoProduto = produtoDao.buscarPrecoPorNome("Xiaomi");
		System.out.println("Preço do produto: " + precoProduto);
	}

	private static void cadastrarProduto() {
		Categoria categoriaCelular = new Categoria("CELULAR");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), categoriaCelular);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoriaCelular);
		produtoDao.cadastrar(celular);

		em.getTransaction().commit();

		em.find(Categoria.class, new CategoriaId("CELULAR", "xpto"));

		em.close();
	}

}
