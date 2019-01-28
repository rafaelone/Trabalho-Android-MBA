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


        btnRegistroAtendimento.setOnClickListener{
        // Log.i("adsadasdasd",""+servicoSelecionado)
         //Log.i("adsadasdasd",""+funcionarioSelecionado)

            val db = BancoDeDados.getDatabase(this)
            val atendimento = Attendance( id_cliente.toString(), "", servicoSelecionado.toString(), funcionarioSelecionado.toString())
           //if(atendimento.data != "")
              InsertAsyncTask(db!!).execute(atendimento)

        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView()
            }
        }

        tvData.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@CadastroAtendimentoActivity,
                        dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()

            }

        })


    }

    private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados): AsyncTask<Attendance,
            Void, String>(){
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Attendance): String {
            Log.i("UESHUEHUHSUHES", ""+params[0])
            Log.i("UESHUEHUHSUHES", ""+params)
           db.atendimentoDAO().inserir(params[0])
            finish()
           return ""

        }


    }


    private fun updateDateInView() {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(cal.time)
        tvData.text = currentDate
    }

        fun carregarSpinnerFuncionario(){
        var str_funcionario = resources.getStringArray(R.array.str_funcionario)
        val view = this;
        spFuncionario = view.findViewById(R.id.SpFuncionario)
        var spinnerAdapter: AdapterServico = AdapterServico(this, str_funcionario)
        spFuncionario.adapter = spinnerAdapter

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

        spServico.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                servicoSelecionado = parent?.getItemAtPosition(position).toString()

            }

        }

    }

}
