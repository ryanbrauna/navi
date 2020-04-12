package com.navi.controllers;

import com.navi.models.Endereco;
import com.navi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity getEnderecos() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PostMapping
    public ResponseEntity createEndereco(@RequestBody Endereco novoEndereco) {
        repository.save(novoEndereco);

        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEndereco(
            @PathVariable Long id,
            @RequestBody Endereco enderecoAtualizado) {

        Endereco endereco = this.repository.findById(id).get();
        Optional<Endereco> searchEndereco = this.repository.findById(id);

        if (searchEndereco.isPresent()) {
            endereco.setN_cep(enderecoAtualizado.getN_cep());
            endereco.setLogradouro(enderecoAtualizado.getLogradouro());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setLocalidade(enderecoAtualizado.getLocalidade());
            endereco.setUf(enderecoAtualizado.getUf());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setComplememnto(enderecoAtualizado.getComplememnto());

            this.repository.save(endereco);
            return ResponseEntity.ok(endereco);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
