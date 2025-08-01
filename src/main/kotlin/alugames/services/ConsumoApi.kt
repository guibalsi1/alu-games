package alugames.services

import alugames.model.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {
    fun buscaJogo(id: String): InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient? = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response: HttpResponse<String?>? = client!!
            .send(request, BodyHandlers.ofString())

        val json = response?.body()
        val gson = Gson()
        val infoJogo = gson.fromJson(json, InfoJogo::class.java)
        return infoJogo
    }
}