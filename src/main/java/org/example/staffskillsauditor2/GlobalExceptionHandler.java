package org.example.staffskillsauditor2;

import org.example.staffskillsauditor2.skills.application.exceptions.SkillNotFoundException;
import org.example.staffskillsauditor2.skills.application.exceptions.StaffNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({SkillNotFoundException.class, StaffNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(RuntimeException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        Map<String, String> validationErrors = null;

        if (ex instanceof ResponseStatusException rse) {
            status = HttpStatus.valueOf(rse.getStatusCode().value());
            message = rse.getReason();
        }
        else if (ex instanceof MethodArgumentNotValidException manve) {
            status = HttpStatus.BAD_REQUEST;
            message = "Validation failed for one or more fields";
            validationErrors = manve.getBindingResult().getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            error -> Objects.requireNonNullElse(error.getDefaultMessage(), "Invalid value"),
                            (existing, replacement) -> existing
                    ));
        }
        else if (ex instanceof ConstraintViolationException cve) {
            status = HttpStatus.BAD_REQUEST;
            message = "Database constraint validation failed.";
            validationErrors = cve.getConstraintViolations().stream()
                    .collect(Collectors.toMap(
                            violation -> violation.getPropertyPath().toString(),
                            ConstraintViolation::getMessage
                    ));
        }
        else if (ex instanceof DataIntegrityViolationException) {
            status = HttpStatus.BAD_REQUEST;
            message = "A duplicate record already exists";
        }
        else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Validation failed for one or more fields";
            validationErrors = Map.of("errorReason", ex.getMessage());
        }

        return createErrorResponse(status, message, validationErrors);
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(
            HttpStatus status,
            String message,
            Map<String, String> validationErrors
    ) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status.value());
        responseBody.put("error", status.getReasonPhrase());
        responseBody.put("message", Objects.requireNonNullElse(message, "No message provided"));
        responseBody.put("timestamp", Instant.now().toString());

        if (validationErrors != null && !validationErrors.isEmpty()) {
            responseBody.put("errors", validationErrors);
        }

        return ResponseEntity.status(status).body(responseBody);
    }
}