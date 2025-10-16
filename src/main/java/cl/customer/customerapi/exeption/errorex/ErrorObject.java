package cl.customer.customerapi.exeption.errorex;

import lombok.*;
import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;

}
