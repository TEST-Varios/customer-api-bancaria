package cl.customer.customerapi.model.dto;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomer {

    private String name;

    private String email;

    private String password;

    private String token;

    private boolean isActive;

    private List<PhoneDto> phones;

}
