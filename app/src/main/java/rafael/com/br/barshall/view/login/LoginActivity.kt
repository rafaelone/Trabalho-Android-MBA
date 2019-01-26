package rafael.com.br.barshall.view.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_login.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.model.Client
import rafael.com.br.barshall.view.main.HomeActivity
import rafael.com.br.barshall.view.main.atendimento.ListaAtendimentoFragment
import rafael.com.br.barshall.view.register.RegisterActivity


class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        Stetho.initializeWithDefaults(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.cliente.observe(this, responseClienteObserver)

        btnRegistro.setOnClickListener {
            MostrarTelaRegistro()
        }

        btSignIn.setOnClickListener {
            loginViewModel.logIn(
                    etEmail.text.toString(),
                    etPassword.text.toString()
            )
        }
    }

    fun MostrarTelaRegistro() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    private var responseClienteObserver = Observer<Client> {
        if (it !== null) {


            Log.i("logcat", ": "+ckConectado.isChecked)
            if(ckConectado.isChecked == true){
                val sharedPreferences = getSharedPreferences("myapp", Context.MODE_PRIVATE)
                val editor  = sharedPreferences.edit()
                editor.putString("id", it.id.toString())
                editor.putString("nome", it.nome.toString())
                editor.apply()
            }

            val homeIntent  = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent )
            finish()
            Toast.makeText(this, "Successful login", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Username or password is wrong", Toast.LENGTH_SHORT).show()
        }
    }



}
