package cl.customer.customerapi.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customers_phones")
public class CustomersPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phones")
    private Long idPhones;

    @JsonProperty("number")
    private String number;

    @JsonProperty("city_code")
    private String cityCode;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customers;

}
