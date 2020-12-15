package br.com.navi.mobile.services

import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.Pedido
import retrofit2.Call
import retrofit2.http.*

interface PedidoService {

    @GET("/comprador/{cpf}/pedidos")
    fun getPedidosComprador(@Path("cpf") cpf : String) : Call<List<Pedido>>

    @GET("/vendedor/{cnpj}/pedidos")
    fun getPedidosLoja(@Path("cnpj") cnpj : String) : Call<List<Pedido>>

    @POST("/vendedor/{cnpj}/pedidos/registrar?cpf={cpf}")
    fun createPedido(@Body pedido: Pedido, @Path("cnpj") cnpj: String, @Path("cpf") cpf: String) : Call<Pedido>

    @PUT("/vendedor/{cnpj}/pedidos/{idPedido}?estado=Entregue")
    fun confirmarEntrega(@Path("cnpj") cnpj: String, @Path("idPedido") idPedido: Int) : Call<Pedido>

}