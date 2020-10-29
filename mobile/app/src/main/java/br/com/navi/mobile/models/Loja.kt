package br.com.navi.mobile.models

import com.google.gson.annotations.SerializedName

data class Loja(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("nome")
    var nome: String,

    @SerializedName("descricao")
    var descricao : String,

    @SerializedName("endereco")
    var endereco: Endereco?,

    @SerializedName("vendedor")
    var vendedor: Vendedor?) {}