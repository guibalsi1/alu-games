package alugames.main

import alugames.model.Gamer
import alugames.model.Jogo
import alugames.services.ConsumoApi
import alugames.utilities.tranformarEmIdade
import java.util.Scanner


fun main() {
    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Dados do Gamer: $gamer")
    println("Idade do Gamer: " + gamer.dataNascimento?.tranformarEmIdade())

    do {
        println("Digite o codigo do jogo para busca: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val meuInfoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null
        val resultado = runCatching {
            meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
        }
        resultado.onFailure {
            println("Jogo Inexistente\n")
        }
        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo:")
                val descricao = leitura.nextLine()
                meuJogo?.descricao = descricao
            } else {
                meuJogo?.descricao = meuJogo.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }
        println("Deseja buscar outro jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    gamer.jogosBuscados.forEach { println(it) }

    println("Jogos ordenados por titulo: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach { println(it?.titulo) }

    println("Deseja excluir algum jogo? S/N")
    val resposta = leitura.nextLine()
    if (resposta.equals("s", true)) {
        println("Jogos existentes: ${gamer.jogosBuscados.size}")
        println("insira a posição do jogo a ser excluido:")
        val numero = leitura.nextInt()
        gamer.jogosBuscados.removeAt(numero)
    }

    println("Busca finalizada")

}