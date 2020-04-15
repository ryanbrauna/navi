package com.navi.repositories;

import com.navi.models.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    Comprador findByCPF(Integer n_cpf);

}
