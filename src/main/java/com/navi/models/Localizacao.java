package com.navi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_localizacao;

    @Column(name = "latitude")
    private String latitutde;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "fk_entregador")
    @OneToMany(mappedBy = "fk_entregador")
    private Entregador fk_entregador;

    public Long getId_localizacao() { return id_localizacao; }

    public String getLatitutde() { return latitutde; }

    public String getLongitude() { return longitude; }

    public Entregador getFk_entregador() { return fk_entregador; }

    public void setId_localizacao(Long id_localizacao) { this.id_localizacao = id_localizacao; }

    public void setLatitutde(String latitutde) { this.latitutde = latitutde; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public void setFk_entregador(Entregador fk_entregador) { this.fk_entregador = fk_entregador; }

}
