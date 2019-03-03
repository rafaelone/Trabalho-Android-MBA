package rafael.com.br.barshall.view.main.atendimento


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.atendimento_item.*
import kotlinx.android.synthetic.main.atendimento_item.view.*
import kotlinx.android.synthetic.main.content_lista.*
import kotlinx.android.synthetic.main.fragment_lista_atendimento.*
import kotlinx.android.synthetic.main.fragment_lista_atendimento.view.*

import rafael.com.br.barshall.R
import rafael.com.br.barshall.context.AppCtx
import rafael.com.br.barshall.dao.BancoDeDados
import rafael.com.br.barshall.model.Attendance

import rafael.com.br.barshall.view.main.atendimento.detalhe.DetalheActivity
import rafael.com.br.barshall.view.main.atendimento.novo.CadastroAtendimentoActivity
import java.io.Serializable
import java.lang.Float.parseFloat


class ListaAtendimentoFragment : Fragment() {

    lateinit var atendimentoViewModel : AtendimentoViewModel
    private var adapter: AtendimentoListAdapter? = null
    private var atendimentos : List<Attendance> = listOf()

    val FORMULARIO_REQUEST_CODE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_lista_atendimento, container, false)
        view.fab.setOnClickListener { view ->
            val cadastroAtendimento = Intent(context, CadastroAtendimentoActivity::class.java)
            startActivityForResult(cadastroAtendimento, FORMULARIO_REQUEST_CODE)
        }
        atendimentoViewModel = ViewModelProviders.of(this).get(AtendimentoViewModel::class.java)
        atendimentoViewModel.atendimentos.observe(this, atendimentoObserver)

        return view


    }


    private var atendimentoObserver = Observer<List<Attendance>>{

        preencherALista(it!!)
    }

    private fun preencherALista(atendimentos: List<Attendance>){

    Log.i("ATENDIMENTO", ""+atendimentos)
        adapter = AtendimentoListAdapter(context!!, atendimentos, {atendimento ->
             val detalheActivity = Intent(context, DetalheActivity::class.java)
           detalheActivity.putExtra("ATENDIMENTO", atendimento)
             startActivity(detalheActivity)
        },{deletar ->
          btnDeletarAtendimento.setOnClickListener {
              val db = BancoDeDados.getDatabase(context!!)
              DeleteAsyncTask(db!!).execute(deletar)
          }
        })
        rvAtendimentos.adapter = adapter
        rvAtendimentos.layoutManager = LinearLayoutManager(context)
        rvAtendimentos.adapter.notifyDataSetChanged()

    }


    private inner class DeleteAsyncTask internal constructor(appDatabase: BancoDeDados): AsyncTask<Attendance, Void,
            String>(){
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Attendance?): String {
            var id = params[0]?.id
            db.atendimentoDAO().apagar(id.toString().toInt())
            return ""
        }
    }

}
