package com.navi.controllers;

import com.navi.api.SMSApi;
import com.navi.models.Vendedor;
import com.navi.repositories.VendedorRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VendedorController {

    @Autowired
    private VendedorRepository repository;

    @PostMapping("/cadastro/vendedor")
    public ResponseEntity createVendedor(@RequestBody Vendedor novoVendedor) {
        repository.save(novoVendedor);

        if (novoVendedor.getTelefone().equals("+5511986743588") || novoVendedor.getTelefone().equals("+5511963944845")) {
            Twilio.init(SMSApi.getAccountSid(), SMSApi.getAuthToken());
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(novoVendedor.getTelefone()),
                    new com.twilio.type.PhoneNumber("+12183878263"),
                    "Olá " + novoVendedor.getNome() + ", seja bem-vindo a Navi").create();

            System.out.println(message.getSid());
        }

        return ResponseEntity.created(null).body(novoVendedor);
    }

    @PutMapping("/vendedor/{cnpj}")
    public ResponseEntity updateVendedor(
            @PathVariable String cnpj,
            @RequestBody Vendedor vendedorAtualizado) {

        Vendedor vendedor = this.repository.findByCnpj(cnpj).get(0);

            vendedor.setNome(vendedorAtualizado.getNome());
            vendedor.setEmail(vendedorAtualizado.getEmail());
            vendedor.setSenha(vendedorAtualizado.getSenha());
            vendedor.setTelefone(vendedorAtualizado.getTelefone());
            vendedor.setCnpj(vendedorAtualizado.getCnpj());

            this.repository.save(vendedor);
            return ResponseEntity.ok(vendedor);

    }

    @DeleteMapping("/vendedor/excluir/{cnpj}")
    public ResponseEntity deleteVendedor(
            @PathVariable String cnpj) {
        Vendedor vendedor = this.repository.findByCnpj(cnpj).get(0);

        repository.delete(vendedor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vendedores")
    public ResponseEntity getVendedores() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @GetMapping("/vendedor/{cnpj}")
    public ResponseEntity getVendedor(
            @PathVariable String cnpj
    ) {
        if (repository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Vendedor search = repository.findOneByCnpj(cnpj);
            return ResponseEntity.ok(search);
        }
    }

}
