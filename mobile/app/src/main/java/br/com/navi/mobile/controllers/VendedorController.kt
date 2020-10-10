package br.com.navi.mobile.controllers

import br.com.navi.mobile.models.Vendedor
import retrofit2.Call
import retrofit2.http.GET

interface VendedorController {

    @GET("vendedores")
    fun getVendedores() : Call<List<Vendedor>>

}