package nl.cim.training.springboot.controllers;

import nl.cim.training.springboot.dto.ErrorResponse;
import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException nfe) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("EMPLOYEE_NOT_FOUND");
        errorResponse.setErrorMessage(nfe.getMessage());

        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }
}
