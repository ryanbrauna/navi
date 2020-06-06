package com.navi.controllers;

import com.navi.models.Comprador;
import com.navi.models.Entregador;
import com.navi.models.Pedido;
import com.navi.models.Vendedor;
import com.navi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @PostMapping("/vendedor/{cnpj}/pedidos/registrar")
    public ResponseEntity createPedido(
            @PathVariable String cnpj,
            @RequestParam(required = true) String cpf,
            @RequestBody Pedido novoPedido) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor vendedor = vendedorRepository.findByCnpj(cnpj).get(0);

            novoPedido.setLoja(lojaRepository.findByVendedor(vendedor));
            novoPedido.setEndereco(lojaRepository.findByVendedor(vendedor).getEndereco());
            novoPedido.setComprador(compradorRepository.findByCpf(cpf).get(0));

            repository.save(novoPedido);
            return ResponseEntity.created(null).body(novoPedido);
        }
    }


    @GetMapping("/vendedor/{cnpj}/pedidos")
    public ResponseEntity getPedidosLoja (
            @PathVariable String cnpj) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Vendedor vendedor = vendedorRepository.findByCnpj(cnpj).get(0);

            List listaDePedidos = repository.findAllByLoja(lojaRepository.findByVendedor(vendedor));
            return ResponseEntity.ok(listaDePedidos);
        }
    }

    @GetMapping("/vendedor/{cnpj}/pedidos/{numeroDoPedido}")
    public ResponseEntity getOnePedidoLoja (
            @PathVariable String cnpj,
            @PathVariable Integer numeroDoPedido) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Pedido search = repository.findByNumeroDoPedido(numeroDoPedido);

            return ResponseEntity.ok(search);
        }
    }

    @GetMapping("/vendedor/{cnpj}/pedidos/comprador/{cpf}")
    public ResponseEntity getPedidosByComprador (
            @PathVariable String cnpj,
            @PathVariable String cpf) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Comprador search = compradorRepository.findByCpf(cpf).get(0);

            List listaDePedidos = repository.findAllByComprador(search);
            return ResponseEntity.ok(listaDePedidos);
        }
    }

    @GetMapping("/comprador/{cpf}/pedidos")
    public ResponseEntity getPedidosComprador (
            @PathVariable String cpf) {
        if (compradorRepository.findByCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Comprador comprador = compradorRepository.findByCpf(cpf).get(0);

            List listaDePedidos = repository.findAllByComprador(comprador);
            return ResponseEntity.ok(listaDePedidos);
        }
    }

    @GetMapping("/comprador/{cpf}/pedidos/{numeroDoPedido}")
    public ResponseEntity getOnePedidoComprador (
            @PathVariable String cpf,
            @PathVariable Integer numeroDoPedido) {
        if (compradorRepository.findByCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Pedido search = repository.findByNumeroDoPedido(numeroDoPedido);

            return ResponseEntity.ok(search);
        }
    }

    @PutMapping("/vendedor/{cnpj}/pedidos/{numeroDoPedido}")
    public ResponseEntity updateEstado (
            @PathVariable String cnpj,
            @PathVariable Integer numeroDoPedido,
            @RequestBody String estado) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Pedido search = repository.findByNumeroDoPedido(numeroDoPedido);

            search.setEstado(estado);
            repository.save(search);

            return ResponseEntity.ok(search);
        }
    }

    @PutMapping("/entregador/{cpf}/pedidos/{numeroDoPedido}")
    public ResponseEntity updateEntregador (
            @PathVariable String cpf,
            @PathVariable Integer numeroDoPedido) {
        if (entregadorRepository.findByCpf(cpf).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Pedido search = repository.findByNumeroDoPedido(numeroDoPedido);
            Entregador entregador = entregadorRepository.findByCpf(cpf).get(0);

            search.setEntregador(entregador);
            repository.save(search);

            return ResponseEntity.ok().body(search);
        }
    }

    @DeleteMapping("/vendedor/{cnpj}/pedidos/{numeroDoPedido}/excluir")
    public ResponseEntity deletePedido (
            @PathVariable String cnpj,
            @PathVariable Integer numeroDoPedido) {
        if (vendedorRepository.findByCnpj(cnpj).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            Pedido search = repository.findByNumeroDoPedido(numeroDoPedido);
            repository.delete(search);

            return ResponseEntity.ok(search);
        }
    }

    @DeleteMapping("/pedidos/{id}")
    public String deleteById (
            @PathVariable Integer id) {
        repository.deleteById(id);

        return "Pedido deletado";
    }

}
