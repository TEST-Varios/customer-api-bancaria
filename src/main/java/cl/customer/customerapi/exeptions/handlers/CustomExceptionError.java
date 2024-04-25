package cl.customer.customerapi.exeptions.handlers;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class CustomExceptionError extends RuntimeException {

    private String message;
    private String uri;
    private Object errors;
    private HttpStatus code;

    public CustomExceptionError(HttpStatus code, String message, String uri, List<String> errors) {
        super();
        this.code = code;
        this.message = message;
        this.uri = uri;
        this.errors = errors;
    }

}
