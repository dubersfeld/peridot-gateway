package com.dub.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dub.spring.model.Customer;

public interface CustomerRepository 
					extends JpaRepository<Customer, Long> {

}
