package br.com.alura.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Iterable<Pedido> findByStatus(StatusPedido status);

	@Query("select p from Pedido p join p.user u where u.username = :username")
	Iterable<Pedido> findAllByUsuario(@Param("username") String username);

}
