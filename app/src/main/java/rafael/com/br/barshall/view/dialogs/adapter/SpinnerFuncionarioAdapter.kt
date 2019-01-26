package rafael.com.br.barshall.view.dialogs.adapter

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView

class SpinnerFuncionarioAdapter<T : ItemSpinnerFuncionario>(
        private val context: Context,
        private val lista: List<T>) : BaseAdapter(), SpinnerAdapter  {

    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
        val txt = TextView(context)
        txt.gravity = Gravity.LEFT
        txt.setPadding(16, 16, 16, 16   )
        txt.textSize = 10f
        txt.setBackgroundColor(Color.parseColor("#ffffff"))
        txt.text = lista[i].itemNome
        txt.setTextColor(Color.parseColor("#000000"))
        return txt
    }

    override fun getItem(i: Int): Any {
        return lista[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }

    fun getMyItemId(position: Int): Any{
        return lista[position].itemID
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val txt = TextView(context)
        txt.setPadding(48, 20, 48, 20)
        txt.textSize = 10f
        txt.gravity = Gravity.CENTER_VERTICAL
        txt.text = lista[position].itemNome
        txt.setTextColor(Color.parseColor("#000000"))
        txt.setBackgroundColor(Color.parseColor("#ffffff"))

        return txt
    }

}
