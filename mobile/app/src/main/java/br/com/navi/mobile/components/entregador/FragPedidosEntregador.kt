package br.com.navi.mobile.components.Entregador.frangments

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.PedidoService
import com.example.prototipos3.AdapterPedido
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.*
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.ll_pedido_cancelado
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.ll_pedido_em_andamento
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.ll_pedido_entregue
import kotlinx.android.synthetic.main.activity_entregador_frag_pedidos.ll_pedido_registrado
import kotlinx.android.synthetic.main.activity_vendedor_frag_entregadores.*
import kotlinx.android.synthetic.main.activity_vendedor_frag_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragPedidosEntregador():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_entregador_frag_pedidos,container,false)
        return view
    }
}