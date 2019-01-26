package rafael.com.br.barshall.view.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import rafael.com.br.barshall.model.Client
import rafael.com.br.barshall.model.ResponseStatus
import rafael.com.br.barshall.repository.ClientRepository

class LoginViewModel : ViewModel() {

    val clientRepository = ClientRepository()
    val cliente: MutableLiveData<Client> = MutableLiveData()

    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    fun logIn(email: String, senha: String) {
        val client = Client(null, "", senha, email, "")

        clientRepository.logIn(client, onComplete = {
            responseStatus.value = ResponseStatus(true, "Login with sucess")
            cliente.value = it

        }, onError = {
            responseStatus.value = ResponseStatus(false, it?.message!!)

        })
    }

}