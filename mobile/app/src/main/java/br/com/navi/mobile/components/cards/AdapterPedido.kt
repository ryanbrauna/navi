package com.example.prototipos3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Pedido

class AdapterPedido(var list: ArrayList<Pedido>):RecyclerView.Adapter<AdapterPedido.ViewHolder>() {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bindItems(data:Pedido){
            var nrPedido:TextView = itemView.findViewById(R.id.card_nr_pedido)
            var descPedido:TextView = itemView.findViewById(R.id.card_pedido_desc)
            var precoPedido:TextView = itemView.findViewById(R.id.card_pedido_preco)

            nrPedido.text = "Nº do Pedido: ${data.numeroDoPedido}"
            descPedido.text = "Descrição: ${data.descricao}"
            precoPedido.text = "Preço: ${data.preco}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.activity_card_pedidos,parent,false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterPedido.ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }
}