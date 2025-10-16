package cl.customer.customerapi.service.implementacion;

import cl.customer.customerapi.model.repositories.ICustomersRepository;
import cl.customer.customerapi.model.dto.PhoneDto;
import cl.customer.customerapi.model.dto.RequestCustomer;
import cl.customer.customerapi.model.dto.ResponseCustomer;
import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.model.entities.CustomersPhone;
import cl.customer.customerapi.model.enums.Roles;
import cl.customer.customerapi.service.servicios.ICustomersService;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

@Log4j2
@Service
public class CustomersImpl implements ICustomersService {

    private final ICustomersRepository iCustumersRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomersImpl(ICustomersRepository iCustumersRepository, PasswordEncoder passwordEncoder) {
        this.iCustumersRepository = iCustumersRepository;
        this.passwordEncoder = passwordEncoder;
    
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
        iCustumersRepository.findByEmail(customer.getEmail())
            .ifPresent(existingCustomer -> {
                throw new IllegalArgumentException("El correo " + customer.getEmail() + " ya se encuentra registrado.");
            });  
        try {
            Customers customers = new Customers();
    
            customers.setName(customer.getName());
            customers.setEmail(customer.getEmail());
            customers.setPassword(passwordEncoder.encode(customer.getPassword()));

            Arrays.stream(Roles.values())
                    .filter(roles -> roles.name().equals("ROL_USER"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));            
            
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

            Arrays.stream(Status.values())
                    .filter(status -> status.name().equals("ACTIVO"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

            customers.setPhones(phonesList);            
            
            iCustumersRepository.save(customers);
            mapToResponseCustomer(customers);
            
        } catch (Exception ex) {
            log.error("Error al guardar el cliente: {}", ex.getMessage());
        }      
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
