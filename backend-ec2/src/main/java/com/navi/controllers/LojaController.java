package com.navi.controllers;

import com.navi.models.Loja;
import com.navi.models.Vendedor;
import com.navi.repositories.LojaRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LojaController {

    @Autowired
    private LojaRepository repository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @PostMapping("/cadastro/vendedor/{cnpj}/loja")
    public ResponseEntity createLoja(
            @RequestBody Loja novaLoja,
            @PathVariable String cnpj) {

        Vendedor vendedor = vendedorRepository.findByCnpj(cnpj).get(0);
        novaLoja.setVendedor(vendedor);

        repository.save(novaLoja);

        return ResponseEntity.ok(novaLoja);
    }

    @GetMapping("/lojas")
    public ResponseEntity getLojas () {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @GetMapping("/vendedor/{cnpj}/lojas")
    public ResponseEntity getLoja (
            @PathVariable String cnpj) {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor vendedor = vendedorRepository.findByCnpj(cnpj).get(0);
            Loja loja = repository.findByVendedor(vendedor);

            return ResponseEntity.ok(loja);
        }
    }

    @PutMapping("/vendedor/{cnpj}/loja")
    public ResponseEntity updateLoja (
            @PathVariable String cnpj,
            @RequestBody Loja lojaAtualizada) {
        Loja loja = repository.findByVendedor(vendedorRepository.findByCnpj(cnpj).get(0));

        loja.setNome(lojaAtualizada.getNome());
        loja.setDescricao(lojaAtualizada.getDescricao());
        loja.setEndereco(lojaAtualizada.getEndereco());
        loja.setVendedor(vendedorRepository.findByCnpj(cnpj).get(0));

        repository.save(loja);
        return ResponseEntity.ok(loja);
    }

    @DeleteMapping("/loja/{id}")
    public String delete ( @PathVariable Integer id) {
        repository.deleteById(id);

        return "Loja Deletada";
    }

}
