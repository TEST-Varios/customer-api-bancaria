package cl.customer.customerapi.service.servicios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import cl.customer.customerapi.service.implementacion.CustomersImpl;

import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.model.repositories.ICustomersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ICustomersServiceTest {

    @Mock
    private ICustomersRepository iCustomersRepository;

    @InjectMocks
    private CustomersImpl customersService;

    @Test
    void getAllCustomersTest() {

        List<Customers> customers = new ArrayList<>();
        customers.add(new Customers());
        given(iCustomersRepository.findAll()).willReturn(customers);

        List<Customers> expected = customersService.getAllCustomers();
        assertEquals(expected, customers);
        verify(iCustomersRepository).findAll();

    }

    @Test
    void getByIdCustomerTest() {
        Long customerID = 1L;

        Customers mockCustomer = Mockito.mock(Customers.class);
        when(iCustomersRepository.findById(customerID)).thenReturn(Optional.of(mockCustomer));

        Optional<Customers> result = Optional.ofNullable(customersService.getByIdCustomer(customerID));

        assertTrue(result.isPresent());
        assertSame(mockCustomer, result.get());
    }

    @Test
    void saveCustomerTest() {

        Date date = new Date();

        Customers customer = Customers.builder()
                .idCustomers(1L)
                .name("Mauricio")
                .email("fail-email@yahoo.com")
                .createAt(date)
                .modified(date)
                .lastLogin(date)
                .token("oiaj039r902kjsahfhowwieq9ie882029j")
                .isActive(true)
                .build();

        when(iCustomersRepository.save(ArgumentMatchers.any(Customers.class))).thenReturn(customer);
        Customers customerSave = customersService.saveCustomer(customer);
        assertThat(customerSave.getName()).isSameAs(customer.getName());
        verify(iCustomersRepository).save(customer);
    }

    @Test
    void deleteCustomerTest_ifFound() {

        Customers customer = Customers.builder()
                .idCustomers(1L)
                .build();
       // when(iCustomersRepository.findById(customer.getIdCustomers())).thenReturn(Optional.of(customer));
        customersService.deleteCustomer(customer.getIdCustomers());
        verify(iCustomersRepository).deleteById(customer.getIdCustomers());
    }

    @Test
    void exceptionWhenCustomerNotFoundTest() {

        Customers customer = Customers.builder()
                .idCustomers(3L)
                .name("Alejandro")
                .build();

        //given(iCustomersRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        customersService.deleteCustomer(customer.getIdCustomers());
    }
}