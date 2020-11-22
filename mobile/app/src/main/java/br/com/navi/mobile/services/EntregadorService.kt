package br.com.navi.mobile.services

import br.com.navi.mobile.models.Entregador
import br.com.navi.mobile.models.Pedido
import retrofit2.Call
import retrofit2.http.*

interface EntregadorService {

    @GET("/entregadores")
    fun getEntregadores() : Call<List<Entregador>>

    @GET("/entregador/{cpf}")
    fun getEntregador(@Path("cpf") cpf : String) : Call<Entregador>

    @GET("/{cnpj}/entregadores")
    fun getEntregadoresLoja(@Path("cnpj") cnpj : String) : Call<List<Entregador>>

//    @POST("/cadastro/entregador")
//    fun createEntregador(@Body Entregador: Entregador) : Call<Entregador>

//    @PUT("/Entregador/{cpf}/atualizar")
//    fun updateEntregador(
//            @Path("cpf") cpf : String,
//            @Body Entregador: Entregador) : Call<Entregador>

//    @DELETE("/Entregador/{cpf}/excluir")
//    fun deleteEntregador(@Path("cpf") cpf: String) : Call<Entregador>

//    @DELETE("/Entregador/{id}")
//    fun delete(@Path("id") id : Int) : Call<Entregador>
    
}