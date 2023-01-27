package com.br.apilibrary.service.impls;

import com.br.apilibrary.entity.Customer;
import com.br.apilibrary.repository.CustomerRepository;
import com.br.apilibrary.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);

    }
}
