package rafael.com.br.barshall.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Attendance {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var id_cliente: String? = null
    var data: String? = null
    var servico: String? = null
    var preco: String? = null
    var funcionario: String? = null

    constructor(){}

    constructor(id: Int, id_cliente: String, data: String, servico: String, preco: String, funcionario: String){
        this.id = id
        this.id_cliente = id_cliente
        this.data = data
        this.servico = servico
        this.preco = preco
        this.funcionario = funcionario
    }

}





