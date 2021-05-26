package com.todoapi

import com.todoapi.files.staticRoutes
import com.todoapi.files.toDoRoutes
import com.todoapi.todo.ToDoList
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val toDoList: ToDoList = ToDoList()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    toDoList.add("Kotlin vorstellen")

    routing {
        toDoRoutes()
        staticRoutes()
    }
}
