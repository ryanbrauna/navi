package br.com.navi.mobile.components.entregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.PedidoService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_entregador_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainEntregador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregador_main)

        grupoDeFragments()

        icon_perfil.setOnClickListener {
            startActivity(Intent(this, PerfilEntregador::class.java))
        }
    }

    private fun grupoDeFragments(){
        var viewPager: ViewPager2 = findViewById(R.id.viewpager)
        var adapter = EntregadorStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        var names:ArrayList<String> = arrayListOf(getString(R.string.nav_pedidos_loja))
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = names[position]
        }.attach()
    }

    fun tabPedidoRegistrado(component: View) {
        if (ll_pedido_registrado.visibility == View.GONE) {
            ll_pedido_registrado.visibility = View.VISIBLE
            icon_tab_pedido_registrado.setImageResource(R.drawable.ic_tab_open)
        } else {
            ll_pedido_registrado.visibility = View.GONE
            icon_tab_pedido_registrado.setImageResource(R.drawable.ic_tab_closed)
        }
    }
    fun tabPedidoEmAndamento(component: View) {
        if (ll_pedido_em_andamento.visibility == View.GONE) {
            ll_pedido_em_andamento.visibility = View.VISIBLE
            icon_tab_em_andamento.setImageResource(R.drawable.ic_tab_open)
        } else {
            ll_pedido_em_andamento.visibility = View.GONE
            icon_tab_em_andamento.setImageResource(R.drawable.ic_tab_closed)
        }
    }
    fun tabPedidoEntregue(component: View) {
        if (ll_pedido_entregue.visibility == View.GONE) {
            ll_pedido_entregue.visibility = View.VISIBLE
            icon_tab_entregue.setImageResource(R.drawable.ic_tab_open)
        } else {
            ll_pedido_entregue.visibility = View.GONE
            icon_tab_entregue.setImageResource(R.drawable.ic_tab_closed)
        }
    }
    fun tabPedidoCancelado(component: View) {
        if (ll_pedido_cancelado.visibility == View.GONE) {
            ll_pedido_cancelado.visibility = View.VISIBLE
            icon_tab_cancelado.setImageResource(R.drawable.ic_tab_open)
        } else {
            ll_pedido_cancelado.visibility = View.GONE
            icon_tab_cancelado.setImageResource(R.drawable.ic_tab_closed)
        }
    }

    fun showFormConfEntrega(component: View){
        if (form_conf_entrega.visibility == View.GONE){
            form_conf_entrega.visibility = View.VISIBLE
            sv_pedidos.visibility = View.GONE
            bt_conf_entrega.visibility = View.GONE
        } else {
            form_conf_entrega.visibility = View.GONE
            sv_pedidos.visibility = View.VISIBLE
            bt_conf_entrega.visibility = View.VISIBLE
        }
    }

    fun confEntrega(component: View){
        if(et_nr_pedido_confirmar_entrega.text.toString() == ""){
            Toast.makeText(baseContext, "Por favor, insira um numedo de pedido", Toast.LENGTH_SHORT).show()
        }else{
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://navi--api.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val requestsPedido = retrofit.create(PedidoService::class.java)
            val callPedido = requestsPedido.confirmarEntrega(codUser, et_nr_pedido_confirmar_entrega.text.toString())

            callPedido.enqueue(object : Callback<Pedido> {
                override fun onResponse(call: Call<Pedido>, response: Response<Pedido>) {
                    Toast.makeText(baseContext, "Entrega do pedido confirmada!", Toast.LENGTH_SHORT).show()
                    println("Entrega do pedido confirmada!")
                    restartActivity()
                }

                override fun onFailure(call: Call<Pedido>, t: Throwable) {
                    Toast.makeText(baseContext, "Numero do pedido incorreto.", Toast.LENGTH_SHORT).show()
                    println("Numero do pedido incorreto.")
                    restartActivity()
                }

            })
        }
    }

    fun restartActivity() {
        startActivity(Intent(this, MainEntregador::class.java))
    }
}