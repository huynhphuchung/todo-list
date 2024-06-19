package com.lifung.todolist.service;

import java.util.List;

import com.lifung.todolist.dto.PageData;
import com.lifung.todolist.dto.Todo;

public interface SearchEngineService {
    PageData<Todo> searchTodo(String userId, List<String> tags, int page, int pageSize);
}
