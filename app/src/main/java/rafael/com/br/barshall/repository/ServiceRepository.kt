package rafael.com.br.barshall.repository

import android.util.Log
import rafael.com.br.barshall.api.getSalaoAPI
import rafael.com.br.barshall.model.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRepository {

    fun getServices(
                    onComplete: (List<Service>?) -> Unit,
                    onError: (Throwable?) -> Unit){

        getSalaoAPI().getService().enqueue(object: Callback<List<Service>>{
            override fun onFailure(call: Call<List<Service>>?, t: Throwable?) {
                Log.i("Service repository", "error" + t)
            }

            override fun onResponse(call: Call<List<Service>>?, response: Response<List<Service>>?) {
                Log.i("servicerepository", "i" + response?.body())
                Log.i("servicerepository", "i" + response)


                onComplete(response?.body())
            }

        })
    }
}