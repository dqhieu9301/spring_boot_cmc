package spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;




@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = DuplicateRecordException.class)
	public ResponseEntity<?> handlerDuplicateRecordException(DuplicateRecordException ex, WebRequest req) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<?> handlerUnauthorizedException(UnauthorizedException ex, WebRequest req) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = InCorectException.class)
	public ResponseEntity<?> handlerInCorectException(InCorectException ex, WebRequest req) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ex, WebRequest req) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
