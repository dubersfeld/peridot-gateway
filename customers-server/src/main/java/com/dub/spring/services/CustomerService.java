package com.dub.spring.services;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.model.Customer;

@PreAuthorize("hasRole('CUSTOMER_USER')")
public interface CustomerService {
	
	public List<Customer> allCustomers();
	
	public Customer getCustomer(long id);

	@PreAuthorize("hasRole('CREATE')")
	public void createCustomer(Customer customer);
	// throws URISyntaxException;
	
	@PreAuthorize("hasRole('UPDATE')")
	public void updateCustomer(Customer customer);
	
	@PreAuthorize("hasRole('DELETE')")
	public void deleteCustomer(long id);
			
}
