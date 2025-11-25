package ar.com.gha.heroesms.infrastructure.input.rest.exception;

import ar.com.gha.heroesms.infrastructure.input.rest.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccess(DataAccessException ex, WebRequest request) {
        return build(HttpStatus.BAD_GATEWAY, "Database access error", ex, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Bad request", ex, request);
    }

    @ExceptionHandler(DuplicateAliasException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateAliasException ex, WebRequest request) {
        return build(HttpStatus.CONFLICT, "Duplicate resource", ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex, request);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String error, Exception ex, WebRequest request) {
        log.error("{}", error, ex);
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                status.value(),
                error
        );
        return ResponseEntity.status(status).body(body);
    }
}
