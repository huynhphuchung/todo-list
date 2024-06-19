package com.lifung.todolist.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lifung.todolist.dto.ApiResponse;
import com.lifung.todolist.dto.PageData;
import com.lifung.todolist.dto.TodoDto;
import com.lifung.todolist.dto.request.NewTodoRequest;
import com.lifung.todolist.dto.request.UpdateTodoRequest;
import com.lifung.todolist.facade.TodoFacade;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Tag(name = "Todo Api")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoController {
    TodoFacade todoFacade;

    @PostMapping("/todo")
    ApiResponse<TodoDto> create(@RequestBody @Valid NewTodoRequest request) {
        return ApiResponse.<TodoDto>builder().result(todoFacade.create(request)).build();
    }

    @GetMapping("/todo/{todoId}")
    ApiResponse<TodoDto> getById(@PathVariable("todoId") String todoId) {
        return ApiResponse.<TodoDto>builder().result(todoFacade.getById(todoId)).build();
    }

    @DeleteMapping("/todo/{todoId}")
    ApiResponse<Void> deleteById(@PathVariable("todoId") String todoId) {
        todoFacade.deleteById(todoId);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/todo/{todoId}")
    ApiResponse<TodoDto> update(@PathVariable("todoId") String todoId, @RequestBody UpdateTodoRequest request) {
        return ApiResponse.<TodoDto>builder()
                .result(todoFacade.update(todoId, request))
                .build();
    }

    @GetMapping("/todo/search")
    ApiResponse<PageData<TodoDto>> search(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "tag", required = false) List<String> tags,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ApiResponse.<PageData<TodoDto>>builder()
                .result(todoFacade.search(userId, tags, page, pageSize))
                .build();
    }
}
