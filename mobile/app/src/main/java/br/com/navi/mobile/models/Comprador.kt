package br.com.navi.mobile.models

import com.google.gson.annotations.SerializedName

data class Comprador(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("nome")
    var nome: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("senha")
    var senha: String,

    @SerializedName("telefone")
    var telefone: String,

    @SerializedName("cpf")
    var cpf: String,

    @SerializedName("endereco")
    var endereco : Endereco?){
}