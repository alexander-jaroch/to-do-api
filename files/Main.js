"use strict";
var ToDoApp;
(function (ToDoApp) {
    const toDoList = document.querySelector(".todo-list");
    const newToDoInput = document.querySelector("input.new-todo");
    const newToDoButton = document.querySelector("i.new-todo");
    const toDoCount = document.querySelector(".todo-count");
    const toDoDone = document.querySelector(".todo-done");
    const toDoRatio = document.querySelector(".todo-ratio");
    function add() {
        request("POST", "/add", response => {
            if (response) {
                createToDo(response);
            }
        }, { text: newToDoInput.value });
    }
    newToDoInput.addEventListener("keypress", event => {
        if (event.key === "Enter") {
            add();
        }
    });
    newToDoButton.addEventListener("click", () => {
        add();
    });
    getItems();
    function getItems() {
        const http = new XMLHttpRequest();
        request("GET", "/items", response => {
            if (response) {
                for (const item of response) {
                    createToDo(item);
                }
            }
        });
    }
    function createToDo(item) {
        newToDoInput.value = "";
        const toDoItem = document.createElement("div");
        toDoItem.classList.add("todo-item");
        const check = document.createElement("i");
        check.classList.add("far");
        if (item.checked) {
            check.classList.add("fa-check-square");
        }
        else {
            check.classList.add("fa-square");
        }
        check.addEventListener("click", () => {
            request("PATCH", "/check/" + item.id, () => {
                check.classList.toggle("fa-check-square");
                check.classList.toggle("fa-square");
                updateCount();
            });
        });
        toDoItem.appendChild(check);
        const input = document.createElement("input");
        const text = document.createElement("span");
        text.innerText = item.text;
        toDoItem.appendChild(text);
        text.addEventListener("click", () => {
            text.replaceWith(input);
            input.focus();
        });
        input.type = "text";
        input.value = text.innerText || "";
        function edit() {
            request("PATCH", "/edit/" + item.id, () => {
                text.innerText = input.value;
                input.replaceWith(text);
            }, { text: input.value });
        }
        input.addEventListener("keypress", event => {
            if (event.key === "Enter") {
                edit();
            }
        });
        input.addEventListener("blur", () => {
            edit();
        });
        const trash = document.createElement("i");
        trash.classList.add("far", "fa-trash-alt");
        trash.addEventListener("click", () => {
            request("DELETE", "/remove/" + item.id, () => {
                toDoItem.remove();
                updateCount();
            });
        });
        toDoItem.appendChild(trash);
        toDoList.appendChild(toDoItem);
        updateCount();
    }
    function request(method, url, callback, data) {
        const http = new XMLHttpRequest();
        http.open(method, url);
        if (data) {
            http.send(JSON.stringify(data));
        }
        else {
            http.send();
        }
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const response = this.response ? JSON.parse(this.response) : undefined;
                callback(response);
            }
        };
    }
    function updateCount() {
        request("GET", "/count", response => {
            if (response) {
                toDoDone.innerText = response.done.toString();
                toDoCount.innerText = response.all.toString();
                toDoRatio.innerText = Math.round(response.done / response.all * 100).toString();
            }
        });
    }
})(ToDoApp || (ToDoApp = {}));
//# sourceMappingURL=Main.js.map