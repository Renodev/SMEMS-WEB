package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.repositories.StaffRepository;

@LocalBean
@Stateless
public class StaffService {
	
	@Inject
	private StaffRepository staffRepository;
	
	public void save (Staff staff) {
		if (staff.getId() == 0) {
			staff.setDate(LocalDate.now());
			staffRepository.save(staff);
		} else {
			staffRepository.update(staff);
		}
	}
	
	public void delete (Staff staff) {
		staff.setErase(true);
		staffRepository.update(staff);
	}
	
	public Staff findByID (long id) {
		return staffRepository.findByID(id);
	}
	
	public List<Staff> findAll() {
		return staffRepository.findAll();
	}
		
}
