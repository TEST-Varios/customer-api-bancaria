package cl.customer.customerapi.model.dto;


import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cl.customer.customerapi.model.entities.CustomersPhone;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDTO {

    private Long idCustomers;

    private String name;

    private String email;

    private String password;

    private String token;

    private boolean isActive;

    private Date createAt;

    private Date modified;

    private Date lastLogin;

    private List<CustomersPhone> phonesList = new ArrayList<>();

}
