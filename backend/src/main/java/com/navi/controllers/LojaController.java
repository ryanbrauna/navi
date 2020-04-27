package com.navi.controllers;

import com.navi.models.Loja;
import com.navi.models.Vendedor;
import com.navi.repositories.LojaRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LojaController {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @PostMapping("/cadastro/vendedor/{cnpj}/loja")
    public ResponseEntity createLoja(
            @RequestBody Loja novaLoja,
            @PathVariable Integer cnpj) {

        Vendedor vendedor = vendedorRepository.findByCnpj(cnpj);

        novaLoja.setVendedor(vendedor);
        repository.save(novaLoja);

        return ResponseEntity.ok(novaLoja);
    }

}
