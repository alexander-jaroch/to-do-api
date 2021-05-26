"use strict";
var ToDoApp;
(function (ToDoApp) {
    var toDoList = document.querySelector(".todolist");
    getItems();
    function getItems() {
        var http = new XMLHttpRequest();
        request("GET", "/items", function (response) {
            if (response) {
                for (var _i = 0, response_1 = response; _i < response_1.length; _i++) {
                    var item = response_1[_i];
                    createToDo(item);
                }
            }
        });
    }
    function createToDo(item) {
        var toDoItem = document.createElement("div");
        toDoItem.classList.add("todo-item");
        var check = document.createElement("i");
        check.classList.add("far");
        if (item.checked) {
            check.classList.add("fa-check-square");
        }
        else {
            check.classList.add("fa-square");
        }
        check.addEventListener("click", function () {
            request("PATCH", "/check/" + item.id, function () {
                check.classList.toggle("fa-check-square");
                check.classList.toggle("fa-square");
            });
        });
        toDoItem.appendChild(check);
        var input = document.createElement("input");
        var text = document.createElement("span");
        text.innerText = item.text;
        toDoItem.appendChild(text);
        text.addEventListener("click", function () {
            text.replaceWith(input);
        });
        input.type = "text";
        input.value = text.innerText || "";
        input.addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                request("PATCH", "/edit/" + item.id, function () {
                    text.innerText = input.value;
                    input.replaceWith(text);
                }, { text: input.value });
            }
        });
        var trash = document.createElement("i");
        trash.classList.add("far", "fa-trash-alt");
        trash.addEventListener("click", function () {
            request("DELETE", "/remove/" + item.id, function () {
                toDoItem.remove();
            });
        });
        toDoItem.appendChild(trash);
        toDoList.appendChild(toDoItem);
    }
    function request(method, url, callback, data) {
        var http = new XMLHttpRequest();
        http.open(method, url);
        if (data) {
            http.send(JSON.stringify(data));
        }
        else {
            http.send();
        }
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var response = this.response ? JSON.parse(this.response) : undefined;
                callback(response);
            }
        };
    }
})(ToDoApp || (ToDoApp = {}));
//# sourceMappingURL=Main.js.map