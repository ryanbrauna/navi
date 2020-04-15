package com.navi.repositories;

import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Vendedor findByCNPJ(Integer cnpj);

    Vendedor Login(String email, String senha);

}
