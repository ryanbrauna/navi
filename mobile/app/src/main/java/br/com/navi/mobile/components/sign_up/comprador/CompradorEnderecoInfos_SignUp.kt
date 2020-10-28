package br.com.navi.mobile.components.sign_up.comprador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.navi.mobile.R
import br.com.navi.mobile.components.SelectUserType_SignUp
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.models.Comprador
import br.com.navi.mobile.models.others.Cep
import br.com.navi.mobile.utils.ApiAccessUtils
import kotlinx.android.synthetic.main.activity_endereco_infos_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompradorEnderecoInfos_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endereco_infos_sign_up)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, CompradorOtherInfos_SignUp::class.java))
    }

    private var comprador : Comprador = Comprador(null, "", "", "", "", "", null)

    fun backToOtherInfos(component : View) {
        val previousScreen = Intent(this, CompradorOtherInfos_SignUp::class.java)
        startActivity(previousScreen)
    }

    fun searchCep(component: View) {
        val cepNumber : String = et_cep.text.toString()
//        var cep : Cep = Cep("", "",  "", "", "")

        val call = ApiAccessUtils().enderecoService().searchCep(cepNumber)
        call.enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>,
                                    response: Response<Cep>) {
                if (response.isSuccessful && response.code() == 200) {
                    val cep : Cep? = response.body()

//                    et_cep.text =
                }
            }

            override fun onFailure(call: Call<Cep>,
                                   t: Throwable) {
                Log.e("onFailure error", t?.message)
            }

        }
        )

    }

    fun addEnderecoInfos(component: View) {
        val compradorOtherInfosActivity = Intent(this, CompradorOtherInfos_SignUp::class.java)

        this.comprador.nome = intent.getStringExtra("nome")
        this.comprador.email = intent.getStringExtra("email")
        this.comprador.cpf = intent.getStringExtra("cpf")
        this.comprador.senha = intent.getStringExtra("senha")
        this.comprador.telefone = intent.getStringExtra("telefone")

        startActivity(Intent(this, Login::class.java))
    }



}