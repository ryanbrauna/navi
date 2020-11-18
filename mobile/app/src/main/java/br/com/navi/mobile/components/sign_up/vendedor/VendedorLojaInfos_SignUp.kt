package br.com.navi.mobile.components.sign_up.vendedor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.models.Vendedor
import kotlinx.android.synthetic.main.activity_vendedor_loja_infos_sign_up.*

class VendedorLojaInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedor_loja_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, VendedorOtherInfos_SignUp::class.java))
    }

    fun backToOtherInfos(component : View) {
        val previousScreen = Intent(this, VendedorOtherInfos_SignUp::class.java)
        startActivity(previousScreen)
    }

    private var vendedor : Vendedor = Vendedor(null, "", "", "", "", "")
    private var loja : Loja = Loja(null, "", "", null, null)


    fun addLojaInfos(component: View) {
        val vendedorEnderecoinfosSignup = Intent(this, VendedorEnderecoInfos_SignUp::class.java)

        this.vendedor.nome = intent.getStringExtra("nome")
        this.vendedor.email = intent.getStringExtra("email")
        this.vendedor.cnpj = intent.getStringExtra("cnpj")
        this.vendedor.telefone = intent.getStringExtra("telefone")
        this.vendedor.senha = intent.getStringExtra("senha")

        this.loja.nome = et_lojaNome.text.toString()
        this.loja.descricao = et_LojaDescricao.text.toString()

        vendedorEnderecoinfosSignup.putExtra("nome", this.vendedor.nome)
        vendedorEnderecoinfosSignup.putExtra("email", this.vendedor.email)
        vendedorEnderecoinfosSignup.putExtra("senha", this.vendedor.senha)
        vendedorEnderecoinfosSignup.putExtra("telefone", this.vendedor.telefone)
        vendedorEnderecoinfosSignup.putExtra("cnpj", this.vendedor.cnpj)

        vendedorEnderecoinfosSignup.putExtra("nomeLoja", this.loja.nome)
        vendedorEnderecoinfosSignup.putExtra("descricao", this.loja.descricao)

        startActivity(Intent(this, VendedorEnderecoInfos_SignUp::class.java))

    }

}