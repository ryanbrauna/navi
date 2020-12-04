package br.com.navi.mobile.components.entregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_entregador_main.*

class MainEntregador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregador_main)

        grupoDeFragments()
        //carregandoGif()

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

    fun carregandoGif() {
        val imageView: ImageView = findViewById(R.id.navi_logo_pequena)
        Glide.with(this).load(R.drawable.navi_logo_white).into(imageView)
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
}