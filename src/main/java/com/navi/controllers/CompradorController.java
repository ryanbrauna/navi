package com.navi.controllers;

import com.navi.models.Comprador;
import com.navi.repositories.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CompradorController {

    @Autowired
    private CompradorRepository repository;

    @PostMapping("cadastro/comprador")
    public ResponseEntity createComprador(@RequestBody Comprador novoComprador) {

        repository.save(novoComprador);

        return ResponseEntity.created(null).body(novoComprador);

    }

    @PutMapping("comprador/{cpf}")
    public ResponseEntity updateComprador(
            @PathVariable Integer n_cpf,
            @RequestBody Comprador compradorAtualizado
    ) {

        Comprador comprador = this.repository.findByCPF(n_cpf);
        Optional<Comprador> searchComprador = this.repository.findById(n_cpf);

        if (searchComprador.isPresent()) {
            comprador.setNome(compradorAtualizado.getNome());
            comprador.setEmail(compradorAtualizado.getEmail());
            comprador.setSenha(compradorAtualizado.getSenha());
            comprador.setTelefone(compradorAtualizado.getTelefone());
            comprador.setN_cpf(compradorAtualizado.getN_cpf());

            this.repository.save(comprador);
            return ResponseEntity.ok(comprador);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("vendedor/excluir/{cpf}")
    public ResponseEntity deleteVendedor(
            @PathVariable Integer n_cpf) {
        Comprador comprador = this.repository.findByCPF(n_cpf);

        repository.delete(comprador);
        return ResponseEntity.ok().build();
    }

}
