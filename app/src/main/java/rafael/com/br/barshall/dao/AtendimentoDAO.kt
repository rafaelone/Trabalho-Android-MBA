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
   // @Query("SELECT * FROM  Attendance")
   // fun listarAtendimentos(): LiveData<List<Attendance>>

    @Update
    fun atualizar(atendimento: Attendance)

    @Delete
    fun apagar(atendimento: Attendance)

}