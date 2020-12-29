package com.rnc.smems.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Customer;
import com.rnc.smems.web.repositories.CustomerRepository;

@Named
@Dependent
public class CustomerConverter implements Converter<Customer> {

	@Inject
	private CustomerRepository customerRepository;
	
	@Override
	public Customer getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			Customer cus = customerRepository.findByID(Long.parseLong(value));
			return cus;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Customer value) {
		if(value != null) {
			String id = String.valueOf(value.getId());
			return id;
		}
		return null;
	}
	
	
}