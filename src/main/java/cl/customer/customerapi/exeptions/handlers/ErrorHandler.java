package cl.customer.customerapi.exeptions.handlers;

import java.util.Collections;
import java.util.List;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomExceptionError.class)
    public ResponseEntity<ErrorMessage> handlerErrorMessage(Exception ex) {
        ErrorMessage errorRespuesta = new ErrorMessage();
        Object request = "";

        errorRespuesta.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorRespuesta.setMessage("Error email duplicado!!");
        errorRespuesta.setUriRequested(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        errorRespuesta.setErrors(Collections.singletonList(ex.getMessage()));


        return ResponseEntity.badRequest().body(errorRespuesta);
    }
}
