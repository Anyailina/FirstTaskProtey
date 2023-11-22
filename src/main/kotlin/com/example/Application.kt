package com.example

import com.example.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class EncodedData(
    val original: String,
    val base64: String,
    val md5: String,
    val sha256: String
)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { isLenient = true; ignoreUnknownKeys = true })
    }

    routing {
        get("/encode") {
            val dataToEncode = call.request.queryParameters["data"] ?: ""
            val base64 = dataToEncode.encodeBase64()
            val md5 = dataToEncode.encodeMD5()
            val sha256 = dataToEncode.encodeSHA256()

            val encodedData = EncodedData(dataToEncode, base64, md5, sha256)
            call.respond(encodedData)
        }
    }
}