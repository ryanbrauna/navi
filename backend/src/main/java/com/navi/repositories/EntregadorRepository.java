package com.navi.repositories;

import com.navi.models.Entregador;
import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {

    List<Entregador> findAllByVendedor(Vendedor vendedor);

    List<Entregador> findByCpf (String cpf);

}
