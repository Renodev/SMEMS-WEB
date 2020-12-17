package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.enums.Position;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class ConfigStaffBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Staff staff;
	
	private List<Staff> staffs;
	
	private Position [] positions;
	
	@Inject
	private StaffService staffService;
	
	@PostConstruct
	public void initialize () {
		staff = new Staff();
		staffs = staffService.findAll();
		positions = Position.values();
	}
	
	public void save () {
		staffService.save(staff);
		initialize();
	}
	
	public void update (Staff staff) {
		this.staff = staff;
	}
	
	public void delete (Staff staff) {
		staffService.delete(staff);
		initialize();
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
		this.positions = positions;
	}
	
}