package com.navi.auth;

import com.navi.models.Vendedor;
import com.navi.repositories.VendedorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendedorProcess {

    private VendedorRepository repository;

    @GetMapping("/login")
    public ResponseEntity loginVendedor(@RequestBody String email, String senha) {
       Vendedor vendedor = this.repository.Login(email, senha);

       return ResponseEntity.accepted().body(vendedor);
    }

}
