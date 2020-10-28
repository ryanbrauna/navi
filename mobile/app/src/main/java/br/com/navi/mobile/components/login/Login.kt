package br.com.navi.mobile.components.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    var user = ""
    var senha = ""

    fun logando(componente: View){
        user = et_email.text.toString()
        senha = et_senha.text.toString()

        if(user=="comprador" &&  senha=="1234") {
            println("logou comprador")
        } else if(user=="vendedor" && senha=="1234") {
            println("logou comprador")
        } else if(user=="entregador" && senha=="1234") {
            println("logou comprador")
        } else {
            println("Errou!")
        }
    }

}