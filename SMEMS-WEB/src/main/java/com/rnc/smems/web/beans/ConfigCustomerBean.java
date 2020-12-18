package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Customer;

import com.rnc.smems.web.services.CustomerService;
/* @Ye Ko Ko
 * 18.12.2020
 */

@Named
@ViewScoped
public class ConfigCustomerBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Customer customer; 
	
	private List<Customer> customers;
	
	@Inject
	private CustomerService customerService;
	
	@PostConstruct
	public void initialize () {
		
		customer = new Customer(); 
		customers = customerService.findAll();
	}
	
	public void save () {
		customerService.save(customer);
		initialize();
	}
	
	public void update (Customer customer) {
		this.customer = customer;
	}
	
	public void delete (Customer customer) {
		customerService.delete(customer);
		initialize();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	

}
