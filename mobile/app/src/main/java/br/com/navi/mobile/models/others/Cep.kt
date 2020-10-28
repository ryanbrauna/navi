package br.com.navi.mobile.models.others

import com.google.gson.annotations.SerializedName

data class Cep (
    @SerializedName("cep")
    var cep : String,

    @SerializedName("logradouro")
    var logradouro : String,

    @SerializedName("bairro")
    var bairro : String,

    @SerializedName("localidade")
    var localidade : String,

    @SerializedName("uf")
    var uf : String) {}