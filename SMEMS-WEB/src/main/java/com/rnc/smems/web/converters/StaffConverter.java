 package com.rnc.smems.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.repositories.StaffRepository;

/**
 * 
 * @author Thae Nandar Soe
 * 			18/12/2020
 *
 */

@Named
@Dependent
public class StaffConverter implements Converter<Staff> {
	
	@Inject
	private StaffRepository staffRepoistory;

	@Override
	public Staff getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null) {
			Staff staff = staffRepoistory.findByID(Long.parseLong(value));
			return staff;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Staff value) {
		if(value!=null) {
			String id = String.valueOf(value.getId());
			return id;
		}
		return null;
	}
	
	

}
