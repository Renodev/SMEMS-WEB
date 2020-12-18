package com.rnc.smems.web.services;


import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.rnc.smems.web.entities.Customer;
import com.rnc.smems.web.repositories.CustomerRepository;

/* @Ye Ko Ko
 * 18.12.2020
 */


@LocalBean
@Stateless
public class CustomerService {
	@Inject
	private CustomerRepository customerRepository;
	
	public void save (Customer customer) {
		if (customer.getId() == 0) {
			customer.setDate(LocalDate.now());
			customerRepository.save(customer);
		} else {
			customerRepository.update(customer);
		}
	}
	
	public void delete (Customer customer) {
		Customer cm = findByID(customer.getId());
		customerRepository.delete(cm);
	}
	
	public Customer findByID (long id) {
		return customerRepository.findByID(id);
	}
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
		

}
