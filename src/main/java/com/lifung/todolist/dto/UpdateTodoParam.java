package com.lifung.todolist.dto;

import java.time.Instant;
import java.util.Set;

import com.lifung.todolist.enums.TodoStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTodoParam {
    String taskName;
    String note;
    Set<String> tags;
    TodoStatus status;
    Instant dueTime;
}
