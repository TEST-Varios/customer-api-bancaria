package cl.customer.customerapi.exeption.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorMessage {

    @JsonProperty("status_code")
    public HttpStatus statusCode;

    @JsonProperty("message")
    public String message;

    @JsonProperty("errors")
    public List<String> errors;

    public ErrorMessage(HttpStatus httpStatus, String message) {
    }

    public ErrorMessage() {

    }
}
