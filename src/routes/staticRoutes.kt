package com.todoapi.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Route.staticRoutes() {
    get("/") {
        call.respondFile(File("resources/index.html"))
    }

    get("/{filename}") {
        val filename: String? = call.parameters["filename"]
        if (filename != null) {
            call.respondFile(File("resources/${filename}"))
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
