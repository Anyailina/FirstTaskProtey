package com.example.plugins

import com.example.Enteties.Message
import com.example.Enteties.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
val users = mutableListOf<User>()
val messages = mutableListOf<Message>()
fun Application.ChatRotes() {

    routing {
        post("/register") {
            val user = call.receive<User>()
            users.add(user)
            call.respond(HttpStatusCode.OK, "User registered successfully")
        }

        post("/login") {
            val credentials = call.receive<User>()
            val user = users.find { it.username == credentials.username && it.password == credentials.password }
            if (user != null) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }

        get("/messages/{userId}") {
            val userId = call.parameters["userId"]
            val userMessages = messages.filter { it.recipientId.toString() == userId }
            call.respond(userMessages)
        }

        post("/send") {
            val message = call.receive<Message>()
            messages.add(message)
            call.respond(HttpStatusCode.OK, "Message sent successfully")
        }
    }
}

