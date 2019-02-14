package rafael.com.br.barshall.utils

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
        Log.d("NOTIFICACAO", "Message Notification Bod: "+ p0?.notification?.body)
        Log.d("NOTIFICACAO", "Message Notification Bod: "+ p0?.notification?.title)
        NotificationUtils.showNotification(this, 21321, "PUSH",
                "PUSH",  p0?.notification?.title!!,  p0?.notification?.body!! )
    }
}