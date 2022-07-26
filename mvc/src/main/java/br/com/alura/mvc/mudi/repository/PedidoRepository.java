package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findByStatus(StatusPedido status);
	/*
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username ")
	List<Pedido> findAllByUsuario(@Param("username") String username);
	*/
}
