package br.com.navi.mobile.components.comprador

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.CompradorService
import br.com.navi.mobile.services.PedidoService
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_comprador_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainComprador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprador_main)

        grupoDeFragments()
        carregandoGif()

        icon_perfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
        }
    }

    private fun grupoDeFragments(){
        var viewPager: ViewPager2 = findViewById(R.id.viewpager)
        var adapter = CompradorStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        var names:ArrayList<String> = arrayListOf("Lojas","Maps","Meus Pedidos")
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = names[position]
        }.attach()
    }

    fun carregandoGif() {
        val imageView: ImageView = findViewById(R.id.navi_logo_pequena)
        Glide.with(this).load(R.drawable.navi_logo_white).into(imageView)
    }
}