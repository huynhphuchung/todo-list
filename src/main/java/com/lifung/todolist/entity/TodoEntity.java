package com.lifung.todolist.entity;

import java.time.Instant;
import java.util.Set;

import com.lifung.todolist.enums.TodoStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Document("todo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoEntity {
    @MongoId
    String id;

    @Indexed
    String userId;

    String taskName;
    String note;

    @Indexed
    Set<String> tags;
    Instant dueTime;

    TodoStatus status;

    @Indexed
    @CreatedDate
    Instant createdDate;

    @LastModifiedDate
    Instant lastModifiedDate;
}
