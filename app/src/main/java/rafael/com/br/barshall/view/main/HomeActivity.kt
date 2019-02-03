package rafael.com.br.barshall.view.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.context.AppCtx
import rafael.com.br.barshall.view.main.atendimento.ListaAtendimentoFragment
import rafael.com.br.barshall.view.main.info.InfoFragment
import rafael.com.br.barshall.view.main.map.MapsActivity
import rafael.com.br.barshall.view.telefonar.TelefoneActivity

class HomeActivity : AppCompatActivity() {

    val manager = supportFragmentManager


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadHomeFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                loadMapFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                loadInfoFragment()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        AppCtx.getInstance().initialize(this)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.call ->{
                val intent = Intent(this, TelefoneActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }



    fun loadInfoFragment(){

        val transaction = manager.beginTransaction()
        val fragment = InfoFragment()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadHomeFragment(){

        val transaction = manager.beginTransaction()
        val fragment = ListaAtendimentoFragment()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun loadMapFragment(){


        val mapIntent = Intent(this, MapsActivity::class.java)
        startActivity(mapIntent)


    }


}
