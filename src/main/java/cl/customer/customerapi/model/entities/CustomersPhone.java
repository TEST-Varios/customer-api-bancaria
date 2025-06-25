package cl.customer.customerapi.model.entities;

import lombok.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "customers_phones")
public class CustomersPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phones")
    private Long idPhones;

    @Column(name = "number")
    private Long number;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customers customers;

}
