package com.example.prototipos3

import android.app.AlertDialog
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
            val nrPedido:TextView = itemView.findViewById(R.id.card_nr_pedido)
            val descPedido:TextView = itemView.findViewById(R.id.card_pedido_desc)
            val precoPedido:TextView = itemView.findViewById(R.id.card_pedido_preco)

            nrPedido.text = data.numeroDoPedido
            descPedido.text = data.descricao
            precoPedido.text = data.preco

            val icRegistrado:TextView = itemView.findViewById(R.id.ic_card_pedido_registrado)
            val icAndamento:TextView = itemView.findViewById(R.id.ic_card_pedido_andamento)

            if (data.estado == "Pedido Registrado"){
                icRegistrado.visibility = View.VISIBLE
            }
            if (data.estado == "Em Andamento"){
                icAndamento.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                val dialog = AlertDialog.Builder(it.context)
                val dialogView = View.inflate(it.context,R.layout.activity_dialog_pedidos,null)
                dialog.setView(dialogView)
                val customDialog = dialog.create()

                val nrPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_nr_pedido)
                val descPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_desc)
                val anotacaoPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_anotacao)
                val precoPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_preco)
                val estadoPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_estado)
                val enderecoPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_endereco)
                val entregadorPedidoDialog:TextView = dialogView.findViewById(R.id.dialog_pedido_entregador)

                nrPedidoDialog.text = data.numeroDoPedido
                descPedidoDialog.text = data.descricao
                anotacaoPedidoDialog.text = data.anotacoes
                precoPedidoDialog.text = data.preco
                estadoPedidoDialog.text = data.estado
                enderecoPedidoDialog.text = "${data.comprador?.endereco?.logradouro}, " +
                        "${data.comprador?.endereco?.numero}, " +
                        "${data.comprador?.endereco?.bairro}, " +
                        "${data.comprador?.endereco?.localidade} - " +
                        "${data.comprador?.endereco?.uf}"
                entregadorPedidoDialog.text = data.entregador?.nome

                customDialog.show()
            }
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