package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pedido")
public class Pedido {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id_pedido;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "anotacoes")
    private String anotacoes;

    @Column(name = "fk_comprador")
    @OneToMany(mappedBy = "fk_comprador")
    private Comprador fk_comprador;

    @Column(name = "fk_endereco")
    @OneToMany(mappedBy = "fk_endereco")
    private Endereco fk_endereco;

    @Column(name = "fk_entregador")
    @OneToMany(mappedBy = "fk_entregador")
    private Entregador fk_entregador;

    @Column(name = "fk_loja")
    @OneToMany(mappedBy = "fk_loja")
    private Loja fk_loja;

    public Long getId_pedido() { return id_pedido; }

    public String getDescricao() { return descricao; }

    public Integer getQuantidade() { return quantidade; }

    public Double getPreco() { return preco; }

    public String getAnotacoes() { return anotacoes; }

    public Comprador getFk_comprador() { return fk_comprador; }

    public Endereco getFk_endereco() { return fk_endereco; }

    public Entregador getFk_entregador() { return fk_entregador; }

    public Loja getFk_loja() { return fk_loja; }

}
