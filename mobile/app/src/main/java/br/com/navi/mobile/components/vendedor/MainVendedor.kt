package br.com.navi.mobile.components.vendedor

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_comprador_main.icon_perfil
import kotlinx.android.synthetic.main.activity_vendedor_frag_entregadores.*
import kotlinx.android.synthetic.main.activity_vendedor_frag_pedidos.*

class MainVendedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedor_main)

        grupoDeFragments()
        carregandoGif()

        icon_perfil.setOnClickListener {
            startActivity(Intent(this, PerfilVendedor::class.java))
        }
    }

    private fun grupoDeFragments(){
        var viewPager: ViewPager2 = findViewById(R.id.viewpager)
        var adapter = VendedorStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        var names:ArrayList<String> = arrayListOf("Pedidos","Entregadores")
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = names[position]
        }.attach()
    }

    fun carregandoGif() {
        val imageView: ImageView = findViewById(R.id.navi_logo_pequena)
        Glide.with(this).load(R.drawable.navi_logo_white).into(imageView)
    }

    fun showFormPedido(component: View) {
        if (rl_form_pedido.visibility == View.GONE){
            rl_criar_pedido.background = null
            bt_criar_pedido.setBackgroundResource(R.drawable.button_border_red)
            bt_criar_pedido.setTextColor(getColor(R.color.Branco))
            bt_criar_pedido.text = getString(R.string.str_bt_cancelar)
            rl_form_pedido.visibility = View.VISIBLE
            sv_pedidos.visibility = View.GONE
        } else {
            rl_criar_pedido.setBackgroundColor(getColor(R.color.AzulBg))
            bt_criar_pedido.setBackgroundResource(R.drawable.button_border_outline)
            bt_criar_pedido.setTextColor(getColor(R.color.AzulNavi))
            bt_criar_pedido.text = getString(R.string.str_bt_criar_pedido)
            rl_form_pedido.visibility = View.GONE
            sv_pedidos.visibility = View.VISIBLE
        }
    }

    fun showFormEntregador(component: View) {
        if (rl_form_entregador.visibility == View.GONE){
            rl_criar_entregador.background = null
            bt_criar_entregador.setBackgroundResource(R.drawable.button_border_red)
            bt_criar_entregador.setTextColor(getColor(R.color.Branco))
            bt_criar_entregador.text = getString(R.string.str_bt_cancelar)
            rl_form_entregador.visibility = View.VISIBLE
            sv_entregadores.visibility = View.GONE
        } else {
            rl_criar_entregador.setBackgroundColor(getColor(R.color.AzulBg))
            bt_criar_entregador.setBackgroundResource(R.drawable.button_border_outline)
            bt_criar_entregador.setTextColor(getColor(R.color.AzulNavi))
            bt_criar_entregador.text = getString(R.string.str_bt_criar_pedido)
            rl_form_entregador.visibility = View.GONE
            sv_entregadores.visibility = View.VISIBLE
        }
    }
}