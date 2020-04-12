package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comprador")
public class Comprador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_comprador;

    @Column(name = "nome")
    private String nome;

    @Email(message = "* Email é obrigatório")
    private String email;

    @Column(name = "senha")
    @Length(min = 5, message = "A senha deve ter mais de 5 caracteres")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "n_cpf")
    @Length(min = 11, max = 11)
    private String n_cpf;

    public Comprador(Long id_comprador, String nome, @Email(message = "* Email é obrigatório") String email, @Length(min = 5, message = "A senha deve ter mais de 5 caracteres") String senha, String telefone, @Length(min = 11, max = 11) String n_cpf) {
        this.id_comprador = id_comprador;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.n_cpf = n_cpf;
    }

    public Long getId_comprador() { return id_comprador; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public String getTelefone() { return telefone; }

    public String getN_cpf() { return n_cpf; }

    public void setId_comprador(Long id_comprador) { this.id_comprador = id_comprador; }

    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) { this.email = email; }

    public void setSenha(String senha) { this.senha = senha; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public void setN_cpf(String n_cpf) { this.n_cpf = n_cpf; }

}
