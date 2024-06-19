package com.lifung.todolist.exception;

import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lifung.todolist.configuration.MessageResource;
import com.lifung.todolist.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageResource messageResource;
    private static final String INVALID_KEY = ErrorCode.INVALID_KEY.name();

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException exception) {
        log.error("Un-categorized exception: ", exception);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(getErrorMessage(ErrorCode.UNCATEGORIZED_EXCEPTION))
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(getErrorMessage(errorCode))
                .build();

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid message key");
        }

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(getErrorMessage(errorCode))
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Get error message for ErrorCode with requesting language
    private String getErrorMessage(ErrorCode errorCode, String... params) {
        Locale requestLocale = LocaleContextHolder.getLocale();

        try {
            return messageResource.getMessage(errorCode.name(), requestLocale, params);
            // This exception happen when message key is not existed
        } catch (NoSuchMessageException e) {
            log.error("Unable to find message for {} in language: {}", errorCode.name(), requestLocale.getLanguage());
            return messageResource.getMessage(INVALID_KEY, requestLocale, errorCode.name());
        }
    }
}
