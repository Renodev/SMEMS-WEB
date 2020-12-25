package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.OverTime;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.OvertimeServices;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class CongfigOvertimeBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private OverTime overtime;
	
	private List<OverTime> overtimes;
	
	private List<Staff> staffs;
	
	@Inject
	private OvertimeServices overtimeServices;
	
	@Inject
	private StaffService  staffservices;
	
	@PostConstruct
	public void  intialize() {
	 overtime =new  OverTime();
	 overtimes=overtimeServices.findall();
	 staffs = staffservices.findAll();	 
	}
	
	
	public void save(OverTime overTime ) {
		 System.out.println(overtime.getStaff().getName());
	}
	
	public void update (OverTime overTime) {
		this.overtime=overTime;
	}
	
	public void delete(OverTime overTime) {
		 overtimeServices.delete(overTime);
		 intialize();
	}
	

	public OverTime getOvertime() {
		return overtime;
	}

	public void setOvertime(OverTime overtime) {
		this.overtime = overtime;
	}

	public List<OverTime> getOvertimes() {
		return overtimes;
	}

	public void setOvertimes(List<OverTime> overtimes) {
		this.overtimes = overtimes;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public OvertimeServices getOvertimeServices() {
		return overtimeServices;
	}

	public void setOvertimeServices(OvertimeServices overtimeServices) {
		this.overtimeServices = overtimeServices;
	}

	public StaffService getStaffservices() {
		return staffservices;
	}

	public void setStaffservices(StaffService staffservices) {
		this.staffservices = staffservices;
	}

}
