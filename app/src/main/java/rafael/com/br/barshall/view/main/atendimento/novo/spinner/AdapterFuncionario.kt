package rafael.com.br.barshall.view.main.atendimento.novo.spinner

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterFuncionario(val context: Context, var listFuncionario: Array<String>): BaseAdapter(){
    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
        val txt = TextView(context)
        txt.gravity = Gravity.LEFT
        txt.setPadding(16, 16, 16, 16   )
        txt.textSize = 10f
        txt.setBackgroundColor(Color.parseColor("#000000"))
        txt.text = listFuncionario[i]
        txt.setTextColor(Color.parseColor("#FFD700"))
        return txt
    }

    override fun getItem(i: Int): Any {
        return listFuncionario[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getMyItemId(position: Int): Any{

        return listFuncionario[position]
    }

    fun getFuncionarioByName(nome: String): Any{
        return listFuncionario.indexOf(nome)
    }

    override fun getCount(): Int {
        return listFuncionario.size
    }

    override fun getDropDownView(i: Int, convertView: View?, parent: ViewGroup?): View {
        val txt = TextView(context)
        txt.gravity = Gravity.LEFT
        txt.setPadding(16, 16, 16, 16   )
        txt.textSize = 10f
        txt.setBackgroundColor(Color.parseColor("#000000"))
        txt.text = listFuncionario[i]
        txt.setTextColor(Color.parseColor("#FFD700"))
        return txt
    }

}