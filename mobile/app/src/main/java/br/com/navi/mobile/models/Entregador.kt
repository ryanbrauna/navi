package br.com.navi.mobile.models

import com.google.gson.annotations.SerializedName

data class Entregador(
        @SerializedName("id")
        var id: Int?,

        @SerializedName("nome")
        var nome: String,

        @SerializedName("email")
        var email: String,

        @SerializedName("senha")
        var senha: String,

        @SerializedName("cpf")
        var cpf: String,

        @SerializedName("cnh")
        var cnh: String,

        @SerializedName("vendedor")
        var vendedor: Vendedor?){
}