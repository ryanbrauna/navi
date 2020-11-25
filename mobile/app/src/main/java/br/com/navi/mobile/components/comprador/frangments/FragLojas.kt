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
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.services.LojaService
import kotlinx.android.synthetic.main.activity_comprador_frag_lojas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragLojas():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_comprador_frag_lojas,container,false)
        getLojas()
        return view
    }

    fun getLojas() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val requestLojas = retrofit.create(LojaService::class.java)
        val callLojaService = requestLojas.getLojas()

        callLojaService.enqueue(object : Callback<List<Loja>> {
            override fun onResponse(call: Call<List<Loja>>, response: Response<List<Loja>>) {
                response.body()?.forEach {
                    val newTv = TextView(context)

                    newTv.text = "Loja: ${it.nome}\n" +
                            "Descrição: ${it.descricao}\n" +
                            "Vendedor: ${it.vendedor?.nome}\n"
                    newTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    newTv.setTextColor(Color.parseColor("#2196F3"))
                    newTv.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    content_lojas.addView(newTv)
                }
            }

            override fun onFailure(call: Call<List<Loja>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}