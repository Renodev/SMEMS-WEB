package com.rnc.smems.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.repositories.MaterialRepository;

@Named 
@Dependent
public class MaterialConverter implements Converter<Material>{

	@Inject
	private MaterialRepository materialRepository;
	
	@Override
	public Material getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			Material material = materialRepository.findByID(Long.parseLong(value));
			return material;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Material value) {
		if (value != null) {
			String id = String.valueOf(value.getId());
			return id;
		}
		return null;
	}

}
