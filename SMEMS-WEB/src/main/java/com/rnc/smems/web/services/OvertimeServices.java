package com.rnc.smems.web.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.OverTime;
import com.rnc.smems.web.entities.Staff;
import com.rnc.smems.web.repositories.OvertimeRepostitory;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;

@LocalBean
@Stateless
public class OvertimeServices {
	
	
	@Inject
	private OvertimeRepostitory overtimeRepostitory;
	
	
	public  void save(OverTime  overTime) {
		if(overTime.getId()==0) {
			overTime.setDate(LocalDate.now());
			overtimeRepostitory.save(overTime);
		}else { 
			overtimeRepostitory.update(overTime);
		}
	}
	
	public void delete(OverTime overTime) {
	overTime.setErase(true);
	overtimeRepostitory.update(overTime);
		
	}
	
	public  OverTime finbyid(long id) {
		return overtimeRepostitory.findbyid(id);
	}
	
	public List<OverTime> findbyStaff(Staff staff){
		return  overtimeRepostitory.finbystaff(staff.getId());
		
	}
 	
	public List<OverTime> findall(){
		return overtimeRepostitory.findAll();
	}
	
	
	
	
}
