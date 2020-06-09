package com.navi.export;

import com.navi.models.*;
import com.navi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ExportController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public static void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException e) {
            System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException e) {
            System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    };

    @GetMapping("/{cnpj}/exportacao")
    public String registrar (@PathVariable String cnpj) {

        String nomeArq = "ArquivoPedidos.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contagem = 0;

        Vendedor vendedor = vendedorRepository.findOneByCnpj(cnpj);
        Date data = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00PEDIDOS";
        header += vendedor.getCnpj();
        header += formatter.format(data);
        header += "01";

        gravaRegistro(nomeArq, header);

        Loja loja = lojaRepository.findByVendedor(vendedor);
        Pedido pedidoEncontrado = pedidoRepository.findAllByLoja(loja).get(1);
        Comprador comprador = pedidoEncontrado.getComprador();

        corpo += "01";
        corpo += String.format("%-5d", pedidoEncontrado.getNumeroDoPedido());
        corpo += String.format("%-5s", comprador.getCpf());
        corpo += String.format("%-50s", pedidoEncontrado.getDescricao());
        corpo += String.format("%05.2f", pedidoEncontrado.getPreco());
        corpo += String.format("%-40s", pedidoEncontrado.getAnotacoes());
        corpo += String.format("%20s", pedidoEncontrado.getEntregador().getCpf());
        corpo += String.format("%20s", pedidoEncontrado.getEndereco().getCep());

        contagem++;
        gravaRegistro(nomeArq, corpo);

        trailer += "02";
        trailer += String.format("%010d", contagem);
        gravaRegistro(nomeArq, trailer);

        return "Registro Criado";
    }

}
