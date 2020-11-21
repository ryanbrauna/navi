package br.com.navi.mobile.components.entregador

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.Login
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_comprador_main.icon_perfil

class MainEntregador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregador_main)

        grupoDeFragments()
        carregandoGif()

        icon_perfil.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            Toast.makeText(baseContext, getString(R.string.txt_exit), Toast.LENGTH_SHORT).show()
        }
    }

    private fun grupoDeFragments(){
        var viewPager: ViewPager2 = findViewById(R.id.viewpager)
        var adapter = EntregadorStateAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        var names:ArrayList<String> = arrayListOf("Pedidos")
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = names[position]
        }.attach()
    }

    fun carregandoGif() {
        val imageView: ImageView = findViewById(R.id.navi_logo_pequena)
        Glide.with(this).load(R.drawable.navi_logo_white).into(imageView)
    }
}