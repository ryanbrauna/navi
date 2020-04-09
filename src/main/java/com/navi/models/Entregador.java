package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Entregador")
public class Entregador {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id_entregador;

 @Column(name = "nome")
 private String nome;

 @Column(name = "n_cpf")
  private int n_cpf;

 @Column(name = "n_cnh")
    private int n_cnh;

 @Column(name = "fk_vendedor")
 @OneToMany(mappedBy = "fk_vendedor")
 private Vendedor fk_vendedor;

 public Long getId_entregador() { return id_entregador; }

 public String getNome() { return nome; }

 public int getN_cpf() { return n_cpf; }

 public int getN_cnh() { return n_cnh; }

 public Vendedor getFk_vendedor() { return fk_vendedor; }

 public void setId_entregador(Long id_entregador) { this.id_entregador = id_entregador;
 }

 public void setNome(String nome) { this.nome = nome; }

 public void setN_cpf(int n_cpf) { this.n_cpf = n_cpf; }

 public void setN_cnh(int n_cnh) { this.n_cnh = n_cnh; }

 public void setFk_vendedor(Vendedor fk_vendedor) { this.fk_vendedor = fk_vendedor; }

}
