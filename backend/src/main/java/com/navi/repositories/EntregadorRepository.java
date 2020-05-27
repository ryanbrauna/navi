package com.navi.repositories;

import com.navi.models.Entregador;
import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {

    List<Entregador> findAllByVendedor(Vendedor vendedor);

    List<Entregador> findByCpf (String cpf);

}
