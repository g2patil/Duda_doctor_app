package exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Unique Constraint Violation
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUniqueConstraintViolation(
            DataIntegrityViolationException ex, WebRequest request) {

        // Check if the exception is due to a unique constraint violation
        String errorMessage = "A record with the same unique field already exists.";
        
        // Log exception details (optional)
        System.out.println("Exception occurred: " + ex.getMessage());

        // Return error response with a custom message and HTTP status CONFLICT (409)
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    // Optionally, handle other exceptions globally (e.g., NullPointerException)
}

