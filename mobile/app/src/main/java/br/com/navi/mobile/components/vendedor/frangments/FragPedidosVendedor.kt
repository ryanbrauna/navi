package br.com.navi.mobile.components.comprador.frangments

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import br.com.navi.mobile.R
import br.com.navi.mobile.components.login.codUser
import br.com.navi.mobile.models.Pedido
import br.com.navi.mobile.services.PedidoService
import kotlinx.android.synthetic.main.activity_vendedor_frag_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragPedidosVendedor():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_vendedor_frag_pedidos,container,false)
        return view
    }
}