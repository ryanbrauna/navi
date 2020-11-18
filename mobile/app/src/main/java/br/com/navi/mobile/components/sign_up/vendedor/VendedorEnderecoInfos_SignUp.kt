package br.com.navi.mobile.components.sign_up.vendedor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.models.Endereco
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.models.Vendedor
import br.com.navi.mobile.utils.ApiAccessUtils
import kotlinx.android.synthetic.main.activity_comprador_endereco_infos_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VendedorEnderecoInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprador_endereco_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, VendedorLojaInfos_SignUp::class.java))
    }

    fun backToOtherInfos(component : View) {
        val previousScreen = Intent(this, VendedorLojaInfos_SignUp::class.java)
        startActivity(previousScreen)
    }

    private var vendedor : Vendedor = Vendedor(null, "", "", "", "", "")
    private var loja : Loja = Loja(null, "", "", null, null)
    private var endereco : Endereco = Endereco(null, "", "", "", "", "", 0, "")


//    fun searchCep(component: View) {
//        val cepNumber : String = et_cep.text.toString()
////        var cep : Cep = Cep("", "",  "", "", "")
//
//        val call = ApiAccessUtils().enderecoService().searchCep(cepNumber)
//        call.enqueue(object : Callback<Cep> {
//            override fun onResponse(call: Call<Cep>,
//                                    response: Response<Cep>) {
//                if (response.isSuccessful && response.code() == 200) {
//                    val cep = response.body()
//
//                }
//            }
//
//            override fun onFailure(call: Call<Cep>,
//                                   t: Throwable) {
//                Log.e("onFailure error", t?.message)
//            }
//
//        }
//        )
//
//    }

    fun addEnderecoInfos(component: View) {
        val vendedorLojainfosSignup = Intent(this, VendedorLojaInfos_SignUp::class.java)

        this.endereco.cep = et_cep.text.toString()
        this.endereco.logradouro = et_logradouro.text.toString()
        this.endereco.bairro = et_logradouro.text.toString()
        this.endereco.localidade = et_logradouro.text.toString()
        this.endereco.uf = et_uf.text.toString()
        this.endereco.numero = et_numero.text.toString().toInt()
        this.endereco.complemento = et_numero.text.toString()


        this.vendedor.nome = intent.getStringExtra("nome")
        this.vendedor.email = intent.getStringExtra("email")
        this.vendedor.cnpj = intent.getStringExtra("cnpj")
        this.vendedor.senha = intent.getStringExtra("senha")
        this.vendedor.telefone = intent.getStringExtra("telefone")

        this.loja.nome = intent.getStringExtra("nomeLoja")
        this.loja.descricao = intent.getStringExtra("descricao")
        this.loja.vendedor = this.vendedor
        this.loja.endereco = this.endereco


        val call = ApiAccessUtils().lojaService().createLoja(this.vendedor.cnpj, this.loja)
        call.enqueue(object : Callback<Loja> {
            override fun onResponse(call: Call<Loja>, response: Response<Loja>) {
                Toast.makeText(baseContext,
                    getString(R.string.txt_cadastrado),
                    Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<Loja>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
            }
        })

        startActivity(Intent(this, Login::class.java))
    }



}