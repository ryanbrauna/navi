package com.navi.controllers;

import com.navi.api.SMSApi;
import com.navi.models.Comprador;
import com.navi.models.Pedido;
import com.navi.repositories.CompradorRepository;
import com.navi.repositories.PedidoRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    @PostMapping("/enviar/{cpf}/{numeroDoPedido}")
    public String mensagemMudançadeEstado (
            @PathVariable String cpf,
            @PathVariable Integer numeroDoPedido) {
        Comprador comprador = compradorRepository.findOneByCpf(cpf);

        Pedido pedido = pedidoRepository.findByNumeroDoPedido(numeroDoPedido);

        if (!pedido.getEstado().equals("Pedido Registrado")) {
            Twilio.init(SMSApi.getAccountSid(), SMSApi.getAuthToken());
            Message message = Message
                    .creator(new PhoneNumber(comprador.getTelefone()),
                            new PhoneNumber("+12183878263"),
                            "Olá " + comprador.getNome() + ", o seu pedido de número " + pedido.getNumeroDoPedido() +
                                    " está/foi " + pedido.getEstado())
                    .create();
            System.out.println(message.getSid());
            return "Mensagem Enviada";
        }
        else {
            return "O Pedido não mudou de estado";
        }
    }


}
