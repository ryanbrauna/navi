package com.example.prototipos3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.navi.mobile.R
import br.com.navi.mobile.models.Loja

class AdapterLoja(var list: ArrayList<Loja>):RecyclerView.Adapter<AdapterLoja.ViewHolder>() {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bindItems(data:Loja){
            var nomeLoja:TextView = itemView.findViewById(R.id.card_loja_nome)
            var descLoja:TextView = itemView.findViewById(R.id.card_loja_desc)
            var enderecoLoja:TextView = itemView.findViewById(R.id.card_loja_endereco)

            nomeLoja.text = data.nome
            descLoja.text = data.descricao
            enderecoLoja.text = "${data.endereco?.logradouro}, " +
                    "${data.endereco?.numero}, " +
                    "${data.endereco?.bairro}, " +
                    "${data.endereco?.localidade} - " +
                    "${data.endereco?.uf}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.activity_card_lojas,parent,false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterLoja.ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }
}