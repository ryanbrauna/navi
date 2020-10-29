package br.com.navi.mobile.services

import br.com.navi.mobile.models.Loja
import retrofit2.Call
import retrofit2.http.*

interface LojaService {

    @GET("/lojas")
    fun getLojas() : Call<List<Loja>>

    @GET("/vendedor/{cnpj}/lojas")
    fun getLoja(@Path("cnpj") cnpj : String) : Call<Loja>

    @POST("/cadastro/vendedor/{cnpj}/loja")
    fun createLoja(
        @Path("cnpj") cnpj : String,
        @Body loja : Loja) : Call<Loja>

    @PUT("/vendedor/{cnpj}/loja")
    fun updateLoja(
        @Path("cnpj") cnpj : String,
        @Body loja : Loja) : Call<Loja>

    @DELETE("/loja/{id}")
    fun deleteLoja(@Path("id") id : Int) : Call<String>

}