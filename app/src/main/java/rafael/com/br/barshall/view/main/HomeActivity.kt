package rafael.com.br.barshall.view.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import rafael.com.br.barshall.R
import rafael.com.br.barshall.view.main.atendimento.ListaAtendimentoFragment
import rafael.com.br.barshall.view.main.info.InfoFragment
import rafael.com.br.barshall.view.main.map.MapsActivity

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


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
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

    fun loadMyAttendance(){
        val transaction = manager.beginTransaction()
        val fragment = ListaAtendimentoFragment()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadMapFragment(){

        /*val transaction = manager.beginTransaction()
        val fragment = MapsActivity()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()*/

        val mapIntent = Intent(this, MapsActivity::class.java)
        startActivity(mapIntent)


    }


}
