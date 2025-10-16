package cl.customer.customerapi.model.entities;

import org.springframework.format.annotation.DateTimeFormat;
import cl.customer.customerapi.model.enums.Roles;
import cl.customer.customerapi.model.enums.Status;
import lombok.*;
import jakarta.persistence.*;
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
    @Column(name = "customer_id")
    private Long idCustomers;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_active")
    private Status status;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created")
    private Date created;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "modified")
    private Date modified;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "last_login")
    private Date lastLogin;

    
    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomersPhone> phones;

    @PrePersist
    public void preSaveCreate() {
        if (this.status == null) {
            this.status = Status.ACTIVO;
        }

        created = new Date();
        modified = new Date();
        lastLogin = new Date();
    }

}