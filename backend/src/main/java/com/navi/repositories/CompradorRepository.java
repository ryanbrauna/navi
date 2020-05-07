package com.navi.repositories;

import com.navi.models.Comprador;
import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    List<Comprador> findByCpf (String cpf);

    @Query("select c from Comprador c where c.email = ?1")
    List<Comprador> findByEmail (String email);

    Comprador findOneByEmail (String email);

    List<Comprador>findByNome(String nome);

}
