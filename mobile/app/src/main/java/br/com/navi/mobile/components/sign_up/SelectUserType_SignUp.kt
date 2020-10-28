package br.com.navi.mobile.components

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.components.sign_up.comprador.CompradorInfos_SignUp
import br.com.navi.mobile.models.Comprador

open class SelectUserType_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user_type__sign_up)
    }

    private var comprador : Comprador = Comprador(null, "", "", "", "", "", null)

    fun createComprador(view: View) : Comprador {
        val compradorInfosActivity = Intent(this, CompradorInfos_SignUp::class.java)

        compradorInfosActivity.putExtra("nome", comprador.nome)
        compradorInfosActivity.putExtra("email", comprador.email)
        compradorInfosActivity.putExtra("senha", comprador.senha)
        compradorInfosActivity.putExtra("telefone", comprador.telefone)
        compradorInfosActivity.putExtra("cpf", comprador.cpf)

        startActivity(Intent(this, CompradorInfos_SignUp::class.java))
        return this.comprador
    }

    fun backToLogin(component : View) {
        val previousScreen = Intent(this, Login::class.java)
        startActivity(previousScreen)
    }

}