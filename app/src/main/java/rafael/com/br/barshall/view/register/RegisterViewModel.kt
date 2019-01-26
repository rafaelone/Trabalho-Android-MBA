package rafael.com.br.barshall.view.register

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rafael.com.br.barshall.model.Client
import rafael.com.br.barshall.model.ResponseStatus
import rafael.com.br.barshall.repository.ClientRepository

class RegisterViewModel : ViewModel(){
    val clientRepository = ClientRepository()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    fun newClient(nome: String, senha: String, email: String, telefone: String){
        val client = Client(null,nome,senha, email, telefone)
        clientRepository.newClient(client, onComplete = {
            responseStatus.value = ResponseStatus(true, "Customer successfully registered")
        }, onError = {
            responseStatus.value = ResponseStatus(false, it?.message!!)
        })

    }
}