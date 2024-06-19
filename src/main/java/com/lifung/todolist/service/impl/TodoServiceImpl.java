package com.lifung.todolist.service.impl;

import org.springframework.stereotype.Service;

import com.lifung.todolist.dto.NewTodoParam;
import com.lifung.todolist.dto.Todo;
import com.lifung.todolist.dto.UpdateTodoParam;
import com.lifung.todolist.exception.AppException;
import com.lifung.todolist.exception.ErrorCode;
import com.lifung.todolist.mapper.TodoMapper;
import com.lifung.todolist.repository.TodoRepository;
import com.lifung.todolist.service.TodoService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;
    TodoMapper todoMapper;

    @Override
    public Todo create(NewTodoParam param) {
        var todoEntity = todoMapper.toTodoEntity(param);

        todoEntity = todoRepository.save(todoEntity);

        return todoMapper.toTodo(todoEntity);
    }

    @Override
    public Todo update(String todoId, UpdateTodoParam param) {
        var todoEntity = todoRepository.findById(todoId).orElseThrow(() -> new AppException(ErrorCode.TODO_NOT_FOUND));

        todoMapper.updateTodoEntity(todoEntity, param);

        return todoMapper.toTodo(todoRepository.save(todoEntity));
    }

    @Override
    public Todo getById(String todoId) {
        return todoRepository
                .findById(todoId)
                .map(todoMapper::toTodo)
                .orElseThrow(() -> new AppException(ErrorCode.TODO_NOT_FOUND));
    }

    @Override
    public void deleteById(String todoId) {
        todoRepository.deleteById(todoId);
    }
}
