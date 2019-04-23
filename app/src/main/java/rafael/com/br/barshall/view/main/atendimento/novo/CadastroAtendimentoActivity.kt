package rafael.com.br.barshall.view.main.atendimento.novo

import android.app.DatePickerDialog
import android.app.Service
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_cadastro_atendimento.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.dao.BancoDeDados
import rafael.com.br.barshall.model.Attendance
import rafael.com.br.barshall.utils.MaskEditUtil
import rafael.com.br.barshall.view.main.atendimento.novo.spinner.AdapterFuncionario
import rafael.com.br.barshall.view.main.atendimento.novo.spinner.AdapterServico
import java.text.SimpleDateFormat
import java.util.*

class CadastroAtendimentoActivity : AppCompatActivity() {

    private lateinit var spServico: Spinner
    private lateinit var spFuncionario: Spinner
    private lateinit var servicoSelecionado: String
    private lateinit var funcionarioSelecionado: String
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atendimento)
        carregarSpinnerFuncionario()
        carregarSpinnerServico()


        val sharedPreferences = this.getSharedPreferences("myapp", Context.MODE_PRIVATE)
        val id_cliente = sharedPreferences.getString("id", "")

        tvData.addTextChangedListener(MaskEditUtil.mask(tvData, MaskEditUtil.FORMAT_DATE))
        val atendimentoAtualizar = intent.getParcelableExtra<Attendance>("ATUALIZAR")
        if(atendimentoAtualizar == null){
            btnRegistroAtendimento.setOnClickListener{
                val db = BancoDeDados.getDatabase(this)
                if (tvData.text.toString().length < 10){
                    Toast.makeText(this, "Please, tell me a date", Toast.LENGTH_SHORT).show()
                }
                val atendimento = Attendance(0,id_cliente.toString(), tvData.text.toString(), servicoSelecionado.toString(), funcionarioSelecionado.toString() )
                if(atendimento.data != "")
                InsertAsyncTask(db!!).execute(atendimento)

            }
        } else {
            txNovo.text = "Update attendance"
            btnRegistroAtendimento.text = "Update"
            val atendimentoAtualizar = intent.getParcelableExtra<Attendance>("ATUALIZAR")
            tvData.setText(atendimentoAtualizar.data.toString())
            btnRegistroAtendimento.setOnClickListener {
                val db = BancoDeDados.getDatabase(this)
                val atendimento = Attendance(atendimentoAtualizar.id, id_cliente.toString(), tvData.text.toString(), servicoSelecionado, funcionarioSelecionado)
                UpdateAsyncTask(db!!).execute(atendimento)
            }

        }



    }

    private inner class UpdateAsyncTask internal constructor(appDatabase: BancoDeDados): AsyncTask<Attendance, Void,
            String>(){
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Attendance?): String {
            var id = params[0]?.id
           var funcionario = params[0]?.funcionario
            var servico = params[0]?.servico
            var data = params[0]?.data
            var id_cliente = params[0]?.id_cliente
            db.atendimentoDAO().atualizar(id.toString().toInt(), id_cliente.toString(), servico.toString(), funcionario.toString(), data.toString())
            finish()
            return ""
        }
    }


        private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados): AsyncTask<Attendance,
            Void, String>(){
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Attendance): String {
            db.atendimentoDAO().inserir(params[0])
            finish()
           return ""
        }
    }


    fun carregarSpinnerFuncionario(){
        var str_funcionario = resources.getStringArray(R.array.str_funcionario)
        val view = this;
        spFuncionario = view.findViewById(R.id.SpFuncionario)
        var spinnerAdapter: AdapterFuncionario = AdapterFuncionario(this, str_funcionario)
        spFuncionario.adapter = spinnerAdapter
            val atendimentoAtualizar = intent.getParcelableExtra<Attendance>("ATUALIZAR")

            if(atendimentoAtualizar != null){
                var funcionarioIntent = spinnerAdapter.getFuncionarioByName(atendimentoAtualizar.funcionario.toString())
                spFuncionario.setSelection(funcionarioIntent.toString().toInt())

            }

        spFuncionario.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                funcionarioSelecionado = parent?.getItemAtPosition(position).toString()

            }

        }
    }

    fun carregarSpinnerServico(){
        var str_servico = resources.getStringArray(R.array.str_servico)
        val view = this;
        spServico = view.findViewById(R.id.SpService)
        var spinnerAdapter: AdapterServico = AdapterServico(this, str_servico)
        spServico.adapter = spinnerAdapter

        val atendimentoAtualizar = intent.getParcelableExtra<Attendance>("ATUALIZAR")

        if(atendimentoAtualizar != null){
            var servicoIntent = spinnerAdapter.getServicoNome(atendimentoAtualizar.servico.toString())
            spServico.setSelection(servicoIntent.toString().toInt())

        }

        spServico.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                servicoSelecionado = parent?.getItemAtPosition(position).toString()

            }

        }

    }

}
