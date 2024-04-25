package cl.customer.customerapi.model.repositories;

import cl.customer.customerapi.model.entities.Customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomersRepository extends JpaRepository<Customers, Long> {
    boolean existsByEmail(@Param("correo") String correo);
}
