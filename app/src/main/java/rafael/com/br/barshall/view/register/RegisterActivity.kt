package rafael.com.br.barshall.view.register

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.Response
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.ResponseStatus

class RegisterActivity : AppCompatActivity() {

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        registerViewModel.responseStatus.observe(this, responseStatusObserver)

        btRegistro.setOnClickListener {

            if(etNome.text.trim().toString().length < 3){
                Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show()
            }else if (edEmail.text.trim().toString().length < 5){
                Toast.makeText(this, "Invalid e-mail", Toast.LENGTH_SHORT).show()
            }else if( edTelefone.text.trim().toString().length < 8) {
                Toast.makeText(this, "Invalid telephone", Toast.LENGTH_SHORT).show()
            }else if(edSenha.text.trim().toString().length < 2){
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show() }
            else{
                registerViewModel.newClient(
                        etNome.text.toString(),
                        edSenha.text.toString(),
                        edEmail.text.toString(),
                        edTelefone.text.toString()
                )
            }
        }

    }

    private var responseStatusObserver = Observer<ResponseStatus>{
        if(it?.success == true){
            setResult(Activity.RESULT_OK)
            Toast.makeText(this, "Successful registration", Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this, it?.mensage, Toast.LENGTH_SHORT).show()
        }
    }
}
