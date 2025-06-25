package cl.customer.customerapi.controller;


import cl.customer.customerapi.exeption.error.CustomExceptionError;
import cl.customer.customerapi.model.dto.RequestCustomer;
import cl.customer.customerapi.model.dto.ResponseCustomer;
import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.service.implementacion.CustomersImpl;
import cl.customer.customerapi.service.servicios.ICustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomersImpl customerService;

    public CustomerController(CustomersImpl customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/getAllCustomers")
    public List<ResponseCustomer> getAllCostumers() {

        try {
            if (customerService.getAllCustomers().isEmpty()) {
                log.error("No se encuentran datos de clientes!!");
            }

            return customerService.getAllCustomers();

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo encontrar Datos!", ex);
        }
    }

    @GetMapping("/getCostumerId/{custId}")
    public ResponseCustomer getCustomerId(@PathVariable Long custId) {

        try {
            if (custId == null){
                log.error("Dato ID vacio !!");
            }
            return customerService.getByIdCustomer(custId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo encontrar Datos!", ex);
        }
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<ResponseCustomer> saveCustomer (@RequestBody RequestCustomer customers) {
        customerService.saveCustomer(customers);
        ResponseCustomer responseCustomer = new ResponseCustomer( 
            customers.getName(),
            customers.getEmail(),
            customers.getPassword(),
            null,
            true,
            customers.getPhone());
           return new ResponseEntity<>(responseCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) {

        try {
            customerService.deleteCustomer(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo borrar Datos!", ex);
        }
    }
}
