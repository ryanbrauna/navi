package br.com.navi.mobile.components.sign_up.vendedor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.SelectUserType_SignUp
import br.com.navi.mobile.components.sign_up.comprador.CompradorOtherInfos_SignUp
import br.com.navi.mobile.models.Vendedor
import kotlinx.android.synthetic.main.activity_vendedor_infos_sign_up.*

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

    private var vendedor : Vendedor = Vendedor(null, "", "", "", "", "")


    fun addCompradorInfos(component: View) {
        val vendedorOtherInfosActivity = Intent(this, VendedorOtherInfos_SignUp::class.java)

        this.vendedor.nome = et_vendedorNome.text.toString()
        this.vendedor.email = et_vendedorEmail.text.toString()
        this.vendedor.cnpj = et_vendedorCnpj.text.toString()

//        val call = ApiAccessUtils().vendedorService().getVendedor(this.vendedor.cnpj)
//        call.enqueue(object : Callback<Vendedor> {
//            override fun onResponse(call: Call<Vendedor>,
//                                    response: Response<Vendedor>
//            ) {
//                if (response.isSuccessful && response.code() == 200) {
//                    Toast.makeText(applicationContext, "Esse CNPJ já foi cadastrado!", Toast.LENGTH_SHORT).show()
//                    // Bloquear botão "Proximo"
//                }
//                else if (response.isSuccessful && response.code() == 404) {
//                    // Não sei o que fazer aqui :P
//
//                }
//            }
//
//            override fun onFailure(call: Call<Vendedor?>?,
//                                   t: Throwable?) {
//                Log.e("onFailure error", t?.message)
//            }
//        })


        vendedorOtherInfosActivity.putExtra("nome", this.vendedor.nome)
        vendedorOtherInfosActivity.putExtra("email", this.vendedor.email)
        vendedorOtherInfosActivity.putExtra("senha", this.vendedor.senha)
        vendedorOtherInfosActivity.putExtra("telefone", this.vendedor.telefone)
        vendedorOtherInfosActivity.putExtra("cnpj", this.vendedor.cnpj)

        startActivity(Intent(this, CompradorOtherInfos_SignUp::class.java))
    }

}