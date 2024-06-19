package com.lifung.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, HttpStatus.BAD_REQUEST),
    TODO_NOT_FOUND(1004, HttpStatus.NOT_FOUND),
    USER_ID_BLANK(1005, HttpStatus.BAD_REQUEST),
    TASK_NAME_BLANK(1006, HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, HttpStatusCode statusCode) {
        this.code = code;
        this.statusCode = statusCode;
    }

    private final int code;
    private final HttpStatusCode statusCode;
}
