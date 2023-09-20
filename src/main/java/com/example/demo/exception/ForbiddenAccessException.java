package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,code = HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends RuntimeException {
}