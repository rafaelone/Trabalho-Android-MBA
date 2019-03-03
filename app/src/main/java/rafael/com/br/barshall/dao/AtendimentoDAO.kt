package rafael.com.br.barshall.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import rafael.com.br.barshall.model.Attendance

@Dao
interface AtendimentoDAO{

    @Insert
    fun inserir(atendimento: Attendance)

    @Query("SELECT * FROM Attendance WHERE id_cliente = :id")
   fun listarAtendimentos(id: Int): LiveData<List<Attendance>>

    @Query("UPDATE Attendance set id_cliente = :id_cliente, data = :data, servico = :servico, funcionario = :funcionario where id = :id" )
   // fun atualizar(data:String, servico: String, funcionario: String, id: Int)
      fun atualizar(id: Int, id_cliente: String, servico: String, funcionario: String, data: String)
    @Query("DELETE FROM Attendance WHERE id = :id")
    fun apagar(id: Int)

}