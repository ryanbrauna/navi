package br.com.navi.mobile.models

import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("cep")
    var cep: String,

    @SerializedName("logradouro")
    var logradouro: String,

    @SerializedName("bairro")
    var bairro: String,

    @SerializedName("localidade")
    var localidade: String,

    @SerializedName("uf")
    var uf: String,

    @SerializedName("numero")
    var numero: Int,

    @SerializedName("complememnto")
    var complemento: String) {}