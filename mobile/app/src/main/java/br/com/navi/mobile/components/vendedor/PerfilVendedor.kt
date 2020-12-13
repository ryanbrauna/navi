package br.com.navi.mobile.components.vendedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.components.login.emailUser
import br.com.navi.mobile.components.login.nomeUser
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.services.LojaService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_perfil_usuario.bt_sair
import kotlinx.android.synthetic.main.activity_perfil_vendedor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerfilVendedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_vendedor)

        imgPerfil()
        fillInformation()

        //bt voltar
        bt_voltar.setOnClickListener {
            startActivity(Intent(this, MainVendedor::class.java))
        }
        //bt sair
        bt_sair.setOnClickListener {
            clearInformation()
            startActivity(Intent(this, Login::class.java))
            Toast.makeText(baseContext, getString(R.string.txt_exit), Toast.LENGTH_SHORT).show()
        }
    }

    fun clearInformation() {
        println("codUser: $codUser")
        println("Limpando variavel...")
        nomeUser = ""
        emailUser = ""
        codUser = ""
        println("codUser: $codUser")
    }

    fun fillInformation() {
        et_perfil_nome.setText(nomeUser)
        et_perfil_email.setText(emailUser)
        et_perfil_cnpj.setText(codUser)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val requestLoja = retrofit.create(LojaService::class.java)
        val callLoja = requestLoja.getLoja(codUser)

        callLoja.enqueue(object : Callback<Loja> {
            override fun onResponse(call: Call<Loja>, response: Response<Loja>) {
                et_perfil_loja_nome.setText(response.body()?.nome)
                et_perfil_loja_desc.setText(response.body()?.descricao)
            }

            override fun onFailure(call: Call<Loja>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun imgPerfil() {
        val imageView: ImageView = findViewById(R.id.imageView_perfil)
        Glide.with(this).load(R.drawable.perfil_vendedor).circleCrop().into(imageView)
    }
}