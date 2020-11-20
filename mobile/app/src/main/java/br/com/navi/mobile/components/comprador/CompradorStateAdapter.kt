package br.com.navi.mobile.components.comprador

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.navi.mobile.components.comprador.frangments.FragLojas
import br.com.navi.mobile.components.comprador.frangments.FragMaps
import br.com.navi.mobile.components.comprador.frangments.FragPedidos

class CompradorStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    var fragment:ArrayList<Fragment> = arrayListOf(
        FragLojas(),
        FragMaps(),
        FragPedidos()
    )

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}