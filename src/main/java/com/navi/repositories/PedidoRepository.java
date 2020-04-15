package com.navi.repositories;

import com.navi.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdPedido(Long id_pedido);

}
