package com.lifung.todolist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.lifung.todolist.dto.NewTodoParam;
import com.lifung.todolist.dto.Todo;
import com.lifung.todolist.dto.TodoDto;
import com.lifung.todolist.dto.UpdateTodoParam;
import com.lifung.todolist.dto.request.NewTodoRequest;
import com.lifung.todolist.dto.request.UpdateTodoRequest;
import com.lifung.todolist.entity.TodoEntity;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    NewTodoParam toNewTodoParam(NewTodoRequest request);

    UpdateTodoParam toUpdateTodoParam(UpdateTodoRequest request);

    TodoDto toTodoDto(Todo todo);

    TodoEntity toTodoEntity(NewTodoParam todoParam);

    Todo toTodo(TodoEntity todoEntity);

    void updateTodoEntity(@MappingTarget TodoEntity entity, UpdateTodoParam param);
}
