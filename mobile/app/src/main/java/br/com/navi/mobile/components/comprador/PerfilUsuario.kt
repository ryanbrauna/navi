package br.com.navi.mobile.components.comprador

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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

class PerfilUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)
        
        imgPerfil()
        fillInformation()

        //bt voltar
        bt_voltar.setOnClickListener {
            startActivity(Intent(this, MainComprador::class.java))
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
        et_perfil_cpf.setText(codUser)
    }

    fun imgPerfil() {
        val imageView: ImageView = findViewById(R.id.imageView_perfil)
        Glide.with(this).load(R.drawable.comprador).circleCrop().into(imageView)
    }
}