package rafael.com.br.barshall.model

import android.os.Parcel
import android.os.Parcelable

data class Attendance (

        var idAtendimento: Int?,
        var id_cliente: String,
        var idServico: String,
        var idFuncionario: String,
        var data: String,
        var nomeServico: String,
        var preco: String,
        var funcionario: String

): Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idAtendimento)
        parcel.writeString(id_cliente)
        parcel.writeString(idServico)
        parcel.writeString(idFuncionario)
        parcel.writeString(data)
        parcel.writeString(nomeServico)
        parcel.writeString(preco)
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



