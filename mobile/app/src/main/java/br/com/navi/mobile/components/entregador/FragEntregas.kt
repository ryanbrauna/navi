package br.com.navi.mobile.components.Entregador.frangments

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
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.EntregadorService
import br.com.navi.mobile.services.PedidoService
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_vendedor_frag_entregadores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragEntregas():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_entregador_frag_pedidos,container,false)
        getPedidosEntregador()
        return view
    }

    fun getPedidosEntregador() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val requestsPedido = retrofit.create(PedidoService::class.java)
        val callPedidosEntregador = requestsPedido.getPedidosLoja("12345678901234")

        callPedidosEntregador.enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                response.body()?.forEach {
                    val newTv = TextView(context)

                    newTv.text = "Nº do Pedido: ${it.numeroDoPedido} \n" +
                            "Descrição: ${it.descricao} \n" +
                            "Preço: ${it.preco}\n"
                    newTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                    newTv.setTextColor(Color.parseColor("#2196F3"))
                    newTv.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    content_pedidos.addView(newTv)
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}