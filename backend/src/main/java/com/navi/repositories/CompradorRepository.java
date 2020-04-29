package com.navi.repositories;

import com.navi.models.Comprador;
import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    Comprador findByCpf (Integer cpf);

    List<Comprador> findByLogin (String email, String senha);

}
