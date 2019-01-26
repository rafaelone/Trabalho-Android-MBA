package rafael.com.br.barshall.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import rafael.com.br.barshall.api.getSalaoAPI
import rafael.com.br.barshall.model.Client
import rafael.com.br.barshall.model.StatusAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientRepository{

    fun newClient(client: Client,
                  onComplete: (Client?) -> Unit,
                  onError: (Throwable?) -> Unit) {

        getSalaoAPI().newClient(client).enqueue(object : Callback<Client> {
            override fun onFailure(call: Call<Client>?, t: Throwable?) {
                onError(t)
            }

            override fun onResponse(call: Call<Client>?, response: Response<Client>?) {
                onComplete(response?.body())
            }

        })
    }

    fun logIn(client: Client,
              onComplete: (Client?) -> Unit,
              onError: (Throwable?) -> Unit ){



        getSalaoAPI().logIn(client).enqueue(object: Callback<Client>{
            override fun onFailure(call: Call<Client>?, t: Throwable?) {
                onError(t)
            }

            override fun onResponse(call: Call<Client>?, response: Response<Client>?) {
                val dados = response?.body()
                onComplete(response?.body())

            }

        })
    }
}