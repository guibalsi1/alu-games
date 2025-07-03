package alugames.main

import alugames.model.Gamer

fun main() {
    val gamer1 = Gamer("Guilherme", "gui2132@gmail.com")
    println(gamer1)
    val gamer2 = Gamer("João", "joao32de@gmail.com", "01/01/1990", "joao123")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "01/01/1990"
        it.usuario = "guib123"
    }. also {
        println(gamer1.idInterno)
    }
    println(gamer1)
    gamer1.usuario = "maria123"
    println(gamer1)
}