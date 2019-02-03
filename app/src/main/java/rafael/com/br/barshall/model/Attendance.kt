package rafael.com.br.barshall.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

@Entity
class Attendance (
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var id_cliente: String? = null,
        var data: String? = null,
        var servico: String? = null,
        var funcionario: String? = null
):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(id_cliente)
        parcel.writeString(data)
        parcel.writeString(servico)
        parcel.writeString(funcionario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attendance> {
        override fun createFromParcel(parcel: Parcel): Attendance {
            return Attendance(parcel)
        }

        override fun newArray(size: Int): Array<Attendance?> {
            return arrayOfNulls(size)
        }
    }

}

/*
@PrimaryKey(autoGenerate = true)
var id: Int = 0
var id_cliente: String? = null
var data: String? = null
var servico: String? = null
var funcionario: String? = null

constructor(){}

constructor(id_cliente: String, data: String, servico: String,  funcionario: String){
    this.id_cliente = id_cliente
    this.data = data
    this.servico = servico
    this.funcionario = funcionario
}*/