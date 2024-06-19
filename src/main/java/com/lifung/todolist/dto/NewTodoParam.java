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
public class NewTodoParam {
    String userId;
    String taskName;
    String note;
    TodoStatus status;
    Set<String> tags;
    Instant dueTime;
}
