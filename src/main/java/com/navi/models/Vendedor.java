package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_vendedor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    @Email(message = "* Email é obrigatório")
    private String email;

    @Column(name = "senha")
    @Length(min = 5, message = "A senha deve ter mais de 5 caracteres")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "n_cnpj")
    @Length(min = 18, max = 18)
    private String n_cnpj;

    public Vendedor(String nome, @Email(message = "* Email é obrigatório") String email, @Length(min = 5, message = "A senha deve ter mais de 5 caracteres") String senha, String telefone, @Length(min = 18, max = 18) String n_cnpj) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.n_cnpj = n_cnpj;
    }

    public Long getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Long id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getN_cnpj() {
        return n_cnpj;
    }

    public void setN_cnpj(String n_cnpj) {
        this.n_cnpj = n_cnpj;
    }
}
