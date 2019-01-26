package rafael.com.br.barshall.repository

import android.util.Log
import rafael.com.br.barshall.api.getSalaoAPI
import rafael.com.br.barshall.model.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository {

    fun getEmployee(
            onComplete: (List<Employee>?) -> Unit,
            onError: (Throwable?) -> Unit){

        getSalaoAPI().getEmployee().enqueue(object: Callback<List<Employee>> {
            override fun onFailure(call: Call<List<Employee>>?, t: Throwable?) {
                Log.i("Service repository", "error" + t)
            }

            override fun onResponse(call: Call<List<Employee>>?, response: Response<List<Employee>>?) {
                Log.i("employeerepository", "i" + response?.body())
                Log.i("employeerepository", "i" + response)


                onComplete(response?.body())
            }

        })
    }

}