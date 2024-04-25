package cl.customer.customerapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.customer.customerapi.model.entities.CustomersPhone;

public interface IPhonesRepository extends JpaRepository<CustomersPhone, Long> {

}
