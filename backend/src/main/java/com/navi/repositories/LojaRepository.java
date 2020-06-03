package com.navi.repositories;

import com.navi.models.Endereco;
import com.navi.models.Loja;
import com.navi.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LojaRepository extends JpaRepository<Loja, Integer> {

    Loja findByEndereco(Endereco endereco);

    Loja findByVendedor(Vendedor vendedor);

}
