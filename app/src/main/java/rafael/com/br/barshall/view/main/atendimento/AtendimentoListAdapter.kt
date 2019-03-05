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
        val CORTE_CABELO = "Corte de cabelo - R$ 20,00"
        val MANICURE = "Manicure - R$ 25,00"
        val BARBA = "Barba - R$ 10,00"
        val PE_DE_CURE = "Pé de cure - R$ 25,00"
        val LUZES = "Luzes - R$ 20,00"
        fun bindView(atendimento: Attendance,
                     listener: (Attendance) -> Unit,
                     listenerDelete: (Attendance) -> Unit) = with(itemView) {
            tvServico.text = atendimento.servico
            tvFuncionario.text = atendimento.funcionario
            Log.i("TIPO_SERVICO", "aaa "+atendimento.servico)
            when(atendimento.servico){
                CORTE_CABELO ->{
                    ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cortecabelo))
                }
                MANICURE -> {
                    ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.manicure))
                }
                BARBA -> {
                    ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.barba))
                }
                PE_DE_CURE -> {
                    ivServ.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pedecure))
                }
            }
            setOnClickListener {
                listenerDelete(atendimento)
                listener(atendimento)
            }
        }

    }
}


