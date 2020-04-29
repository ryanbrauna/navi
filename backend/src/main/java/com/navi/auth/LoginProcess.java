package com.navi.auth;

import com.navi.models.Comprador;
import com.navi.models.Vendedor;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
                    return ResponseEntity.notFound().build();
                }
                else if (compradorRepository.findByEmail(email).isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                else {
                    if (vendedorRepository.findByEmail(email).isEmpty()) {
                        this.comprador = compradorRepository.findByEmail(email).get(0);
                        if (comprador.getSenha().equals(senha)){
                            list.add(this.comprador);
                            logado = true;
                            return ResponseEntity.accepted().body(comprador);
                        }
                        else {
                            return ResponseEntity.notFound().build();
                        }
                    }
                    else {
                        this.vendedor = vendedorRepository.findByEmail(email).get(0);
                        if (vendedor.getSenha().equals(senha)) {
                            list.add(vendedor);
                            logado = true;
                            return ResponseEntity.accepted().body(vendedor);
                        }
                        else {
                            return ResponseEntity.notFound().build();
                        }
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
