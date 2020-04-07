package com.navi.models;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer n_cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer numero;
    private String complememnto;

    public void displayEndereco(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("n_cep")  + "\t"
            + resultSet.getString("logradouro")  + "\t"
            + resultSet.getString("bairro")  + "\t"
            + resultSet.getString("localidade")  + "\t"
            + resultSet.getString("uf")  + "\t"
            + resultSet.getInt("numero")  + "\t"
            + resultSet.getString("complemento"));
        }
    }

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
