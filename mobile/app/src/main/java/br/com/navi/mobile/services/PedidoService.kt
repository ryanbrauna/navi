package br.com.navi.mobile.services

import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.Pedido
import retrofit2.Call
import retrofit2.http.*

interface PedidoService {

    @GET("/comprador/{cpf}/pedidos")
    fun getPedidosComprador(@Path("cpf") cpf : String) : Call<List<Pedido>>

    @GET("/vendedor/{cnpj}/pedidos")
    fun getPedidosVendedor(@Path("cnpj") cnpj : String) : Call<List<Pedido>>

//    @GET("/comprador/{cpf}")
//    fun getComprador(@Path("cpf") cpf : String) : Call<Comprador>
//
//    @POST("/cadastro/comprador")
//    fun createComprador(@Body comprador: Comprador) : Call<Comprador>
//
//    @PUT("/comprador/{cpf}/atualizar")
//    fun updateComprador(
//        @Path("cpf") cpf : String,
//        @Body comprador: Comprador) : Call<Comprador>
//
//    @DELETE("/comprador/{cpf}/excluir")
//    fun deleteComprador(@Path("cpf") cpf: String) : Call<Comprador>
//
//    @DELETE("/comprador/{id}")
//    fun delete(@Path("id") id : Int) : Call<Comprador>

}