package com.dub.spring.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dub.spring.exceptions.CustomerNotFoundException;
import com.dub.spring.exceptions.CustomerServerException;
import com.dub.spring.exceptions.DuplicateCustomerException;
import com.dub.spring.model.Customer;
import com.dub.spring.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
				
	@Autowired
    private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> allCustomers() {
				
		List<Customer> list = customerRepository.findAll();
	
		return list;
	}


	@Override
	public void createCustomer(Customer customer) {
						
		try {
			customerRepository.save(customer);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateCustomerException();
		} catch (Exception e) {
			throw new CustomerServerException();
		}
	}


	@Override
	public Customer getCustomer(long id) {
				
		// never return null
		Customer customer = customerRepository.findOne(id);
		if (customer != null) {
			return customer;
		} else {
			throw new CustomerNotFoundException();
		}
	}


	@Override
	public void updateCustomer(Customer customer) {
					
		customerRepository.save(customer);
	}


	@Override
	public void deleteCustomer(long id) {
				
		customerRepository.delete(id);
	}
}
