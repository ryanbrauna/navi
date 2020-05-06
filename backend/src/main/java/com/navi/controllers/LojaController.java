package com.navi.controllers;

import com.navi.models.Loja;
import com.navi.models.Vendedor;
import com.navi.repositories.LojaRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "localhost:3000")
public class LojaController {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @PostMapping("/cadastro/vendedor/{id}/loja")
    public ResponseEntity createLoja(
            @RequestBody Loja novaLoja,
            @PathVariable Integer id) {

        Vendedor vendedor = vendedorRepository.findById(id).get();
        novaLoja.setVendedor(vendedor);

        repository.save(novaLoja);

        return ResponseEntity.ok(novaLoja);
    }

}
