package com.navi.repositories;

import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findByCnpj (String cnpj);

    List<Vendedor> findByEmail (String email);

}
