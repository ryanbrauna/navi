package com.navi.controllers;

import com.navi.models.Vendedor;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @PostMapping("/cadastro/vendedor")
    public ResponseEntity createVendedor(@RequestBody Vendedor novoVendedor) {
        repository.save(novoVendedor);

        return ResponseEntity.created(null).body(novoVendedor);
    }

    @PutMapping("/vendedor/{cnpj}")
    public ResponseEntity updateVendedor(
            @PathVariable String cnpj,
            @RequestBody Vendedor vendedorAtualizado) {

        Vendedor vendedor = this.repository.findByCnpj(cnpj);
        Optional<Vendedor> searchVendedor = this.repository.searchCnpj(cnpj);

        if (searchVendedor.isPresent()) {
            vendedor.setNome(vendedorAtualizado.getNome());
            vendedor.setEmail(vendedorAtualizado.getEmail());
            vendedor.setSenha(vendedorAtualizado.getSenha());
            vendedor.setTelefone(vendedorAtualizado.getTelefone());
            vendedor.setCnpj(vendedorAtualizado.getCnpj());

            this.repository.save(vendedor);
            return ResponseEntity.ok(vendedor);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vendedor/excluir/{cnpj}")
    public ResponseEntity deleteVendedor(
            @PathVariable String cnpj) {
        Vendedor vendedor = this.repository.findByCnpj(cnpj);

        repository.delete(vendedor);
        return ResponseEntity.ok().build();
    }

}