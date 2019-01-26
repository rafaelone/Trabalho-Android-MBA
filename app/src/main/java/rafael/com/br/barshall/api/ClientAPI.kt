package rafael.com.br.barshall.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientApi<T> {

    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .client(getOkhttpClientAuth().build())
                .baseUrl("https://api-mba.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(c)
    }

}

    fun getOkhttpClientAuth(): OkHttpClient.Builder{
        return OkHttpClient.Builder()
                //.addInterceptor(AuthInterceptor())
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
    }

    fun getSalaoAPI(): SalaoAPI {
    return ClientApi<SalaoAPI>().getClient(SalaoAPI::class.java)
}