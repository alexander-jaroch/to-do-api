package com.todoapi.routes

import com.todoapi.toDoList
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

fun Route.toDoRoutes() {
    get("/items") {
        call.respondText(Json.encodeToString(toDoList.items), ContentType.Application.Json)
    }
}
