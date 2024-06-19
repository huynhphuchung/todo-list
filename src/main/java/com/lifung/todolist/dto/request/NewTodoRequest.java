package com.lifung.todolist.dto.request;

import java.time.Instant;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

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
public class NewTodoRequest {
    @NotBlank(message = "USER_ID_BLANK")
    String userId;

    @NotBlank(message = "TASK_NAME_BLANK")
    String taskName;

    String note;
    Set<String> tags;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Instant dueTime;
}
