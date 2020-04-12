package com.navi.repositories;

import com.navi.models.Entregador;
import com.navi.models.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LojaRepository  extends JpaRepository<Loja, Long> {

    List<Loja> findByIdLoja(Long id_loja);

}
