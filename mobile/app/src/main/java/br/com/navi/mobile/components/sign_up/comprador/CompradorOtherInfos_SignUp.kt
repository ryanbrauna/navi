package br.com.navi.mobile.components.sign_up.comprador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Comprador
import kotlinx.android.synthetic.main.activity_comprador_other_infos_sign_up.*

class CompradorOtherInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprador_other_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, CompradorInfos_SignUp::class.java))
    }

    private var comprador : Comprador = Comprador(null, "", "", "", "", "", null)

    fun backToCompradorInfos(component : View) {
        val previousScreen = Intent(this, CompradorInfos_SignUp::class.java)
        startActivity(previousScreen)
    }

    fun addOtherCompradorInfos(component: View) {
        val compradorInfosActivity = Intent(this, CompradorInfos_SignUp::class.java)

        this.comprador.nome = intent.getStringExtra("nome")
        this.comprador.email = intent.getStringExtra("email")
        this.comprador.cpf = intent.getStringExtra("cpf")
        this.comprador.telefone = et_compradorTelefone.text.toString()
        if (et_compradorSenha.text.toString() != et_compradorConfirmar_senha.text.toString()) {
            Toast.makeText(applicationContext, "As senhas não se coincidem", Toast.LENGTH_SHORT).show()
            // Bloquear o botão
        }
        else {
            this.comprador.senha = et_compradorSenha.text.toString()
        }


        compradorInfosActivity.putExtra("nome", this.comprador.nome)
        compradorInfosActivity.putExtra("email", this.comprador.email)
        compradorInfosActivity.putExtra("senha", this.comprador.senha)
        compradorInfosActivity.putExtra("telefone", this.comprador.telefone)
        compradorInfosActivity.putExtra("cpf", this.comprador.cpf)

        startActivity(Intent(this, CompradorEnderecoInfos_SignUp::class.java))
    }

}