package br.com.navi.mobile.utils

import br.com.navi.mobile.services.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAccessUtils {

        fun getInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun compradorService() : CompradorService = getInstance().create(CompradorService::class.java)

        fun vendedorService() : VendedorService = getInstance().create(VendedorService::class.java)

        fun lojaService() : LojaService = getInstance().create(LojaService::class.java)

        fun enderecoService() : EnderecoService = getInstance().create(EnderecoService::class.java)

        fun entregadorService() : EntregadorService = getInstance().create(EntregadorService::class.java)
}