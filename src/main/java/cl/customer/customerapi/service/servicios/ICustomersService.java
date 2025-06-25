package cl.customer.customerapi.service.servicios;


import java.util.List;

import cl.customer.customerapi.model.dto.ResponseCustomer;
import cl.customer.customerapi.model.dto.RequestCustomer;

public interface ICustomersService {

    List<ResponseCustomer> getAllCustomers();
    ResponseCustomer getByIdCustomer(Long id);
    void saveCustomer(RequestCustomer customer);
    void deleteCustomer(Long id);
}
