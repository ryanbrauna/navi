package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "n_cep")
    private Integer n_cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "uf")
    @Length(max = 2, min = 2, message = "O campo uf deve ter no minimo 2 caracteres")
    private String uf;

    @Column(name = "numero")
    @Length(min = 1, message = "O campo numero deve ser preenchido")
    private Integer numero;

    @Column(name = "complemento")
    private String complememnto;

    public void displayEndereco(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("n_cep") + "\t"
                    + resultSet.getString("logradouro") + "\t"
                    + resultSet.getString("bairro") + "\t"
                    + resultSet.getString("localidade") + "\t"
                    + resultSet.getString("uf") + "\t"
                    + resultSet.getInt("numero") + "\t"
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