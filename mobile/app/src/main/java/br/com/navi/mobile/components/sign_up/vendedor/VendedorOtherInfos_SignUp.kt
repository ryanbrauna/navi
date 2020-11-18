package br.com.navi.mobile.components.sign_up.vendedor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.Endereco
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.models.Vendedor
import kotlinx.android.synthetic.main.activity_comprador_other_infos_sign_up.*
import kotlinx.android.synthetic.main.activity_vendedor_other_infos_sign_up.*

class VendedorOtherInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedor_other_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, VendedorInfos_SignUp::class.java))
    }

    private var comprador : Comprador = Comprador(null, "", "", "", "", "", null)

    fun backToVendedorInfos(component : View) {
        val previousScreen = Intent(this, VendedorInfos_SignUp::class.java)
        startActivity(previousScreen)
    }

    private var vendedor : Vendedor = Vendedor(null, "", "", "", "", "")
    private var loja : Loja = Loja(null, "", "", null, null)
    private var endereco : Endereco = Endereco(null, "", "", "", "", "", 0, "")


    fun addOtherVendedorInfos(component: View) {
        val vendedorLojaInfosActivity = Intent(this, VendedorLojaInfos_SignUp::class.java)

        this.vendedor.nome = intent.getStringExtra("nome")
        this.vendedor.email = intent.getStringExtra("email")
        this.vendedor.cnpj = intent.getStringExtra("cnpj")
        this.vendedor.telefone = et_vendedorTelefone.text.toString()
        if (et_vendedorSenha.text.toString() != et_vendedorConfirmar_senha.text.toString()) {
            Toast.makeText(applicationContext, "As senhas não se coincidem", Toast.LENGTH_SHORT).show()
            // Bloquear o botão
        }
        else {
            this.vendedor.senha = et_compradorSenha.text.toString()
        }


        vendedorLojaInfosActivity.putExtra("nome", this.vendedor.nome)
        vendedorLojaInfosActivity.putExtra("email", this.vendedor.email)
        vendedorLojaInfosActivity.putExtra("senha", this.vendedor.senha)
        vendedorLojaInfosActivity.putExtra("telefone", this.vendedor.telefone)
        vendedorLojaInfosActivity.putExtra("cnpj", this.vendedor.cnpj)

        startActivity(Intent(this, VendedorLojaInfos_SignUp::class.java))
    }

}