package com.navi.controllers;

import com.navi.models.Comprador;
import com.navi.models.Endereco;
import com.navi.models.Loja;
import com.navi.models.Vendedor;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.EnderecoRepository;
import com.navi.repositories.LojaRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "localhost:3000")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public ResponseEntity getEnderecos() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PostMapping("/cadastro/endereco")
    public ResponseEntity createEndereco(@RequestBody Endereco novoEndereco) {
        repository.save(novoEndereco);

        return ResponseEntity.created(null).build();
    }

    @PostMapping("/cadastro/comprador/{cpf}/endereco")
    public ResponseEntity createEnderecoByComprador(
            @PathVariable String cpf,
            @RequestBody Endereco novoEndereco) {

        Comprador compradorCadastrado = compradorRepository.findByCpf(cpf);

        compradorCadastrado.setEndereco(novoEndereco);
        repository.save(novoEndereco);

        return ResponseEntity.ok(compradorCadastrado);
    }

    @PostMapping("/cadastro/vendedor/{cnpj}/loja/endereco")
    public ResponseEntity createEnderecoByLoja(
            @PathVariable String cnpj,
            @RequestBody Endereco novoEndereco) {

        repository.save(novoEndereco);

        Vendedor vendedorCadastrado = vendedorRepository.findByCnpj(cnpj);
        Loja lojaCadastrada = lojaRepository.findByVendedor(vendedorCadastrado);
        lojaCadastrada.setEndereco(novoEndereco);

        return ResponseEntity.ok(lojaCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEndereco(
            @PathVariable Integer id,
            @RequestBody Endereco enderecoAtualizado) {

        Endereco endereco = this.repository.findById(id).get();
        Optional<Endereco> searchEndereco = this.repository.findById(id);

        if (searchEndereco.isPresent()) {
            endereco.setCep(enderecoAtualizado.getCep());
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

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteEndereco(
            @PathVariable Integer id) {
        Endereco endereco = this.repository.findById(id).get();

        if (repository.findById(id).isPresent()) {
            this.repository.delete(endereco);

            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
