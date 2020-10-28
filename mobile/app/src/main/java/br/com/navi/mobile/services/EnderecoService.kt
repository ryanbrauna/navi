package br.com.navi.mobile.services

import br.com.navi.mobile.models.Endereco
import br.com.navi.mobile.models.others.Cep
import retrofit2.Call
import retrofit2.http.*

interface EnderecoService {

    @GET
    fun getEnderecos() : Call<List<Endereco>>

    @GET("/loja/{cnpj}/endereco")
    fun getEnderecoByLojas(@Path("cnpj") cnpj : String) : Call<Endereco>

    @GET("/comprador/{cpf}/endereco")
    fun getEnderecoByComprador(@Path("cpf") cpf : String) : Call<Endereco>

    @GET("/cep/{cep}")
    fun searchCep(@Path("cep") cep : String) : Call<Cep>

    @POST("/cadastro/endereco/{cep}")
    fun createEndereco(@Path("cep") cep: String) : Call<Endereco>

    @POST("/cadastro/comprador/{cpf}/endereco")
    fun createEnderecoByComprador(
        @Path("cpf") cpf : String,
        @Body endereco: Endereco) : Call<Endereco>

    @POST("/cadastro/vendedor/{cnpj}/loja/endereco")
    fun createEnderecoByLoja(
        @Path("cnpj") cnpj : String,
        @Body endereco: Endereco) : Call<Endereco>

    @PUT("/{id}")
    fun updateEndereco(
        @Path("id") id : Int,
        @Body endereco: Endereco) : Call<Endereco>

    @DELETE("/deletar/{id}")
    fun deleteEndereco(@Path("id") id : Int) : Call<Endereco>

}