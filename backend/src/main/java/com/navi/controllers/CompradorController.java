package com.navi.controllers;

import com.navi.models.Comprador;
import com.navi.repositories.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompradorController {

    @Autowired
    private CompradorRepository repository;

    @PostMapping("/cadastro/comprador")
    public ResponseEntity createComprador(@RequestBody Comprador novoComprador) {
        repository.save(novoComprador);
        return ResponseEntity.ok(novoComprador);
    }

    @GetMapping("/compradores")
    public ResponseEntity getCompradores() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping("/comprador/{cpf}/atualizar")
    public ResponseEntity updateComprador(
            @PathVariable Integer cpf,
            @RequestBody Comprador compradorAtualizado) {

        Comprador comprador = repository.findByCpf(cpf);

        comprador.setNome(compradorAtualizado.getNome());
        comprador.setEmail(compradorAtualizado.getEmail());
        comprador.setSenha(compradorAtualizado.getSenha());
        comprador.setCpf(compradorAtualizado.getCpf());
        comprador.setEndereco(compradorAtualizado.getEndereco());

        repository.save(comprador);
        return ResponseEntity.ok(comprador);


    }

    @DeleteMapping("/comprador/{cpf}/excluir")
    public ResponseEntity deleteComprador( @PathVariable Integer cpf) {
        Comprador search = repository.findByCpf(cpf);

        repository.delete(search);
        return ResponseEntity.ok(search);
    }

}
