package com.todoapi.todo

import kotlinx.serialization.Serializable

@Serializable
data class ToDoCount(val all: Int, val done: Int)

class ToDoList {
    val items: MutableList<ToDoItem> = mutableListOf()
    private var currentId: Long = 0

    fun count(): ToDoCount {
        return ToDoCount(items.count(), items.count { item -> item.checked })
    }

    fun add(text: String): ToDoItem {
        val item = ToDoItem(currentId++, false, text)
        items.add(item)
        return item
    }

    fun check(id: Long) {
        val item: ToDoItem? = get(id)
        if (item != null) {
            item.checked = !item.checked
        }
    }

    fun edit(id: Long, text: String) {
        val item: ToDoItem? = get(id)
        if (item != null) {
            item.text = text
        }
    }

    fun remove(id: Long) {
        val item: ToDoItem? = get(id)
        if (item != null) {
            items.remove(item)
        }
    }

    operator fun get(id: Long): ToDoItem? {
        return items.find { item -> item.id == id }
    }
}
