package com.navi.controllers;

import com.navi.client.ViaCepClient;
import com.navi.models.*;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.EnderecoRepository;
import com.navi.repositories.LojaRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "ec2-52-0-161-170.compute-1.amazonaws.com")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ViaCepClient client;

    @GetMapping
    public ResponseEntity getEnderecos() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @GetMapping("/loja/{cnpj}/endereco")
    public ResponseEntity getEnderecoByLoja (@PathVariable String cnpj) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor search = vendedorRepository.findOneByCnpj(cnpj);
            Loja lojaSearch = lojaRepository.findByVendedor(search);

            return ResponseEntity.ok(lojaSearch.getEndereco());
        }
    }

    @GetMapping("/comprador/{cpf}/endereco")
    public ResponseEntity getEnderecoByComprador (@PathVariable String cpf) {
        if (compradorRepository.findByCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Comprador search = compradorRepository.findOneByCpf(cpf);

            return ResponseEntity.ok(search.getEndereco());
        }
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity consultarCep(@PathVariable String cep) {
        Cep cepEncontrado = client.getCep(cep);
        return ResponseEntity.ok(cepEncontrado);
    }

    @PostMapping("/cadastro/endereco/{cep}")
    public ResponseEntity createEndereco(
            @PathVariable String cep) {

        Endereco novoEndereco = new Endereco();
        Cep cepEncontrado = client.getCep(cep);


        novoEndereco.setCep(cepEncontrado.getCep());
        novoEndereco.setLogradouro(cepEncontrado.getLogradouro());
        novoEndereco.setBairro(cepEncontrado.getBairro());
        novoEndereco.setLocalidade(cepEncontrado.getLocalidade());
        novoEndereco.setUf(cepEncontrado.getUf());

        return ResponseEntity.created(null).body(novoEndereco);
    }

    @PostMapping("/cadastro/comprador/{cpf}/endereco")
    public ResponseEntity createEnderecoByComprador(
            @PathVariable String cpf,
            @RequestBody Endereco novoEndereco) {

        if (compradorRepository.findByCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Comprador compradorCadastrado = compradorRepository.findOneByCpf(cpf);
            compradorCadastrado.setEndereco(novoEndereco);
            repository.save(novoEndereco);

            return ResponseEntity.ok(compradorCadastrado);

        }

    }

    @PostMapping("/cadastro/vendedor/{cnpj}/loja/endereco")
    public ResponseEntity createEnderecoByLoja(
            @PathVariable String cnpj,
            @RequestBody Endereco novoEndereco) {


        if (vendedorRepository.findByCnpj(cnpj).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor vendedorCadastrado = vendedorRepository.findByCnpj(cnpj).get(0);
            Loja lojaCadastrada = lojaRepository.findByVendedor(vendedorCadastrado);
            lojaCadastrada.setEndereco(novoEndereco);

            repository.save(novoEndereco);
            return ResponseEntity.ok(lojaCadastrada);
        }
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
