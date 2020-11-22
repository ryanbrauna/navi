package br.com.navi.mobile.models

import com.google.gson.annotations.SerializedName

data class Pedido(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("numeroDoPedido")
    var numeroDoPedido: String,

    @SerializedName("descricao")
    var descricao : String,

    @SerializedName("anotacoes")
    var anotacoes : String,

    @SerializedName("preco")
    var preco : String,

    @SerializedName("estado")
    var estado : String,

    @SerializedName("comprador")
    var comprador: Comprador?,

    @SerializedName("vendedor")
    var vendedor: Vendedor?,

    @SerializedName("entregador")
    var entregador: Entregador?) {}