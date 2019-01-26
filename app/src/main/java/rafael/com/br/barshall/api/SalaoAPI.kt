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

    @POST("/attendance/list")
    fun getAttendance(@Body attendance: Attendance): Call<List<Attendance>>

    @GET("/service/list")
    fun getService(): Call<List<Service>>

    @GET("/employee/list")
    fun getEmployee(): Call<List<Employee>>

    @POST("/attendance/new")
    fun newAttendance(@Body attendance: Attendance): Call<Attendance>
}