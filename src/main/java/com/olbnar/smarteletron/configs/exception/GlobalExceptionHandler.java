package com.olbnar.smarteletron.configs.exception;

import com.olbnar.smarteletron.exception.role.RoleNotFoundException;
import com.olbnar.smarteletron.exception.tradeoperation.OperationCodeException;
import com.olbnar.smarteletron.exception.tradeoperation.OperationTypeException;
import com.olbnar.smarteletron.exception.tradeoperation.TradeOperationException;
import com.olbnar.smarteletron.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND,  String.valueOf(HttpStatus.FORBIDDEN.value()), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN, String.valueOf(HttpStatus.FORBIDDEN.value()), "Acesso negado: Você não tem permissão para acessar este recurso",
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()) ,ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationCodeException.class)
    public ResponseEntity<?> handleOperationCodeException(OperationCodeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationTypeException.class)
    public ResponseEntity<?> handleOperationTypeException(OperationTypeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TradeOperationException.class)
    public ResponseEntity<?> handleTradeOperationException(TradeOperationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Classe interna para detalhes do erro
    static class ErrorDetails {
        private HttpStatus status;
        private String statusCode;
        private String message;
        private String details;

        public ErrorDetails(HttpStatus status, String httpStatusCode, String message, String details) {
            this.status = status;
            this.statusCode = httpStatusCode;
            this.message = message;
            this.details = details;
        }

        // Getters
        public HttpStatus getStatus() {
            return status;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }
    }
}
