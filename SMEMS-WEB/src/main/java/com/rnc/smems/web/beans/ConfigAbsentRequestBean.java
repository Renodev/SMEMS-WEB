package com.rnc.smems.web.beans;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	
	public void generateOffDay () {
		if (absentRequest.getDateFrom() != null && absentRequest.getDateTo() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			try {
				String s1 = absentRequest.getDateFrom().toString();
				String s2 = absentRequest.getDateTo().toString();
				String str1 = s1.substring(8, 10) + s1.substring(4, 8) + s1.substring(0, 4) + " 00:00:00";
				String str2 = s2.substring(8, 10) + s2.substring(4, 8) + s2.substring(0, 4) + " 00:00:00";
				Date dateFrom = sdf.parse(str1);
				Date dateTo = sdf.parse(str2);
				long difference_In_Time = dateFrom.getTime() - dateTo.getTime(); 
				long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) 
                  % 365;
            absentRequest.setOffDay(~(difference_In_Days - 1));
			} catch (ParseException e) {

			}
		}
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
