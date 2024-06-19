package com.lifung.todolist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lifung.todolist.entity.TodoEntity;

@Repository
public interface TodoRepository extends MongoRepository<TodoEntity, String> {}
