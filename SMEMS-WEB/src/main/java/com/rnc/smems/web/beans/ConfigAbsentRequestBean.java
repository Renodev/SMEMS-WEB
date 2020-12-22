package com.rnc.smems.web.beans;


import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.AbsentRequest;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.AbsentRequestService;
import com.rnc.smems.web.services.StaffService;

/**
 * 
 * @author Thae Nandar Soe
 * 			18/12/2020
 *
 */

@Named
@ViewScoped
public class ConfigAbsentRequestBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AbsentRequest absentRequest;
	
	private List<Staff> staffs; 
	
	private List<AbsentRequest> absentRequests;
	
	@Inject
	private AbsentRequestService absentRequestService;
	
	@Inject
	private StaffService staffService;
	
	@PostConstruct
	public void initialize() {
		absentRequest = new AbsentRequest();
		absentRequests = absentRequestService.findAll();
		staffs = staffService.findAll();
	}
	
	public void save() {
		absentRequestService.save(absentRequest);
		initialize();
	}
	public void update(AbsentRequest absentRequest) {
		this.absentRequest = absentRequest;
	}
	
	public void delete(AbsentRequest absentRequest) {
		absentRequestService.delete(absentRequest);
		initialize();
	}

	public AbsentRequest getAbsentRequest() {
		return absentRequest;
	}

	public void setAbsentRequest(AbsentRequest absentRequest) {
		this.absentRequest = absentRequest;
	}

	public List<AbsentRequest> getAbsentRequests() {
		return absentRequests;
	}

	public void setAbsentRequests(List<AbsentRequest> absentRequests) {
		this.absentRequests = absentRequests;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	
}
