package rafael.com.br.barshall.view.main.atendimento

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.atendimento_item.view.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Attendance


class AtendimentoListAdapter(val context: Context,
                             var atendimentos: List<Attendance>,
                             val listener: (Attendance) -> Unit,
                             val listenerDelete: (Attendance) -> Unit):RecyclerView.Adapter<AtendimentoListAdapter.AtendimentoViewHolder>(){

    override fun getItemCount(): Int {

        return atendimentos.size
    }

    fun setList(atds: List<Attendance>){

        this.atendimentos = atds

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtendimentoListAdapter.AtendimentoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.atendimento_item, parent, false);
        return AtendimentoViewHolder(v)
    }



    override fun onBindViewHolder(holder: AtendimentoListAdapter.AtendimentoViewHolder, position: Int) {
        val atendimento = atendimentos[position]
        holder?.let{
            holder.bindView(atendimento, listener, listenerDelete)
        }
    }

    class AtendimentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(atendimento: Attendance,
                     listener: (Attendance) -> Unit,
                     listenerDelete: (Attendance) -> Unit) = with(itemView) {
            tvServico.text = atendimento.servico
            tvFuncionario.text = atendimento.funcionario

            if (atendimento.servico == "Corte de cabelo") {
                ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cortecabelo))
            }

            //setOnClickListener{listener(atendimento)}
            setOnClickListener {
                listenerDelete(atendimento)
                listener(atendimento)
            }
        }

    }
}


