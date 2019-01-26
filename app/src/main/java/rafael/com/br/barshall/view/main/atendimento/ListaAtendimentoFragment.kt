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
import java.lang.Float.parseFloat


class ListaAtendimentoFragment : Fragment() {

    lateinit var atendimentoViewModel : AtendimentoViewModel
    private var adapter: AtendimentoListAdapter? = null

    val FORMULARIO_REQUEST_CODE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_lista_atendimento, container, false)

        atendimentoViewModel = ViewModelProviders.of(this).get(AtendimentoViewModel::class.java)


        list()

        return view
    }

    fun list(){

    }

    private var atendimentoObserver = Observer<List<Attendance>>{

    }

    private fun preencherALista(atendimentos: List<Attendance>){

    }








}
