package cl.customer.customerapi.exeptions.handlers;

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

    @JsonProperty("uri")
    public String uriRequested;

    @JsonProperty("errors")
    public List<String> errors;

}