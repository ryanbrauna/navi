package com.navi.client;

import com.navi.models.Cep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "http://viacep.com.br")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    Cep getCep (@PathVariable String cep);


}
