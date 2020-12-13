package br.com.navi.mobile.components.comprador.frangments

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.PedidoService
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.ll_pedido_cancelado
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.ll_pedido_em_andamento
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.ll_pedido_entregue
import kotlinx.android.synthetic.main.activity_comprador_frag_pedidos.ll_pedido_registrado
import kotlinx.android.synthetic.main.activity_vendedor_frag_pedidos.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class FragPedidosComprador():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_comprador_frag_pedidos,container,false)
        return view
    }
}