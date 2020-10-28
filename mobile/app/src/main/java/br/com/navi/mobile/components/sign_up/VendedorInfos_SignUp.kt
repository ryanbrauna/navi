package br.com.navi.mobile.components

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R

class VendedorInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedor_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, SelectUserType_SignUp::class.java))
    }

    fun backToSelectUserType(component : View) {
        val previousScreen = Intent(this, SelectUserType_SignUp::class.java)
        startActivity(previousScreen)
    }

}