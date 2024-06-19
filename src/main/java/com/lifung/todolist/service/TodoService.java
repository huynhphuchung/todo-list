package com.lifung.todolist.service;

import com.lifung.todolist.dto.NewTodoParam;
import com.lifung.todolist.dto.Todo;
import com.lifung.todolist.dto.UpdateTodoParam;

public interface TodoService {
    Todo create(NewTodoParam param);

    Todo update(String todoId, UpdateTodoParam param);

    Todo getById(String todoId);

    void deleteById(String todoId);
}
