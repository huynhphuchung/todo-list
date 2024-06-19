package com.lifung.todolist.facade;

import java.util.List;

import com.lifung.todolist.enums.TodoStatus;
import org.springframework.stereotype.Service;

import com.lifung.todolist.dto.PageData;
import com.lifung.todolist.dto.TodoDto;
import com.lifung.todolist.dto.request.NewTodoRequest;
import com.lifung.todolist.dto.request.UpdateTodoRequest;
import com.lifung.todolist.mapper.TodoMapper;
import com.lifung.todolist.service.SearchEngineService;
import com.lifung.todolist.service.TodoService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoFacade {
    TodoService todoService;
    TodoMapper todoMapper;
    SearchEngineService searchEngineService;

    public TodoDto create(NewTodoRequest request) {
        var newTodoParam = todoMapper.toNewTodoParam(request);
        newTodoParam.setStatus(TodoStatus.OPENED);

        var todo = todoService.create(newTodoParam);

        return todoMapper.toTodoDto(todo);
    }

    public TodoDto update(String todoId, UpdateTodoRequest request) {
        var updateTodoParam = todoMapper.toUpdateTodoParam(request);

        var todo = todoService.update(todoId, updateTodoParam);

        return todoMapper.toTodoDto(todo);
    }

    public TodoDto getById(String todoId) {
        return todoMapper.toTodoDto(todoService.getById(todoId));
    }

    public void deleteById(String todoId) {
        todoService.deleteById(todoId);
    }

    public PageData<TodoDto> search(
            String userId, List<String> tags, int page, int pageSize) {

        var result = searchEngineService.searchTodo(userId, tags, page, pageSize);

        return PageData.<TodoDto>builder()
                .currentPage(result.getCurrentPage())
                .totalElements(result.getTotalElements())
                .pageSize(result.getPageSize())
                .totalPages(result.getTotalPages())
                .data(result.getData().stream().map(todoMapper::toTodoDto).toList())
                .build();
    }
}
