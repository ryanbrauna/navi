package br.com.navi.mobile.components.entregador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_perfil_entregador.*

class PerfilEntregador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_entregador)

        imgPerfil()
        fillInformation()

        //bt voltar
        bt_voltar.setOnClickListener {
            startActivity(Intent(this, MainEntregador::class.java))
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
        cnhUser = ""
        println("codUser: $codUser")
    }

    fun fillInformation() {
        et_perfil_nome.setText(nomeUser)
        et_perfil_email.setText(emailUser)
        et_perfil_cpf.setText(codUser)
        et_perfil_cnh.setText(cnhUser)
    }

    fun imgPerfil() {
        val imageView: ImageView = findViewById(R.id.imageView_perfil)
        Glide.with(this).load(R.drawable.perfil_entregador).circleCrop().into(imageView)
    }


}