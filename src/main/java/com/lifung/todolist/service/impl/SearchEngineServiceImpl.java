package com.lifung.todolist.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lifung.todolist.dto.PageData;
import com.lifung.todolist.dto.Todo;
import com.lifung.todolist.entity.TodoEntity;
import com.lifung.todolist.mapper.TodoMapper;
import com.lifung.todolist.service.SearchEngineService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchEngineServiceImpl implements SearchEngineService {
    MongoTemplate mongoTemplate;
    TodoMapper todoMapper;

    @Override
    public PageData<Todo> searchTodo(
            String userId, List<String> tags, int page, int pageSize) {
        Query query = new Query();
        Criteria criteria = Criteria.where("userId").is(userId);

        if (!CollectionUtils.isEmpty(tags)) criteria.and("tags").in(tags);

        query.addCriteria(criteria);

        // Building sorting
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        query.with(sort);

        // Total elements
        long totalElement = mongoTemplate.count(query, TodoEntity.class);
        long totalPage = (totalElement + pageSize - 1) / pageSize;

        // Building Pagination info
        Pageable pageable = PageRequest.of(page, pageSize);
        query.with(pageable);

        // Perform searching with criteria
        List<TodoEntity> products = mongoTemplate.find(query, TodoEntity.class);

        return PageData.<Todo>builder()
                .currentPage(page)
                .pageSize(pageSize)
                .totalPages(totalPage)
                .totalElements(totalElement)
                .data(products.stream().map(todoMapper::toTodo).toList())
                .build();
    }
}
