package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Attendance;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.services.AttendanceService;
import com.rnc.smems.web.services.StaffService;

@Named
@ViewScoped
public class ConfigAttendanceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LocalDate date;
	
	private List<Attendance> attendances;
	
	@Inject
	private AttendanceService attendanceService;

	@Inject
	private StaffService staffService;
	
	@PostConstruct
	public void initialize () {
		date = LocalDate.now();
		List<Staff> staffs = staffService.findAll();
		attendances = attendanceService.findByDate(date);
		if (attendances.isEmpty()) {
			for (Staff staff : staffs) {
				Attendance attendance = new Attendance();
				attendance.setDate(LocalDate.now());
				attendance.setStaff(staff);
				attendanceService.save(attendance);
			}
			attendances = attendanceService.findByDate(date);
		}
	}
	
	public void update () {
		for (Attendance attendance : attendances) {
			attendanceService.update(attendance);
			initialize(); //plus commend
		}
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}