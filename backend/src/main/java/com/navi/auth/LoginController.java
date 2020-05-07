package com.navi.auth;

import com.navi.repositories.CompradorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    private List lista = new ArrayList();

    public Boolean validarLogin(String email, String senha) {
        if (compradorRepository.findOneByEmail(email) != null &&
                compradorRepository.findOneByEmail(email).getEmail().equals(email) &&
                compradorRepository.findOneByEmail(email).getSenha().equals(senha)) {
            return true;
        }
        else {
            if (vendedorRepository.findOneByEmail(email) != null &&
                    vendedorRepository.findOneByEmail(email).getEmail().equals(email) &&
                    vendedorRepository.findOneByEmail(email).getSenha().equals(senha)) {
                return true;
            }
            else {
                lista.add(compradorRepository.findByEmail(email));
                return true;
            }
        }
    }

    @GetMapping("/login")
    public ResponseEntity logar(@RequestBody String email, String senha) {

        lista.add(compradorRepository.findByEmail(email));

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/logof")
    public ResponseEntity logof() {
        lista.clear();

        return ResponseEntity.ok(lista);
    }


}
