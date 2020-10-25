package com.navi.repositories;

import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    List<Vendedor> findByCnpj (String cnpj);

    Vendedor findOneByEmail (String email);

    Vendedor findOneByCnpj (String cnpj);

}
