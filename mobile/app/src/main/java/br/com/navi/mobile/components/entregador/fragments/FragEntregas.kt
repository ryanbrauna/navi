package br.com.navi.mobile.components.comprador.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.cnhUser
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Entregador
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.EntregadorService
import br.com.navi.mobile.services.PedidoService
import com.example.prototipos3.AdapterEntregador
import com.example.prototipos3.AdapterPedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragEntregas():Fragment() {
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewOfLayout = inflater.inflate(R.layout.activity_entregador_frag_entregas,container,false)

        val recyclerEntregasView: RecyclerView = viewOfLayout.findViewById(R.id.recyclerEntregas)
        recyclerEntregasView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val requestEntregas = retrofit.create(PedidoService::class.java)
        val callEntregasService = requestEntregas.getPedidosLoja(codUser)
        callEntregasService.enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                val listEntregadores = ArrayList<Pedido>()

                response.body()?.forEach {
                    if (it.entregador?.cnh == cnhUser &&
                        (it.estado == "Pedido Registrado" || it.estado == "Em Andamento"))
                    { listEntregadores.add(it) }
                }

                recyclerEntregasView.adapter = AdapterPedido(listEntregadores)
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return viewOfLayout
    }
}