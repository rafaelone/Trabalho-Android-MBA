package rafael.com.br.barshall.view.main.atendimento


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.content_lista.*
import kotlinx.android.synthetic.main.fragment_lista_atendimento.*
import kotlinx.android.synthetic.main.fragment_lista_atendimento.view.*

import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Attendance

import rafael.com.br.barshall.view.main.atendimento.detalhe.DetalheActivity
import rafael.com.br.barshall.view.main.atendimento.novo.CadastroAtendimentoActivity
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
        mostrarAtendimentos()
        return view


    }

    private fun mostrarAtendimentos(){
         ViewModelProviders.of(this)
                 .get(AtendimentoViewModel::class.java)
                 .atendimentos
                 .observe(this, Observer <List<Attendance>>{
                  adapter?.setList(it!!)
                     Log.i("inicio", "DASDADASDADDSDADAD"+it)
                     adapter = AtendimentoListAdapter(context!!, atendimentos, {atendimento ->
                        // val detalheActivity = Intent(this, DetalheActivity::class.java)
                        // detalheActivity.putExtra("ATENDIMENTO", atendimento)
                        // startActivity(detalheActivity)

                     }, {})
                     rvAtendimentos.adapter = adapter
                     rvAtendimentos.layoutManager = LinearLayoutManager(context)
                     rvAtendimentos.adapter.notifyDataSetChanged()
                 })

    }

}
