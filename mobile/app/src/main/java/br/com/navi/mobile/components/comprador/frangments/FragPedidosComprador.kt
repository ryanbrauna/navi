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
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.PedidoService
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class FragPedidosComprador():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_comprador_frag_pedidos,container,false)
        getPedidosComprador()
        return view
    }

    fun getPedidosComprador() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val requestsPedido = retrofit.create(PedidoService::class.java)
        val callPedidosComprador = requestsPedido.getPedidosComprador(codUser)

        callPedidosComprador.enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                response.body()?.forEach {
                    val newTv = TextView(context)

                    newTv.text = "Nº do Pedido: ${it.numeroDoPedido} \n" +
                            "Descrição: ${it.descricao} \n" +
                            "Preço: ${it.preco}\n"
                    newTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    newTv.setTextColor(Color.parseColor("#2196F3"))
                    newTv.setBackgroundResource(R.drawable.edit_text_border)

                    content_pedidos.addView(newTv)
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}