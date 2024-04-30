package cl.customer.customerapi.controller;

import cl.customer.customerapi.config.SwaggerConfig;
import cl.customer.customerapi.exeption.error.CustomExceptionError;
import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.service.servicios.ICustomersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Api(value = "Controller Manager New Clients")
@Import(SwaggerConfig.class)
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private ICustomersService iCustomersService;

    @ApiOperation(value = "Listado de clientes", response = CustomerController.class)
    @GetMapping("/getAllCustomers")
    public List<Customers> getAllCostumers() {

        try {
            if (iCustomersService.getAllCustomers().isEmpty()) {
                log.error("No se encuentran datos de clientes!!");
            }

            return iCustomersService.getAllCustomers();

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo encontrar Datos!", ex);
        }
    }

    @ApiOperation(value = "Entrega detalle del Cliente por ID", response = CustomerController.class)
    @GetMapping("/getCostumerId/{custId}")
    public Customers getCustomerId(@PathVariable Long custId) {

        try {
            if (custId == null){
                log.error("Dato ID vacio !!");
            }
            return iCustomersService.getByIdCustomer(custId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo encontrar Datos!", ex);
        }
    }


    @ApiOperation(value = "Guarda nuevos Clientes", response = CustomerController.class)
    @PostMapping("/saveCustomer")
    public Customers saveCustomer (@RequestBody Customers customers) throws CustomExceptionError {

           return iCustomersService.saveCustomer(customers);

    }


    @ApiOperation(value = "Borra Clientes de la lista", response = CustomerController.class)
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@PathVariable Long id) {

        try {
            iCustomersService.deleteCustomer(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo borrar Datos!", ex);
        }
    }
}
