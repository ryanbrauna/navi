package com.navi.repositories;

import com.navi.models.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    Comprador findByCpf (Integer cpf);

}
