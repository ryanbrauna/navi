package br.com.navi.mobile.components.comprador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.navi.mobile.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainComprador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprador_main)

        grupoDeFragments()
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
}