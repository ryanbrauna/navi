package com.navi.auth;

import com.navi.repositories.CompradorRepository;
import com.navi.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

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
                return false;
            }
        }
    }

    @GetMapping("/login")
    public Boolean logar(@RequestBody String email, String senha) {
        return validarLogin(email, senha);
    }


}
