package rafael.com.br.barshall.repository

import android.util.Log
import rafael.com.br.barshall.api.getSalaoAPI
import rafael.com.br.barshall.model.Attendance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AttendanceRepository{


     fun getAttendance(attendance: Attendance,
            onComplete: (List<Attendance>?) -> Unit,
            onError: (Throwable?) -> Unit ){



      getSalaoAPI().getAttendance(attendance).enqueue(object: Callback<List<Attendance>>{

          override fun onFailure(call: Call<List<Attendance>>?, t: Throwable?) {
              Log.i("teste", "aa " + t)
             onError(t)
          }

          override fun onResponse(call: Call<List<Attendance>>?, response: Response<List<Attendance>>?) {
              Log.i("idcliente", "i" + response?.body())
              onComplete(response?.body())
          }


      })


  }

    fun newAttendance(attendance: Attendance,
                  onComplete: (Attendance?) -> Unit,
                  onError: (Throwable?) -> Unit) {

        getSalaoAPI().newAttendance(attendance).enqueue(object : Callback<Attendance> {
            override fun onFailure(call: Call<Attendance>?, t: Throwable?) {
                onError(t)
            }

            override fun onResponse(call: Call<Attendance>?, response: Response<Attendance>?) {
                onComplete(response?.body())
            }

        })
    }



}