package br.com.navi.mobile.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAccessUtils {

    companion object {

        fun getInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}