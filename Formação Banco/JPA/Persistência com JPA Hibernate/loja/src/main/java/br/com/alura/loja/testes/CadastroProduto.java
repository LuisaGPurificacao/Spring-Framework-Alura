package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {

		Categoria categoriaCelular = new Categoria("CELULAR");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), categoriaCelular);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoriaCelular);
		produtoDao.cadastrar(celular);

		em.getTransaction().commit();
		em.close();

	}

}
