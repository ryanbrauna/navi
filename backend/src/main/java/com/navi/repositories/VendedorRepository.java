package com.navi.repositories;

import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findByCnpj (Integer cnpj);

    List<Vendedor> findByLogin (String email, String senha);

}
