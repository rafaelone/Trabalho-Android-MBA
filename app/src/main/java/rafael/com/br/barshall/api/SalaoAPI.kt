package rafael.com.br.barshall.api

import rafael.com.br.barshall.model.*
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*

interface SalaoAPI{

    @GET("/check/checkOn")
    fun checkOn(): Call<StatusAPI>

    @POST("/client/new")
    fun newClient(@Body client: Client): Call<Client>

    @POST("/login/login-in")
    fun logIn(@Body client: Client): Call<Client>


}