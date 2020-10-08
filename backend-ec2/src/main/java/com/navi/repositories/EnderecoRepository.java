package com.navi.repositories;

import com.navi.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findAllByCep(String cep);

    Endereco findByCep (String cep);

    Optional<Endereco> findById(Integer id);
}
