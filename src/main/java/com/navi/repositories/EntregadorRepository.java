package com.navi.repositories;

import com.navi.models.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    List<Entregador> findByCPF(String cpf);

}
