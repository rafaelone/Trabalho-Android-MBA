package rafael.com.br.barshall

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        FirebaseApp.initializeApp(this)

        // Get token
        // [START retrieve_current_token]
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("GEN_TOKEN", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
                    //val msg = getString(R.string.msg_token_fmt, token)
                    Log.d("GEN_TOKEN", token)
                    //Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
        // [END retrieve_current_token]
    }
}