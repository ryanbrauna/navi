package com.navi.controllers;

import com.navi.database.VendedorDAO;
import com.navi.models.Vendedor;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendedorController {

    @Autowired
    VendedorRepository repository;

    private VendedorDAO vendedorDAO = new VendedorDAO();

    @PostMapping("/cadastro/vendedor")
    public String create(@RequestBody Vendedor novoVendedor) {

        Boolean criou = vendedorDAO.createVendedor(novoVendedor);

        repository.save(new Vendedor(novoVendedor.getNome(), novoVendedor.getEmail(), novoVendedor.getSenha(),
                novoVendedor.getTelefone(), novoVendedor.getTelefone()));

        return "Usuario criado";
    }

    @DeleteMapping("/vendedor/excluir")
    public String delete(@RequestBody Vendedor vendedor) {

        Boolean deletou = vendedorDAO.deleteVendedor(vendedor);

        repository.delete(vendedor);

        return "Usuario deletado";
    }

}
