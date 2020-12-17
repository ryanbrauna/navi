package br.com.navi.mobile.components.comprador.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.components.maps.MapsNavigationActivity
import br.com.navi.mobile.models.Loja
import br.com.navi.mobile.services.LojaService
import com.example.prototipos3.AdapterLoja
import kotlinx.android.synthetic.main.activity_comprador_frag_lojas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragLojas:Fragment() {
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewOfLayout = inflater.inflate(R.layout.activity_comprador_frag_lojas,container,false)

        val recyclerLojasView: RecyclerView = viewOfLayout.findViewById(R.id.recyclerLojas)
        recyclerLojasView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://navi--api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val requestLojas = retrofit.create(LojaService::class.java)
        val callLojaService = requestLojas.getLojas()
        callLojaService.enqueue(object : Callback<List<Loja>> {
            override fun onResponse(call: Call<List<Loja>>, response: Response<List<Loja>>) {
                val listLojas = ArrayList<Loja>()
                val lojas = response.body()
                lojas?.sortedBy { it.nome }?.forEach { listLojas.add(it) }
                recyclerLojasView.adapter = AdapterLoja(listLojas)
            }

            override fun onFailure(call: Call<List<Loja>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return viewOfLayout
    }

}