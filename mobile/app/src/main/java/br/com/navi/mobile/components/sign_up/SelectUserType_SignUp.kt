package br.com.navi.mobile.components

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.components.sign_up.comprador.CompradorInfos_SignUp
import br.com.navi.mobile.components.sign_up.vendedor.VendedorInfos_SignUp
import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.Vendedor

open class SelectUserType_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user_type__sign_up)
    }

    private var comprador : Comprador = Comprador(null, "", "", "", "", "", null)
    private var vendedor : Vendedor = Vendedor(null, "", "", "", "","")

    fun backToLogin(component : View) {
        val previousScreen = Intent(this, Login::class.java)
        startActivity(previousScreen)
    }

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

    fun createVendedor(view: View) : Vendedor {
        val vendedorInfosActivity = Intent(this, VendedorInfos_SignUp::class.java)

        vendedorInfosActivity.putExtra("nome", vendedor.nome)
        vendedorInfosActivity.putExtra("email", vendedor.email)
        vendedorInfosActivity.putExtra("senha", vendedor.senha)
        vendedorInfosActivity.putExtra("telefone", vendedor.telefone)
        vendedorInfosActivity.putExtra("cnpj", vendedor.cnpj)

        startActivity(Intent(this, VendedorInfos_SignUp::class.java))
        return this.vendedor
    }



}