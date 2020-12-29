package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.AbsentRequest;
import com.rnc.smems.web.entities.Attendance;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.repositories.AttendanceRepository;

@LocalBean
@Stateless
public class AttendanceService {
	
	@Inject
	private AttendanceRepository attendanceRepository;
	
	public void save(Attendance attendance) {
		if(attendance.getId() == 0) {
			attendanceRepository.save(attendance);
		} else {
			update(attendance);
		}
	}
	public void getAttendance(AbsentRequest absentRequest) {
		if(absentRequest.getDateFrom()!=null && absentRequest.getDateTo()!=null) {
			LocalDate current = absentRequest.getDateFrom();
			while(!current.isEqual(absentRequest.getDateTo())) {
				
				Attendance attendance = new Attendance();
				
				attendance.setDate(current);
				attendance.setStaff(absentRequest.getStaff());
				attendance.setPresent(true);
				attendance.setDescription(absentRequest.getDescription());
				save(attendance);
				current= current.plusDays(1);
			}	
		}
	}
	
	public void update(Attendance attendance) {
		attendanceRepository.update(attendance);
	}
	
	public void delete(Attendance attendance) {
		Attendance att = findByID(attendance.getId());
		attendanceRepository.delete(att);
	}
	
	public Attendance findByID(long id) {
		return attendanceRepository.findByID(id);
	}
	
	public List<Attendance> findByDate(LocalDate date){
		return attendanceRepository.findByDate(date);
	}
	
	public List<Attendance> findAll(){
		return attendanceRepository.findAll();
	}
	
	public List<Attendance> findByStaffDateFromAndDateTo(Staff staff,String dateFrom, String dateTo){
		return attendanceRepository.findByStaffAndDateFromAndDateTo(staff.getId(),dateFrom,dateTo);
	}
	
}

