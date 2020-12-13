package br.com.navi.mobile.components.comprador.frangments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Entregador
import br.com.navi.mobile.services.EntregadorService
import com.example.prototipos3.AdapterEntregador
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
        val viewOfLayout = inflater.inflate(R.layout.activity_vendedor_frag_entregadores,container,false)

        val recyclerEntregadoresView: RecyclerView = viewOfLayout.findViewById(R.id.recyclerEntregadores)
        recyclerEntregadoresView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val requestEntregador = retrofit.create(EntregadorService::class.java)
        val callEntregadorService = requestEntregador.getEntregadoresLoja(codUser)
        callEntregadorService.enqueue(object : Callback<List<Entregador>> {
            override fun onResponse(call: Call<List<Entregador>>, response: Response<List<Entregador>>) {
                val listEntregadores = ArrayList<Entregador>()
                val entregadores = response.body()
                entregadores?.sortedBy { it.nome }?.forEach { listEntregadores.add(it) }
                recyclerEntregadoresView.adapter = AdapterEntregador(listEntregadores)
            }

            override fun onFailure(call: Call<List<Entregador>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return viewOfLayout
    }
}