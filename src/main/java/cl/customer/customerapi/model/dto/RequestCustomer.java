package cl.customer.customerapi.model.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCustomer {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phone;

}
