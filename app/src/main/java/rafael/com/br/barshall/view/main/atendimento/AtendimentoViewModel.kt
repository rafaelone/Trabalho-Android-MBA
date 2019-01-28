package rafael.com.br.barshall.view.main.atendimento

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import rafael.com.br.barshall.MyApplication
import rafael.com.br.barshall.dao.BancoDeDados
import rafael.com.br.barshall.model.Attendance
import rafael.com.br.barshall.model.ResponseStatus

class AtendimentoViewModel(application: Application): AndroidViewModel(application){

    lateinit var atendimentos: LiveData<List<Attendance>>

    private val bd:BancoDeDados = BancoDeDados.getDatabase(application.applicationContext)!!

    //val sharedPreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE)


    val sharedPreferences = application.applicationContext.getSharedPreferences("myapp", Context.MODE_PRIVATE)
    val id_cliente = sharedPreferences.getString("id", "")

    init{
        listarAtendimento()

    }

    private fun listarAtendimento(){
        atendimentos = bd.atendimentoDAO().listarAtendimentos()

    }
}