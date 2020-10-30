package br.com.navi.mobile.components.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.SelectUserType_SignUp
import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.Vendedor
import br.com.navi.mobile.services.CompradorService
import br.com.navi.mobile.services.VendedorService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_registre.setOnClickListener { openCadastrar() }
    }

    fun logando(componente:View){
        var autenticou = false;

        val user = et_email.text.toString()
        val senha = et_senha.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://navi--api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requestsComprador = retrofit.create(CompradorService::class.java)
        val callComprador = requestsComprador.getCompradores()

        val requestsVendedor = retrofit.create(VendedorService::class.java)
        val callVendedor = requestsVendedor.getVendedores()

//        val requestsEntregador = retrofit.create(::class.java)
//        val callEntregador = requestsEntregador.getEntregador()

        callComprador.enqueue(object: Callback<List<Comprador>>{
            override fun onFailure(call: Call<List<Comprador>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<Comprador>>,
                response: Response<List<Comprador>>
            ) {
                response.body()?.forEach {
                    if(user==it.email &&  senha==it.senha) {
                        Toast.makeText(baseContext, "Bem vindo Comprador ${it.nome}", Toast.LENGTH_SHORT).show()
                        autenticou = true
                    } else {
                        autenticou = false
                    }
                }
            }

        })

        if (!autenticou){
            callVendedor.enqueue(object: Callback<List<Vendedor>>{
                override fun onFailure(call: Call<List<Vendedor>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<Vendedor>>,
                    response: Response<List<Vendedor>>
                ) {
                    response.body()?.forEach {
                        if(user==it.email &&  senha==it.senha) {
                            Toast.makeText(baseContext, "Bem vindo Vendedor ${it.nome}", Toast.LENGTH_SHORT).show()
                            autenticou = true
                        } else {
                            autenticou = false
                        }
                    }
                }

            })
        }

//        if (!autenticou){
//            callEntregador.enqueue(object: Callback<List<Entregador>>{
//                override fun onFailure(call: Call<List<Entregador>>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onResponse(
//                    call: Call<List<Entregador>>,
//                    response: Response<List<Entregador>>
//                ) {
//                    response.body()?.forEach {
//                        if(user==it.email &&  senha==it.senha) {
//                            Toast.makeText(baseContext, "Bem vindo Entregador ${it.nome}", Toast.LENGTH_SHORT).show()
//                            autenticou = true
//                        } else {
//                            autenticou = false
//                        }
//                    }
//                }
//
//            })
//        }

        if (!autenticou){
            Toast.makeText(baseContext, "Seu email ou senha estão incorretos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCadastrar(){
        val intent = Intent(this, SelectUserType_SignUp::class.java)
        startActivity(intent)
    }

}