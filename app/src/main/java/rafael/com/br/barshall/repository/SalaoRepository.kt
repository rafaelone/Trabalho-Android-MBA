package rafael.com.br.barshall.repository

import rafael.com.br.barshall.api.getSalaoAPI
import rafael.com.br.barshall.model.StatusAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SalaoRepository {


    fun checkAPI(
            onComplete: (StatusAPI?) -> Unit,
            onError: (Throwable?) -> Unit){

        getSalaoAPI().checkOn().enqueue(object: Callback<StatusAPI>{
            override fun onFailure(call: Call<StatusAPI>?, t: Throwable?) {
                onError(t)
            }

            override fun onResponse(call: Call<StatusAPI>?, response: Response<StatusAPI>?) {
                onComplete(response?.body())

            }

        })
    }


}