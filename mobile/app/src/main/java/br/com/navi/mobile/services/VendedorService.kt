package br.com.navi.mobile.services

import br.com.navi.mobile.models.Vendedor
import retrofit2.Call
import retrofit2.http.*

interface VendedorService {

    @GET("vendedores")
    fun getVendedores() : Call<List<Vendedor>>

    @GET("/vendedor/{cnpj}")
    fun getVendedor(@Path("cnpj") cnpj : String) : Call<Vendedor>

    @POST("/cadastro/vendedor")
    fun createVendedor(@Body vendedor : Vendedor) : Call<Vendedor>

    @PUT("/vendedor/{cnpj}")
    fun updateVendedor(
        @Path("cnpj") cnpj : String,
        @Body vendedor : Vendedor) : Call<Vendedor>

    @DELETE("/vendedor/excluir/{cnpj}")
    fun deleteVendedor(@Path("cnpj") cnpj: String) : Call<Void>

    // O DELETE criminoso
    @DELETE("/vendedor/{id}")
    fun delete(@Path("id") id : Int) : Call<Void>

}