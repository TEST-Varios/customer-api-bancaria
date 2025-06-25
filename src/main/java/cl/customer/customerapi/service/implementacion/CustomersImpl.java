package cl.customer.customerapi.service.implementacion;

import cl.customer.customerapi.model.repositories.ICustomersRepository;
import cl.customer.customerapi.model.dto.PhoneDto;
import cl.customer.customerapi.model.dto.RequestCustomer;
import cl.customer.customerapi.model.dto.ResponseCustomer;
import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.model.entities.CustomersPhone;
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
    public List<ResponseCustomer> getAllCustomers() {
        return iCustumersRepository.findAll()
                .stream()
                .map(this::mapToResponseCustomer)
                .toList();
    }    

    @Override
    public ResponseCustomer getByIdCustomer(Long id) {
        return iCustumersRepository.findById(id)
                .map(this::mapToResponseCustomer)
                .orElse(null);
    }

    @Override
    public void saveCustomer(RequestCustomer customer) {        
        Customers customers = new Customers();

        customers.setName(customer.getName());
        customers.setEmail(customer.getEmail());
        customers.setPassword(customer.getPassword());
        
        List<CustomersPhone> phonesList = customer.getPhone()
                .stream()
                .map(phoneDto -> {
                    CustomersPhone phoneEntity = new CustomersPhone();

                    phoneEntity.setNumber(phoneDto.getNumber());
                    phoneEntity.setCityCode(phoneDto.getCityCode());
                    phoneEntity.setCountryCode(phoneDto.getCountryCode());

                    phoneEntity.setCustomers(customers);
                    return phoneEntity;
                })
                .toList();
        
        customers.setPhones(phonesList);
        
        iCustumersRepository.save(customers);
        mapToResponseCustomer(customers);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (iCustumersRepository.existsById(id)) {
            iCustumersRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El id " + id + " no se encuentra.");
        }
    }

    public ResponseCustomer mapToResponseCustomer(Customers customers) {
        ResponseCustomer responseCustomer = new ResponseCustomer();        
        
        responseCustomer.setName(customers.getName());
        responseCustomer.setEmail(customers.getEmail());
        responseCustomer.setPassword(customers.getPassword());
        List<PhoneDto> phonesList = customers.getPhones()
                .stream()
                .map(phoneEntity -> {
                    PhoneDto phoneDto = new PhoneDto();
                    phoneDto.setNumber(phoneEntity.getNumber());
                    phoneDto.setCityCode(phoneEntity.getCityCode());
                    phoneDto.setCountryCode(phoneEntity.getCountryCode());
                    return phoneDto;
                })
                .toList();
        responseCustomer.setPhones(phonesList);
        return responseCustomer;
    }

   
}
