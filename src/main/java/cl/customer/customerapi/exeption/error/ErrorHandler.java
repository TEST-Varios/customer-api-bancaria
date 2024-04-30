package cl.customer.customerapi.exeption.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomExceptionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handlerErrorMessage(CustomExceptionError ex) {

        ErrorMessage errorRespuesta = new ErrorMessage();
        Object request = null;

        errorRespuesta.setStatusCode(HttpStatus.BAD_REQUEST);
        errorRespuesta.setMessage("Error email duplicado!!");
        errorRespuesta.setErrors(Collections.singletonList(ex.getMessage()));


        return ResponseEntity.badRequest().body(errorRespuesta);
    }

}
