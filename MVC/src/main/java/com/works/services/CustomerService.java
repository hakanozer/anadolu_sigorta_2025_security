package com.works.services;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerLoginDto;
import com.works.repositories.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DBService dbService;
    private final TinkEncDec tinkEncDec;
    private final HttpServletRequest request;

    public Customer login(CustomerLoginDto customerLoginDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customerLoginDto.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            String plainPassword = tinkEncDec.decrypt(customer.getPassword());
            if (customerLoginDto.getPassword().equals(plainPassword)) {
                customer.setPassword(null);
                request.getSession().setAttribute("customer", customer);
                return customer;
            }
        }
        return null;
    }

    public boolean loginDB(CustomerLoginDto customerLoginDto) {
        try {
            String sql = "select * from customer where email = ? and password = ?";
            PreparedStatement st = dbService.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, customerLoginDto.getEmail());
            st.setString(2, customerLoginDto.getPassword());
            ResultSet rs = st.executeQuery();
            return rs.next();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
