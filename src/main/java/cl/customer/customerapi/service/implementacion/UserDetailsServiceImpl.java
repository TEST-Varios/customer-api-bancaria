package cl.customer.customerapi.service.implementacion;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.customer.customerapi.model.entities.Customers;
import cl.customer.customerapi.model.enums.Roles;
import cl.customer.customerapi.model.repositories.ICustomersRepository;

import java.util.ArrayList;

@Service("userDetailService")
@Transactional(readOnly=true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ICustomersRepository customersRepository;

    public UserDetailsServiceImpl(ICustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customers user = customersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado!"));
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        Roles userRole = user.getRole();
        if (userRole != null) {
            roles.add(new SimpleGrantedAuthority(userRole.name()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail() , user.getPassword(), roles);
    }
}
