package com.todoapi.files

import com.todoapi.toDoList
import com.todoapi.todo.ToDoItemData
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

fun Route.toDoRoutes() {
    get("/items") {
        call.respondText(Json.encodeToString(toDoList.items), ContentType.Application.Json)
    }

    get("/count") {
        call.respondText(Json.encodeToString(toDoList.count()), ContentType.Application.Json)
    }

    post("/add") {
        val data: ToDoItemData = Json.decodeFromString(call.receiveText())
        call.respondText(Json.encodeToString(toDoList.add(data.text)), ContentType.Application.Json)
    }

    patch("/check/{id}") {
        val id: Long? = call.parameters["id"]?.toLong()
        if (id != null) {
            toDoList.check(id)
        }
        call.respond(HttpStatusCode.OK)
    }

    patch("/edit/{id}") {
        val id: Long? = call.parameters["id"]?.toLong()
        if (id != null) {
            val data: ToDoItemData = Json.decodeFromString(call.receiveText())
            toDoList.edit(id, data.text)
        }
        call.respond(HttpStatusCode.OK)
    }

    delete("/remove/{id}") {
        val id: Long? = call.parameters["id"]?.toLong()
        if (id != null) {
            toDoList.remove(id)
        }
        call.respond(HttpStatusCode.OK)
    }
}
