package co.id.app.servicebe.exception;

import co.id.app.servicebe.model.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponse> handleInternalServerError(Exception exception){
        log.error("exception internal server error = {} ",exception.getMessage());
        return new ResponseEntity<>(ServerResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .message("Something went wrong !")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<ServerResponse> handleUnauthorize(UnauthorizeException exception){
        log.error("exception unauthorize = {} ",exception.getMessage());
        return new ResponseEntity<>(ServerResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(new Date())
                .message(exception.getMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<ServerResponse> handleResourceNotfound(ResourceNotfoundException exception){
        log.error("exception resource not found = {} ",exception.getMessage());
        return new ResponseEntity<>(ServerResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(exception.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidResourceException.class)
    public ResponseEntity<ServerResponse> handleResourceInvalid(InvalidResourceException exception){
        log.error("exception resource invalid = {} ",exception.getMessage());
        return new ResponseEntity<>(ServerResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .message(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}

