package com.lifung.todolist.dto;

import java.util.Collections;
import java.util.List;

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
public class PageData<T> {
    int currentPage;
    int pageSize;
    long totalElements;
    long totalPages;

    @Builder.Default
    List<T> data = Collections.emptyList();
}
