package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CicloDeVida {

	public static void main(String[] args) {

		Categoria categoriaCelular = new Categoria("CELULAR");

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		em.persist(categoriaCelular);
		categoriaCelular.setNome("XPTO");

		em.flush();
		em.clear();

		// o método MERGE não muda o estado da entidade passada para MANAGED, essa
		// entidade continua no estado DETACHED
		// ele devolve uma nova referência, e essa referência está no estado MANAGED

		categoriaCelular = em.merge(categoriaCelular);
		categoriaCelular.setNome("1234");
		em.flush();
		em.clear();
		em.remove(categoriaCelular);
		em.flush();
	}

}
