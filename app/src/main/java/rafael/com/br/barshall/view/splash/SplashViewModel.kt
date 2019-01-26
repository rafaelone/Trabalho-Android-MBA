package rafael.com.br.barshall.view.splash

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rafael.com.br.barshall.repository.SalaoRepository
import rafael.com.br.barshall.model.ResponseStatus

class SplashViewModel : ViewModel(){

    val salaoRepository = SalaoRepository()
    val responseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun checkStatus(){
        salaoRepository.checkAPI(onComplete = {
            isLoading.value = false
            responseStatus.value = ResponseStatus(true, "Api esta rodando")
        }, onError = {
            isLoading.value = false
            responseStatus.value = ResponseStatus(false, it?.message!!)
        })
    }

}