package com.diogo.barbernet.api.infra.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponserError(String message, HttpStatus httpStatus, LocalDateTime time) {
}
