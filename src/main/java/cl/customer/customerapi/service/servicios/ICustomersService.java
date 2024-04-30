package cl.customer.customerapi.service.servicios;

import cl.customer.customerapi.exeption.error.CustomExceptionError;
import cl.customer.customerapi.model.entities.Customers;

import java.util.List;

public interface ICustomersService {

    List<Customers> getAllCustomers();
    Customers getByIdCustomer(Long id);
    Customers saveCustomer(Customers customer) throws CustomExceptionError;
    void deleteCustomer(Long id);
}
