package com.navi.models;

import javax.persistence.*;

@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_cep")
    private Integer n_cep;

    @Column(name = "logradouro", length = 100 )
    private String logradouro;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "localidade", length = 100)
    private String localidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 50)
    private String complememnto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getN_cep() {
        return n_cep;
    }

    public void setN_cep(Integer n_cep) {
        this.n_cep = n_cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplememnto() {
        return complememnto;
    }

    public void setComplememnto(String complememnto) {
        this.complememnto = complememnto;
    }
}