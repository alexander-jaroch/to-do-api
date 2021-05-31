package com.todoapi.todo

import kotlinx.serialization.Serializable

@Serializable
data class ToDoCount(val all: Int, val done: Int)

class ToDoList {
    val items: MutableList<ToDoItem> = mutableListOf()
    private var currentId: Long = 0

    operator fun get(id: Long): ToDoItem? {
        return items.find{ item -> item.id == id }
    }

    fun add(text: String): ToDoItem {
        val item: ToDoItem = ToDoItem(currentId++, false, text)
        items.add(item)
        return item
    }

    fun toggleCheck(id: Long) {
        val item: ToDoItem? = this[id]
        if(item != null) {
            item.checked = !item.checked
        }
    }

    fun editText(id: Long, text: String) {
        val item: ToDoItem? = this[id]
        if(item != null) {
            item.text = text
        }
    }

    fun remove(id: Long) {
        val item: ToDoItem? = this[id]
        if(item != null) {
            items.remove(item)
        }
    }

    fun count(): ToDoCount {
        return ToDoCount(items.count(), items.count { item -> item.checked })
    }
}
