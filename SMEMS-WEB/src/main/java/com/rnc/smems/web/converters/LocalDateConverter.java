package com.rnc.smems.web.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Model
public class LocalDateConverter implements Converter<LocalDate>{
	
	private DateTimeFormatter df;
	
	@PostConstruct
	private void init() {
		df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
		if(null != value && !value.isEmpty()) {
			return LocalDate.parse(value, df);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		if(null != value) {
			LocalDate date = (LocalDate) value;
			return date.format(df);
		}
		return null;
	}

}
