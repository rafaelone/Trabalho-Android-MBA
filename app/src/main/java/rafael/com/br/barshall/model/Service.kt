package rafael.com.br.barshall.model

import rafael.com.br.barshall.view.dialogs.adapter.ItemSpinner

/*data class Service (

        var id: Int?,
        var nome: String,
        var preco: String


)*/

data class Service(var id: Int = 0, var nome: String = "", var preco: String = "") : ItemSpinner{
    override val itemID: Any
    get() = id
    override val itemNome: String
        get() = nome
    override val itemPreco: String
        get() = preco
}

//data class Service(override val id: Any, override val nome: String, override val preco: String): ItemSpinner {
