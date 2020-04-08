package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_loja;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fk_endereco")
    @OneToOne(mappedBy = "fk_endereco")
    private Endereco fk_endereco;

    @Column(name = "fk_vendedor")
    @OneToOne(mappedBy = "fk_vendedor")
    private Vendedor fk_vendedor;

    public Long getId_loja() {
        return id_loja;
    }

    public void setId_loja(Long id_loja) {
        this.id_loja = id_loja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getFk_endereco() {
        return fk_endereco;
    }

    public void setFk_endereco(Endereco fk_endereco) {
        this.fk_endereco = fk_endereco;
    }

    public Vendedor getFk_vendedor() {
        return fk_vendedor;
    }

    public void setFk_vendedor(Vendedor fk_vendedor) {
        this.fk_vendedor = fk_vendedor;
    }
}


