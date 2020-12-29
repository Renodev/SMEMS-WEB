package com.rnc.smems.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.repositories.ProductRepository;

@Named
@Dependent
public class ProductConverter implements Converter<Product> {

	@Inject
	private ProductRepository productRepository;
	
	@Override
	public Product getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			Product product = productRepository.findByID(Long.parseLong(value));
			return product;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Product value) {
		if(value != null) {
			String id = String.valueOf(value.getId());
			return id;
		}
		return null;
	}
}