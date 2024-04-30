package cl.customer.customerapi.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "customers_data")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_customers")
    private Long idCustomers;

    private String name;

    private String email;

    private String password;

    private String token;

    @JsonProperty("is_active")
    private boolean isActive;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("created")
    private Date createAt;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modified;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("last_login")
    private Date lastLogin;

    @JsonManagedReference
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    @JsonProperty("phones")
    private List<CustomersPhone> phonesList = new ArrayList<>();

    @PrePersist
    public void preSaveCreate() {
        isActive = true;
        createAt = new Date();
        modified = new Date();
        lastLogin = new Date();
    }

}