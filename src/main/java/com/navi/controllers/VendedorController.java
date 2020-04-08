package com.navi.controllers;

import com.navi.models.Vendedor;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendedorController {

    @Autowired
    VendedorRepository repository;

    @PostMapping("/cadastro/vendedor")
    public String create(@RequestBody Vendedor novoVendedor) {
        repository.save(new Vendedor(novoVendedor.getNome(), novoVendedor.getEmail(), novoVendedor.getSenha(),
                novoVendedor.getTelefone(), novoVendedor.getTelefone()));

        return "Usuario criado";
    }

}
