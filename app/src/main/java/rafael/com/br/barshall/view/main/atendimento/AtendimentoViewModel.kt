package rafael.com.br.barshall.view.main.atendimento

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import rafael.com.br.barshall.context.AppCtx
import rafael.com.br.barshall.dao.BancoDeDados
import rafael.com.br.barshall.model.Attendance


class AtendimentoViewModel(application: Application): AndroidViewModel(application){

    lateinit var atendimentos: LiveData<List<Attendance>>

    private val bd:BancoDeDados = BancoDeDados.getDatabase(AppCtx.getInstance().ctx!!)!!

    //val sharedPreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE)


    //val sharedPreferences = application.applicationContext.getSharedPreferences("myapp", Context.MODE_PRIVATE)
    val sharedPreferences = AppCtx.getInstance().ctx!!.getSharedPreferences("myapp", Context.MODE_PRIVATE)
    val id_cliente = sharedPreferences.getString("id", "")


    init{
        listarAtendimento()

    }

    private fun listarAtendimento(){

        atendimentos = bd.atendimentoDAO().listarAtendimentos(id_cliente.toString().toInt())


    }
}