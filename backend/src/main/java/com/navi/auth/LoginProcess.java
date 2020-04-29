package com.navi.auth;

import com.navi.models.Comprador;
import com.navi.models.Vendedor;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginProcess {

    @Autowired
    CompradorRepository compradorRepository;

    @Autowired
    VendedorRepository vendedorRepository;

    @GetMapping("/login")
    public ResponseEntity loginUser(
            @RequestBody String email, String senha) {

        Comprador comprador;
        Vendedor vendedor;

        if (vendedorRepository.findByLogin(email, senha).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else if (compradorRepository.findByLogin(email, senha).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            if (vendedorRepository.findByLogin(email, senha).isEmpty()) {
                comprador = compradorRepository.findByLogin(email, senha).get(0);
                return ResponseEntity.accepted().body(comprador);
            }
            else {
                vendedor = vendedorRepository.findByLogin(email, senha).get(0);
                return ResponseEntity.accepted().body(vendedor);
            }
        }
    }

}
