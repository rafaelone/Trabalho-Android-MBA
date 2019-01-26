package rafael.com.br.barshall.model

import rafael.com.br.barshall.view.dialogs.adapter.ItemSpinnerFuncionario

/*
data class Employee (

        var id: Int?,
        var nome: String,
        var email: String,
        var senha: String,
        var rg: String,
        var cpf: String,
        var telefone: String

)*/

data class Employee(var id: Int = 0,
                    var nome: String = "",
                    var email: String = "",
                    var senha: String = "",
                    var rg: String = "",
                    var cpf: String = "",
                    var telefone: String = "") : ItemSpinnerFuncionario{
    override val itemID: Any
        get() = id
    override val itemNome: String
        get() = nome
    override val itemEmail: String
        get() = email
    override val itemSenha: String
        get() = senha
    override val itemRg: String
        get() = rg
    override val itemCpf: String
        get() = cpf
    override val itemTelefone: String
        get() = telefone

}