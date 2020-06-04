package com.navi.controllers;

import com.navi.models.Entregador;
import com.navi.models.Vendedor;
import com.navi.repositories.EntregadorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3000")
public class EntregadorController {

    @Autowired
    private EntregadorRepository repository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @PostMapping("/{cnpj}/entregadores/cadastro")
    public ResponseEntity createEntregador (
            @PathVariable String cnpj,
            @RequestBody Entregador novoEntregador) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            novoEntregador.setVendedor(vendedorRepository.findByCnpj(cnpj).get(0));
            repository.save(novoEntregador);

            return ResponseEntity.created(null).body(novoEntregador);
        }
    }

    @GetMapping("/entregadores")
    public ResponseEntity getAllEntregadores () {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            List listaDeEntregadores = repository.findAll();

            return ResponseEntity.ok(listaDeEntregadores);
        }
    }

    @GetMapping("/{cnpj}/entregadores")
    public ResponseEntity getEntregadores (
            @PathVariable String cnpj) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor searchVendedor = vendedorRepository.findByCnpj(cnpj).get(0);
            List entregadoresList = repository.findAllByVendedor(searchVendedor);

            return ResponseEntity.ok(entregadoresList);
        }
    }

    @GetMapping("/{cnpj}/entregadores/{cpf}")
    public ResponseEntity getEntregador (
            @PathVariable String cnpj,
            @PathVariable String cpf) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            if (repository.findByCpf(cpf).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Entregador searchEntregador = repository.findByCpf(cpf).get(0);
            return ResponseEntity.ok(searchEntregador);
        }
    }

    @PutMapping("/{cnpj}/entregadores/{cpf}/atualizar")
    public ResponseEntity updateEntregador (
            @PathVariable String cnpj,
            @PathVariable String cpf,
            @RequestBody Entregador novoEntregador) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Entregador entregador = repository.findByCpf(cpf).get(0);

            entregador.setNome(novoEntregador.getNome());
            entregador.setEmail(novoEntregador.getEmail());
            entregador.setSenha(novoEntregador.getSenha());
            entregador.setCpf(novoEntregador.getCpf());
            entregador.setCnh(novoEntregador.getCnh());

            repository.save(entregador);
            return ResponseEntity.ok(entregador);
        }
    }

    @DeleteMapping("/{cnpj}/entregadores/{cpf}/excluir")
    public ResponseEntity deleteEntregador (
            @PathVariable String cnpj,
            @PathVariable String cpf) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Entregador entregador = repository.findByCpf(cpf).get(0);

            repository.delete(entregador);
            return ResponseEntity.ok(entregador);
        }
    }
}
