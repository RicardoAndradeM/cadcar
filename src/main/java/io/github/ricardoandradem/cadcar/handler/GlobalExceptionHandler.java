package io.github.ricardoandradem.cadcar.handler;

import io.github.ricardoandradem.cadcar.common.exception.ClientNotFoundException;
import io.github.ricardoandradem.cadcar.client.exception.CpfAlreadyExistsException;
import io.github.ricardoandradem.cadcar.login.exception.IncorrectCredentialsException;
import io.github.ricardoandradem.cadcar.common.exception.EmailAlreadyExistsException;
import io.github.ricardoandradem.cadcar.user.exception.UserNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.exception.BrandNotFoundException;
import io.github.ricardoandradem.cadcar.vehicle.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<String> handleIncorrectCredentialsException(IncorrectCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<String> handleCpfAlreadyExistsException(CpfAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
