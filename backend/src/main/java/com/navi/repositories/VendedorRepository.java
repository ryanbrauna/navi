package com.navi.repositories;

import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findByCnpj (String cnpj);

    Optional<Vendedor> searchCnpj (String cnpj);

    List<Vendedor> findByLogin (String email, String senha);

}
