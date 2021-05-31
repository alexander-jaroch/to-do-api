package com.todoapi.todo

import kotlinx.serialization.Serializable

@Serializable
data class ToDoItem (val id: Long, var checked: Boolean, var text: String)
