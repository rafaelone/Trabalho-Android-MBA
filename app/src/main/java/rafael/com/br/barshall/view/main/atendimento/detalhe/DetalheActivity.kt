package rafael.com.br.barshall.view.main.atendimento.detalhe

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhe.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Attendance
import rafael.com.br.barshall.view.main.atendimento.AtendimentoViewModel

class DetalheActivity : AppCompatActivity() {

    lateinit var atendimentoViewModel : AtendimentoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)


        atendimentoViewModel = ViewModelProviders.of(this).get(AtendimentoViewModel::class.java)
        //atendimentoViewModel.responseStatus.observe(this, responseStatusObserver)


        btnAtualizar.setOnClickListener{
            //var atualizarIntent = Intent(this, CadastroAtendimentoActivity::class.java)
          //  atualizarIntent.putExtra("ATUALIZAR", atendimento)
            //startActivity(atualizarIntent)
           // finish()
        }


    }
}