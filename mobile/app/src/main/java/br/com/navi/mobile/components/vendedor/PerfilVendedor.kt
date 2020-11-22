package br.com.navi.mobile.components.vendedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_perfil_usuario.bt_sair
import kotlinx.android.synthetic.main.activity_perfil_vendedor.*

class PerfilVendedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_vendedor)

        imgPerfil()

        //bt voltar
        bt_voltar.setOnClickListener {
            startActivity(Intent(this, MainVendedor::class.java))
        }
        //bt sair
        bt_sair.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            Toast.makeText(baseContext, getString(R.string.txt_exit), Toast.LENGTH_SHORT).show()
        }
    }

    fun imgPerfil() {
        val imageView: ImageView = findViewById(R.id.imageView_perfil)
        Glide.with(this).load(R.drawable.perfil).circleCrop().into(imageView)
    }
}