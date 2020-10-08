package com.navi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pedido")
    private Integer id;

    @Column(name = "numero_pedido")
    private Integer numeroDoPedido;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "anotacoes")
    private String anotacoes;

    @Column(name = "estado")
    private String estado;

    @JoinColumn(name = "id_comprador")
    @ManyToOne
    private Comprador comprador;

    @JoinColumn(name = "id_endereco")
    @ManyToOne
    private Endereco endereco;

    @JoinColumn(name = "id_loja")
    @ManyToOne
    private Loja loja;

    @JoinColumn(name = "id_entregador")
    @ManyToOne
    private Entregador entregador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDoPedido() {
        return numeroDoPedido;
    }

    public void setNumeroDoPedido(Integer numeroDoPedido) {
        this.numeroDoPedido = numeroDoPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
