package cl.customer.customerapi.service.implementacion;

import cl.customer.customerapi.config.JwtUtil;
import cl.customer.customerapi.exeption.error.CustomExceptionError;
import cl.customer.customerapi.model.repositories.ICustomersRepository;
import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.service.servicios.ICustomersService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomersImpl implements ICustomersService {

    private final ICustomersRepository iCustumersRepository;

    public CustomersImpl(ICustomersRepository iCustumersRepository) {
        this.iCustumersRepository = iCustumersRepository;
    }

    @Override
    public List<Customers> getAllCustomers() {
        return (List<Customers>)  iCustumersRepository.findAll();
    }

    @Override
    public Customers getByIdCustomer(Long id) {
        return iCustumersRepository.findById(id).orElse(null);
    }

    @Override
    public Customers saveCustomer(Customers customer) throws CustomExceptionError {

        if (iCustumersRepository.existsByEmail(customer.getEmail())) {
            throw new CustomExceptionError("Email de usuario, ya esta registrado");
        } else {

            String user = customer.getName();
            String tokenBack = JwtUtil.generateToken(user);
            customer.setToken(tokenBack);

            return iCustumersRepository.save(customer);
        }

    }

    @Override
    public void deleteCustomer(Long id) {
        iCustumersRepository.deleteById(id);
    }
}
