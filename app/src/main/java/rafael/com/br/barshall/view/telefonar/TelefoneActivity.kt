package rafael.com.br.barshall.view.telefonar

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_telefone.*
import rafael.com.br.barshall.R
import java.util.jar.Manifest

class TelefoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefone)

        var numero = edNumberCall.text

        btnTurnOn.setOnClickListener{
            if(numero.length < 7){
                Toast.makeText(this, "Please enter a correct number", Toast.LENGTH_LONG).show()
            } else{

                var intent =  Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero.toString(), null));
                startActivity(intent);
                if(ActivityCompat. checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    var arrayPermission = arrayOf("android.Manifest.permission.CALL_PHONE")
                    ActivityCompat.requestPermissions(this,arrayPermission, 1 )

                }

                startActivity(intent);
            }
        }

    }


}
