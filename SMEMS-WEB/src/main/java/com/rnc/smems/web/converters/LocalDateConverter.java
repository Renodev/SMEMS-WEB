package com.rnc.smems.web.converters;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * 
 * @author Thae Nandar Soe
 * 			18/12/2020
 *
 */

@Model
public class LocalDateConverter implements Converter<LocalDate>{
	

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
		if(null != value && !value.isEmpty()) {
			return LocalDate.parse(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		if(null != value) {
			LocalDate date = (LocalDate) value;
			return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		return null;
	}

}
