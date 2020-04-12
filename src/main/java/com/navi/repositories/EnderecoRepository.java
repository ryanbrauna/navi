package com.navi.repositories;

import com.navi.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Override
    List<Endereco> findAllById(Iterable<Long> iterable);

    List<Endereco> findAllByCEP(Integer cep);

    Endereco findById(Integer id);
}
