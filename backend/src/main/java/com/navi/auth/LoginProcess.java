package com.navi.auth;

import com.navi.models.Comprador;
import com.navi.models.Vendedor;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3000")
public class LoginProcess {

    @Autowired
    CompradorRepository compradorRepository;

    @Autowired
    VendedorRepository vendedorRepository;

    private Comprador comprador;
    private Vendedor vendedor;
    private boolean logado = false;
    List list;


    @GetMapping("/login")
    public ResponseEntity loginUser(
            @RequestBody String email, String senha) {

        while (!logado) {
            try {

                if (vendedorRepository.findByLogin(email, senha).isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                else if (compradorRepository.findByLogin(email, senha).isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                else {
                    if (vendedorRepository.findByLogin(email, senha).isEmpty()) {
                        this.comprador = compradorRepository.findByLogin(email, senha).get(0);
                        list.add(this.comprador);
                        logado = true;
                        return ResponseEntity.accepted().body(comprador);
                    }
                    else {
                        this.vendedor = vendedorRepository.findByLogin(email, senha).get(0);
                        list.add(vendedor);
                        logado = true;
                        return ResponseEntity.accepted().body(vendedor);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return ResponseEntity.accepted().body(list);
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    @DeleteMapping("/logof")
    public ResponseEntity logofUser() {
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            list.clear();
            return ResponseEntity.ok().build();
        }
    }

}
