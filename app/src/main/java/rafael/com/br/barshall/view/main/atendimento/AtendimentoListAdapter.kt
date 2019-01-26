package rafael.com.br.barshall.view.main.atendimento

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.atendimento_item.view.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Attendance

class AtendimentoListAdapter(
        val context: Context,
        val atendimentos: List<Attendance>,
        val listener: (Attendance) -> Unit,
        val listenerDelete: (Attendance) -> Unit
    ) : RecyclerView.Adapter<AtendimentoListAdapter.AtendimentoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtendimentoViewHolder{
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.atendimento_item, parent, false)
        return AtendimentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AtendimentoViewHolder, position: Int) {
        val atendimento = atendimentos[position]
        holder?.let {
            holder.bindView(atendimento, listener, listenerDelete)
        }


    }

    override fun getItemCount(): Int {
        return atendimentos.size
    }

    class AtendimentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(atendimento: Attendance,
                     listener: (Attendance) -> Unit,
                     listenerDelete: (Attendance) -> Unit) = with(itemView){
            tvServico.text = atendimento.servico
            tvPreco.text = atendimento.preco
            tvFuncionario.text = atendimento.funcionario

            if(atendimento.servico == "Corte de cabelo"){
                ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cortecabelo))
            }

            //setOnClickListener{listener(atendimento)}
            setOnClickListener {
                listener(atendimento)
            }
        }
    }


    /*private fun setImage(resourceId: Int){


        //ivServ.setImageDrawable(ContextCompat.getDrawable(this, resourceId))

    }*/

}
