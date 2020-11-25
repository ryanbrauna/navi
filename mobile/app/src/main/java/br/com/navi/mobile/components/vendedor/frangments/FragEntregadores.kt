package br.com.navi.mobile.components.comprador.frangments

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Entregador
import br.com.navi.mobile.services.EntregadorService
import kotlinx.android.synthetic.main.activity_vendedor_frag_entregadores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragEntregadores():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_vendedor_frag_entregadores,container,false)
        getEntregadores()
        return view
    }

    fun getEntregadores() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val requestEntregador = retrofit.create(EntregadorService::class.java)
        val callEntregadorService = requestEntregador.getEntregadoresLoja("12345678901234")

        callEntregadorService.enqueue(object : Callback<List<Entregador>> {
            override fun onResponse(call: Call<List<Entregador>>, response: Response<List<Entregador>>) {
                response.body()?.forEach {
                    val newTv = TextView(context)

                    newTv.text = "Entregador: ${it.nome}\n" +
                            "E-mail: ${it.email}\n" +
                            "CPF: ${it.cpf}\n"
                    newTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    newTv.setTextColor(Color.parseColor("#2196F3"))
                    newTv.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    content_entregadores.addView(newTv)
                }
            }

            override fun onFailure(call: Call<List<Entregador>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}