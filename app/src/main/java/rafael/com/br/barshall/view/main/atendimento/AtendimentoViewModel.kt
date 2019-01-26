package rafael.com.br.barshall.view.main.atendimento

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import rafael.com.br.barshall.model.Attendance
import rafael.com.br.barshall.model.Employee
import rafael.com.br.barshall.model.ResponseStatus
import rafael.com.br.barshall.model.Service
import rafael.com.br.barshall.repository.AttendanceRepository
import rafael.com.br.barshall.repository.EmployeeRepository
import rafael.com.br.barshall.repository.ServiceRepository

class AtendimentoViewModel: ViewModel(){


    val atendimentoRepository = AttendanceRepository()
    val atendimentos: MutableLiveData<List<Attendance>> = MutableLiveData()

    val serviceRepository = ServiceRepository();
    val servicos: MutableLiveData<List<Service>> = MutableLiveData()

    val employeeRepository = EmployeeRepository();
    val funcionarios: MutableLiveData<List<Employee>> = MutableLiveData()

    val attendance = Attendance(null, "", "", "", "", "","","")

    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()


    fun listAttendance(id_cliente: String){

        val attendance = Attendance(null,id_cliente, "", "", "","","","")

        atendimentoRepository.getAttendance(attendance, onComplete = {
            Log.i("aaaaa", "a"+ it)

            atendimentos.value = it

        }, onError = {
            atendimentos.value = mutableListOf()
        })
    }

    fun listServices(){
        serviceRepository.getServices(onComplete = {
            servicos.value = it
        }, onError = {
            servicos.value = mutableListOf()
        })
    }

    fun listEmployees(){
        employeeRepository.getEmployee(onComplete = {funcionarios.value = it},
                onError = {
                    funcionarios.value = mutableListOf()
                })
    }

    fun newAttendance(id: String, date: String) {

        attendance.id_cliente = id
        attendance.data = date
        Log.i("Attendance", "msg: "+attendance)

        atendimentoRepository.newAttendance(attendance, onComplete = {
            responseStatus.value = ResponseStatus(true, "Attendanca has been registrated")

        }, onError = {
            responseStatus.value = ResponseStatus(false, it?.message!!)
        })
    }


}