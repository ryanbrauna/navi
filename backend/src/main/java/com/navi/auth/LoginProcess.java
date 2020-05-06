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

                if (vendedorRepository.findByEmail(email).isEmpty()) {
                    if (compradorRepository.findByEmail(email).isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    else {
                        this.comprador = compradorRepository.findByEmail(email).get(0);
                        if (comprador.getSenha().equals(senha)) {
                            list.add(comprador);
                            logado = true;
                            return ResponseEntity.accepted().body(list);
                        }
                        else {
                            return ResponseEntity.notFound().build();
                        }
                    }
                }
                else {
                    this.vendedor = vendedorRepository.findByEmail(email).get(0);
                    if (vendedor.getSenha().equals(senha)) {
                        if (list.contains(comprador)) {
                            return ResponseEntity.status(401).build();
                        }
                        else {
                            list.add(vendedor);
                            logado = true;
                            return ResponseEntity.accepted().body(list);
                        }
                    }
                }
            }
            catch (Exception e) {
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
