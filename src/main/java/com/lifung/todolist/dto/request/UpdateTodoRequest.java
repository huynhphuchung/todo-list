package com.lifung.todolist.dto.request;

import java.time.Instant;
import java.util.Set;

import com.lifung.todolist.enums.TodoStatus;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
public class UpdateTodoRequest {
    @NotNull
    @Length(min = 3, max = 100)
    String taskName;

    String note;
    Set<String> tags;

    TodoStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Instant dueTime;
}
