package com.todoapi

import com.todoapi.routes.staticRoutes
import com.todoapi.routes.toDoRoutes
import com.todoapi.todo.ToDoList
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val toDoList: ToDoList = ToDoList()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    toDoList.add("Kotlin/KTOR vorstellen")
    routing {
        /*get("/") {
            call.respondText("Hello World!", ContentType.Text.Plain);
        }*/

        staticRoutes()
        toDoRoutes()
    }
}
