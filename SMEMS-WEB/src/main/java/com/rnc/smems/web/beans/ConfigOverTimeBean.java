package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Attendance;
import com.rnc.smems.web.entities.OverTime;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.AttendanceService;
import com.rnc.smems.web.services.OverTimeService;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class ConfigOverTimeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private OverTime overtime;
	
	private Staff staff;
	
	private LocalDate date;

	private boolean attend;

	private List<Staff> staffs;
	
	private List<Attendance> attendStaff;
	
	private List<OverTime> overtimes;
	
	@Inject
	private OverTimeService overtimeService;
	
	@Inject
	private StaffService staffService;
	
	@Inject 
	private AttendanceService attendanceService;
	
	
	@PostConstruct
	public void initialize () {
		staff = new Staff();
		overtime = new OverTime();
		staffs = staffService.findAll();
		overtimes = overtimeService.findAll();
		
		//attendStaff = new ArrayList<>();
		//attendStaff = attendanceService.findByAttendance(date);
	}


	
	public List<Attendance> getAttendStaff() {
		return attendStaff;
	}

	public void setAttendStaff(List<Attendance> attendStaff) {
		this.attendStaff = attendStaff;
	}

	public AttendanceService getAttendanceService() {
		return attendanceService;
	}


	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}


	public void save () {
		overtimeService.save(overtime);
		initialize();
	}
	
	public void update (OverTime overtime) {
		this.overtime = overtime;
	}
	
	public void delete (OverTime overtime) {
		overtimeService.delete(overtime);
		initialize();
	}
	
	public OverTime getOvertime() {
		return overtime;
	}

	public void setOvertime(OverTime overtime) {
		this.overtime = overtime;
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

	public OverTimeService getOvertimeService() {
		return overtimeService;
	}

	public void setOvertimeService(OverTimeService overtimeService) {
		this.overtimeService = overtimeService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public boolean isAttend() {
		return attend;
	}

	public void setAttend(boolean attend) {
		this.attend = attend;
	}

	public List<OverTime> getOvertimes() {
		return overtimes;
	}

	public void setOvertimes(List<OverTime> overtimes) {
		this.overtimes = overtimes;
	}
	
	

}
