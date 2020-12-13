package com.example.prototipos3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Entregador
import br.com.navi.mobile.models.Loja

class AdapterEntregador(var list: ArrayList<Entregador>):RecyclerView.Adapter<AdapterEntregador.ViewHolder>() {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bindItems(data:Entregador){
            var nomeEntregador:TextView = itemView.findViewById(R.id.card_entregador_nome)
            var emailEntregador:TextView = itemView.findViewById(R.id.card_entregador_email)
            var cpfEntregador:TextView = itemView.findViewById(R.id.card_entregador_cpf)

            nomeEntregador.text = data.nome
            emailEntregador.text = "E-mail: ${data.email}"
            cpfEntregador.text = "CPF: ${data.cpf}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.activity_card_entregadores,parent,false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterEntregador.ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }
}