package com.example.animelist.util

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object Provider {
    val client: HttpClient = HttpClient(Android) {

        //to map json objects returned from the api to a kotlin data class
        install(ContentNegotiation) {
            json(Json {
                //ignores json keys we have not included in our data class
                ignoreUnknownKeys = true
            })
        }
        //a logger to see logging information about every request we make using the client
        install(Logging) {
            level = LogLevel.ALL
        }

    }
}