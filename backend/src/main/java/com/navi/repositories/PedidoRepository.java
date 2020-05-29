package com.navi.repositories;

import com.navi.models.Comprador;
import com.navi.models.Loja;
import com.navi.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByLoja (Loja loja);

    Pedido findByNumeroDoPedido (Integer numeroDoPedido);

    List<Pedido> findAllByComprador (Comprador comprador);

}
