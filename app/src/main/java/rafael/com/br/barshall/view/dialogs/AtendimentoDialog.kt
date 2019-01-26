package rafael.com.br.barshall.view.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.atendimento_dialog.*
import kotlinx.android.synthetic.main.atendimento_dialog.view.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Employee
import rafael.com.br.barshall.model.ResponseStatus
import rafael.com.br.barshall.model.Service
import rafael.com.br.barshall.view.dialogs.adapter.SpinnerAdapter
import rafael.com.br.barshall.view.dialogs.adapter.SpinnerFuncionarioAdapter
import rafael.com.br.barshall.view.main.atendimento.AtendimentoViewModel

class AtendimentoDialog: DialogFragment() {


    private lateinit var builder: AlertDialog.Builder
    private lateinit var spService: Spinner
    private lateinit var spEmployee: Spinner
    lateinit var atendimentoViewModel: AtendimentoViewModel


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        atendimentoViewModel = ViewModelProviders.of(this).get(AtendimentoViewModel::class.java)

        atendimentoViewModel.servicos.observe(this, servicoObserver);
        atendimentoViewModel.funcionarios.observe(this, funcionarioObserver)

        atendimentoViewModel.responseStatus.observe(this, responseStatusObserver)


        val sharedPreferences = this.getActivity()?.getSharedPreferences("myapp", Context.MODE_PRIVATE)
        val id = sharedPreferences?.getString("id", "")

        builder = AlertDialog.Builder(activity)
        val v = activity?.layoutInflater?.inflate(R.layout.atendimento_dialog, null)!!
        spService = v.findViewById(R.id.spService)
        spEmployee = v.findViewById(R.id.spEmployee)

        builder.setView(v)
        builder.setTitle("Novo Atendimento")
       builder.setPositiveButton("Adicionar"){_,_ ->

           // val date = view.edSchedule.text.toString()
            val date = v.edSchedule.text.toString()
            atendimentoViewModel.newAttendance(id.toString(), date)
          // Log.i("dasdsads", ": "+ atendimentoViewModel.responseStatus.value)


        }



        builder.setNegativeButton("Cancelar", null)

        listServices()
        listEmployee()
        return builder.create()
    }

    fun listServices(){
        atendimentoViewModel.listServices()
    }

    fun listEmployee(){
        atendimentoViewModel.listEmployees()
    }

    private var servicoObserver = Observer<List<Service>>{
        if(it!!.size != 0){
            Log.i("aa", "a:" +it )

            initServiceSpinner(it, spService)
        }else{
            Log.i("aa", "deu ruim:" )
        }
    }

    fun initServiceSpinner(it: List<Service>, spinner: Spinner){
       // val services = arrayListOf(Service(1, "Corte de cabelo", "20,00"))
        val services = it
        val spinnerAdapter = SpinnerAdapter(context!!, services)

       spinner.adapter = spinnerAdapter

       spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

           override fun onNothingSelected(p0: AdapterView<*>?) {}

           override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var service = parent?.getItemAtPosition(position) as Service
                atendimentoViewModel.attendance.idServico = service.id.toString()


           }

       }

    }

    private var funcionarioObserver = Observer<List<Employee>>{
        if(it!!.size != 0){
            initEmployeeSpinner(it, spEmployee)
        }else{
            Log.i("aa", "deu ruim:" )
        }
    }

     fun initEmployeeSpinner(it: List<Employee>, spinner: Spinner){
        val employees = it
        val spinnerAdapter = SpinnerFuncionarioAdapter(context!!, employees)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var employee = parent?.getItemAtPosition(position) as Employee
                atendimentoViewModel.attendance.idFuncionario = employee.id.toString()


            }

        }

    }

    private var responseStatusObserver = Observer<ResponseStatus>{
        Log.i("dasdsads", " antes do if: "+ it)
        if(it?.success == true){
            Log.i("onActivityResult", ": "+ it)
            val sharedPreferences = this.getActivity()?.getSharedPreferences("myapp", Context.MODE_PRIVATE)
            val id = sharedPreferences?.getString("id", "")
            atendimentoViewModel.listAttendance(id.toString())
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("onActivityResult", ": "+requestCode)
        Log.i("onActivityResult", ": "+resultCode)
    }*/


}